package com.web.app.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.Entity.ActionHistories;
import com.web.app.domain.Entity.CaseRelations;
import com.web.app.domain.Entity.Cases;
import com.web.app.domain.Entity.OdrUsers;
import com.web.app.domain.MosDetail.ParticipatedStatusChangeResultInfo;
import com.web.app.domain.MosDetail.RelationsContent;
import com.web.app.domain.constants.Constants;
import com.web.app.domain.constants.MessageConstants;
import com.web.app.mapper.UpdCasesStatusMapper;
import com.web.app.mapper.GetCaseRelationsMapper;
import com.web.app.service.CommonService;
import com.web.app.service.MosDetailService;
import com.web.app.service.UtilService;

/**
 * 申立て詳細画面_概要ServiceImpl
 * 
 * @author DUC 朱暁芳
 * @since 2024/04/23
 * @version 1.0
 */
@Service
public class MosDetailServiceImpl implements MosDetailService {

    private static final Logger log = LogManager.getLogger(MosDetailServiceImpl.class);

    @Autowired
    private UpdCasesStatusMapper updCasesStatusMapper;
    @Autowired
    private GetCaseRelationsMapper getCaseRelationsMapper;
    @Autowired
    private CommonService commonService;
    @Autowired
    private UtilService utilService;

    /**
     * 参加済状態変更
     * 参加表明対象ケースの状態の取得
     * 
     * @param caseId 参加表明する渡された引数: 案件ID
     * @param uId    引数: ユーザーID
     * @return 戻り値は「 参照表明更新済FLG」に返される
     */
    @Override
    public ParticipatedStatusChangeResultInfo participatedStatusSearch(String caseId, String uId) {
        // 1.参加表明対象ケースの状態の取得判定
        Cases participationSel = updCasesStatusMapper.participatedStatusSearch(caseId);
        ParticipatedStatusChangeResultInfo participatedFlag = new ParticipatedStatusChangeResultInfo();

        // 2.ケースの状態の更新
        if (participationSel != null) {
            participatedFlag = participatedCaseStatusChangeUpdate(participationSel, caseId, uId);
            if (participatedFlag != null) {
                return participatedFlag;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * ケース状態の更新
     * 
     * @param participationSel 参加表明対象ケースの状態に取得された
     * @param caseId           案件ID
     * @return 戻り値は「 参照表明更新済FLG」に返される
     */
    private ParticipatedStatusChangeResultInfo participatedCaseStatusChangeUpdate(
            Cases participationSel, String caseId, String uId) {
        // 「ID」を取得
        String cid = participationSel.getCid();
        // 「案件ステージ」を取得
        Integer caseStage = participationSel.getCaseStage();
        // 「案件ステータス」を取得
        String status = participationSel.getCaseStatus();

        ParticipatedStatusChangeResultInfo participatedStatusChangeResultInfo = new ParticipatedStatusChangeResultInfo();

        // テーブル「cases」から取得したCaseStageが0（回答） かつ CaseStatusが0000（申立後-参加待ち）の場合、以下の処理を実行
        if (caseStage == Constants.STR_CASES_CASESTAGE_0 && (Constants.CASE_STATUS_0.equals(status))) {
            // ケース状態の更新
            int updateNum = updCasesStatusMapper.caseStatusChangeUpdate(cid);
            // ケース状態の更新の件数が0以外場合
            if (updateNum != Constants.UPDATE_NUMBER_0) {
                // 正常に更新の場合、参照表明更新済Flgに0（正常更新）を設定して、画面へ返す
                participatedStatusChangeResultInfo.setParticipatedFlag(Constants.PARTICIPATED_FLAG_0);
            }

            // アクション履歴の登録
            ActionHistories actionHistory = new ActionHistories();
            actionHistory.setId(utilService.GetGuid());
            actionHistory.setPlatformId(Constants.PLATFORMID_0001);
            actionHistory.setCaseId(caseId);
            actionHistory.setActionType("MediatiorResigned");
            actionHistory.setCaseStage(Constants.NUM_0);
            actionHistory.setUserId(uId);
            actionHistory.setUserType(Constants.NUM_2);
            actionHistory.setHaveFile(false);
            actionHistory.setLastModifiedBy(uId);

            Boolean insertFlag = commonService.InsertActionHistories(actionHistory, null, true, false);
            // アクション履歴の登録 失敗
            if (!insertFlag) {
                log.error(MessageConstants.C00018E);
            }

            // メール送信用関係者メアドの取得
            RelationsContent relationsContent = selectRelationsContentData(caseId);
            if (relationsContent != null) {
                participatedStatusChangeResultInfo.setRelationsContent(relationsContent);
            }
        } else {
            // テーブル「案件」から取得したCaseStageが0（回答）以外の場合、以下の処理を実行
            // 正常に更新の場合、参照表明更新済Flgに1（更新不可）を設定し、画面へ返す
            participatedStatusChangeResultInfo.setParticipatedFlag(Constants.PARTICIPATED_FLAG_1);
        }
        return participatedStatusChangeResultInfo;
    }

    /**
     * 関係者内容取得
     *
     * @param caseId フロントエンド転送
     * @return 関係者内容取得の取得必要なすべてのデータ
     */
    @Override
    public RelationsContent selectRelationsContentData(String caseId) {
        // 関係者メアド取得API呼び出し
        // CaseRelations caseRelations =
        // getCaseRelationsMapper.getCaseRelations(caseId);

        RelationsContent relationsContent = new RelationsContent();

        // // メールベースクエリ対応userの名前
        // OdrUsers petitionUser = getRelationsContentMapper
        // .RelationsContentListDataSearch(caseRelations.getPetitionUserInfo_Email());

        // if (petitionUser != null) {
        // // 申立人氏名
        // relationsContent.setPetitionUserName(petitionUser.getFirstName() + " " +
        // petitionUser.getLastName());
        // // 申立人氏名（カナ）
        // relationsContent
        // .setPetitionUserkana(petitionUser.getFirstName_kana() + " " +
        // petitionUser.getLastName_kana());
        // // 申立人所属会社
        // relationsContent.setPetitionUsercompanyName(petitionUser.getCompanyName());
        // // 申立人メールアドレス
        // relationsContent.setPetitionUserEmail(caseRelations.getPetitionUserInfo_Email());
        // }

        // if (caseRelations.getAgent1_Email() != null) {
        // OdrUsers users =
        // getRelationsContentMapper.RelationsContentListDataSearch(caseRelations.getAgent1_Email());
        // if (users != null) {
        // // 代理人1氏名
        // relationsContent.setAgent1Name(users.getFirstName() + " " +
        // users.getLastName());
        // // 代理人1氏名（カナ）
        // relationsContent.setAgent1kana(users.getFirstName_kana() + " " +
        // users.getLastName_kana());
        // // 代理人1メールアドレス
        // relationsContent.setAgent1Email(caseRelations.getAgent1_Email());
        // } else {
        // // 取得なしの場合
        // relationsContent.setAgent1Flag(1);
        // }
        // }

        // if (caseRelations.getAgent2_Email() != null) {
        // OdrUsers users =
        // getRelationsContentMapper.RelationsContentListDataSearch(caseRelations.getAgent2_Email());
        // if (users != null) {
        // // 代理人2氏名
        // relationsContent.setAgent2Name(users.getFirstName() + " " +
        // users.getLastName());
        // // 代理人2氏名（カナ）
        // relationsContent.setAgent2kana(users.getFirstName_kana() + " " +
        // users.getLastName_kana());
        // // 代理人3メールアドレス
        // relationsContent.setAgent2Email(caseRelations.getAgent2_Email());
        // } else {
        // // 取得なしの場合
        // relationsContent.setAgent2Flag(1);
        // }
        // }

        // if (caseRelations.getAgent3_Email() != null) {
        // OdrUsers users =
        // getRelationsContentMapper.RelationsContentListDataSearch(caseRelations.getAgent3_Email());
        // if (users != null) {
        // // 代理人3氏名
        // relationsContent.setAgent3Name(users.getFirstName() + " " +
        // users.getLastName());
        // // 代理人3氏名（カナ）
        // relationsContent.setAgent3kana(users.getFirstName_kana() + " " +
        // users.getLastName_kana());
        // // 代理人3メールアドレス
        // relationsContent.setAgent3Email(caseRelations.getAgent3_Email());
        // } else {
        // // 取得なしの場合
        // relationsContent.setAgent3Flag(1);
        // }
        // }

        // if (caseRelations.getAgent4_Email() != null) {
        // OdrUsers users =
        // getRelationsContentMapper.RelationsContentListDataSearch(caseRelations.getAgent4_Email());
        // if (users != null) {
        // // 代理人4氏名
        // relationsContent.setAgent4Name(users.getFirstName() + " " +
        // users.getLastName());
        // // 代理人4氏名（カナ）
        // relationsContent.setAgent4kana(users.getFirstName_kana() + " " +
        // users.getLastName_kana());
        // // 代理人4メールアドレス
        // relationsContent.setAgent4Email(caseRelations.getAgent4_Email());
        // } else {
        // // 取得なしの場合
        // relationsContent.setAgent4Flag(1);
        // }
        // }

        // if (caseRelations.getAgent5_Email() != null) {
        // OdrUsers users =
        // getRelationsContentMapper.RelationsContentListDataSearch(caseRelations.getAgent5_Email());
        // if (users != null) {
        // // 代理人5氏名
        // relationsContent.setAgent5Name(users.getFirstName() + " " +
        // users.getLastName());
        // // 代理人5氏名（カナ）
        // relationsContent.setAgent5kana(users.getFirstName_kana() + " " +
        // users.getLastName_kana());
        // // 代理人5メールアドレス
        // relationsContent.setAgent5Email(caseRelations.getAgent5_Email());
        // } else {
        // // 取得なしの場合
        // relationsContent.setAgent5Flag(1);
        // }
        // }

        // OdrUsers traderUser = getRelationsContentMapper
        // .RelationsContentListDataSearch(caseRelations.getTraderUserEmail());

        // if (traderUser != null) {
        // // 相手方氏名
        // relationsContent.setTraderUserName(traderUser.getFirstName() + " " +
        // traderUser.getLastName());
        // // 相手方氏名（カナ）
        // relationsContent.setTraderUserkana(traderUser.getFirstName_kana() + " " +
        // traderUser.getLastName_kana());
        // // 相手方所属会社
        // relationsContent.setTraderUsercompanyName(traderUser.getCompanyName());
        // // 相手方メールアドレス
        // relationsContent.setTraderUserEmail(caseRelations.getTraderUserEmail());
        // }

        // if (caseRelations.getTraderAgent1_UserEmail() != null) {

        // OdrUsers users = getRelationsContentMapper
        // .RelationsContentListDataSearch(caseRelations.getTraderAgent1_UserEmail());
        // if (users != null) {
        // // 代理人1氏名
        // relationsContent.setTrader1Name(users.getFirstName() + " " +
        // users.getLastName());
        // // 代理人1氏名（カナ）
        // relationsContent.setTrader1kana(users.getFirstName_kana() + " " +
        // users.getLastName_kana());
        // // 代理人1メールアドレス
        // relationsContent.setTrader1Email(caseRelations.getTraderAgent1_UserEmail());
        // } else {
        // // 取得なしの場合
        // relationsContent.setTrader1Flag(1);
        // }
        // }

        // if (caseRelations.getTraderAgent2_UserEmail() != null) {
        // OdrUsers users = getRelationsContentMapper
        // .RelationsContentListDataSearch(caseRelations.getTraderAgent2_UserEmail());
        // if (users != null) {
        // // 代理人2氏名
        // relationsContent.setTrader2Name(users.getFirstName() + " " +
        // users.getLastName());
        // // 代理人2氏名（カナ）
        // relationsContent.setTrader2kana(users.getFirstName_kana() + " " +
        // users.getLastName_kana());
        // // 代理人2メールアドレス
        // relationsContent.setTrader2Email(caseRelations.getTraderAgent2_UserEmail());
        // } else {
        // // 取得なしの場合
        // relationsContent.setTrader2Flag(1);
        // }
        // }

        // if (caseRelations.getTraderAgent3_UserEmail() != null) {
        // OdrUsers users = getRelationsContentMapper
        // .RelationsContentListDataSearch(caseRelations.getTraderAgent3_UserEmail());
        // if (users != null) {
        // // 代理人3氏名
        // relationsContent.setTrader3Name(users.getFirstName() + " " +
        // users.getLastName());
        // // 代理人3氏名（カナ）
        // relationsContent.setTrader3kana(users.getFirstName_kana() + " " +
        // users.getLastName_kana());
        // // 代理人3メールアドレス
        // relationsContent.setTrader3Email(caseRelations.getTraderAgent3_UserEmail());
        // } else {
        // // 取得なしの場合
        // relationsContent.setTrader3Flag(1);
        // }
        // }

        // if (caseRelations.getTraderAgent4_UserEmail() != null) {
        // OdrUsers users = getRelationsContentMapper
        // .RelationsContentListDataSearch(caseRelations.getTraderAgent4_UserEmail());
        // if (users != null) {
        // // 代理人4氏名
        // relationsContent.setTrader4Name(users.getFirstName() + " " +
        // users.getLastName());
        // // 代理人4氏名（カナ）
        // relationsContent.setTrader4kana(users.getFirstName_kana() + " " +
        // users.getLastName_kana());
        // // 代理人4メールアドレス
        // relationsContent.setTrader4Email(caseRelations.getTraderAgent4_UserEmail());
        // } else {
        // // 取得なしの場合
        // relationsContent.setTrader4Flag(1);
        // }
        // }

        // if (caseRelations.getTraderAgent5_UserEmail() != null) {
        // OdrUsers users = getRelationsContentMapper
        // .RelationsContentListDataSearch(caseRelations.getTraderAgent5_UserEmail());
        // if (users != null) {
        // // 代理人5氏名
        // relationsContent.setTrader5Name(users.getFirstName() + " " +
        // users.getLastName());
        // // 代理人5氏名（カナ）
        // relationsContent.setTrader5kana(users.getFirstName_kana() + " " +
        // users.getLastName_kana());
        // // 代理人5メールアドレス
        // relationsContent.setTrader5Email(caseRelations.getTraderAgent5_UserEmail());
        // } else {
        // // 取得なしの場合
        // relationsContent.setTrader5Flag(1);
        // }
        // }

        return relationsContent;
    }

    @Override
    public com.web.app.domain.CaseRelations getCaseRelations(String CaseId) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCaseRelations'");
    }
}
