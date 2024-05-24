package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.Entity.ActionHistories;
import com.web.app.domain.Entity.CasePetitions;
import com.web.app.domain.Entity.CaseRelations;
import com.web.app.domain.Entity.OdrUsers;
import com.web.app.domain.Entity.UsersMessages;
import com.web.app.domain.MosDetail.PetitionsContent;
import com.web.app.domain.MosDetail.RelationsContent;
import com.web.app.domain.MosDetail.Withdrawal;
import com.web.app.domain.MosDetail.WithdrawalReturn;
import com.web.app.domain.constants.Constants;
import com.web.app.domain.constants.MessageConstants;
import com.web.app.mapper.AddMessagesMapper;
import com.web.app.mapper.ApplyWithdrawMapper;
import com.web.app.mapper.GetCaseRelationsMapper;
import com.web.app.mapper.GetPetitionsContentMapper;
import com.web.app.mapper.GetRelationsContentMapper;
import com.web.app.service.CommonService;
import com.web.app.service.MosDetailService;
import com.web.app.service.UtilService;

/**
 * 申立て概要画面
 * 
 * @author DUC 張万超 王亞テイ
 * @since 2024/04/23
 * @version 1.0
 */
@Service
public class MosDetailServiceImpl implements MosDetailService {

    private static final Logger log = LogManager.getLogger(MosDetailServiceImpl.class);

    @Autowired
    private ApplyWithdrawMapper applyWithdrawMapper;

    @Autowired
    private CommonService commonService;

    @Autowired
    private GetPetitionsContentMapper getPetitionsContentMapper;

    @Autowired
    private AddMessagesMapper addMessagesMapper;

    @Autowired
    private GetCaseRelationsMapper getCaseRelationsMapper;

    @Autowired
    private GetRelationsContentMapper getRelationsContentMapper;

    @Autowired
    private UtilService utilService;

    /**
     * 申立ての内容取得
     *
     * @param caseId フロントエンド転送
     * @return 申立ての内容の取得必要なすべてのデータ
     */
    @Override
    public PetitionsContent selectPetitionData(String caseId) {

        PetitionsContent petitionsContent = new PetitionsContent();

        // 申立ての内容取得
        CasePetitions casePetitions = getPetitionsContentMapper.petitionListDataSearch(caseId);

        petitionsContent.setCasePetitions(casePetitions);

        // 添付資料取得
        petitionsContent.setAttachedFile(getPetitionsContentMapper.petitionFileSearch(casePetitions.getCaseId()));

        // 拡張項目取得
        petitionsContent.setExtensionItem(getPetitionsContentMapper.petitionExtensionitemSearch(caseId));

        return petitionsContent;
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
        CaseRelations caseRelations = getCaseRelationsMapper.getCaseRelations(caseId);

        RelationsContent relationsContent = new RelationsContent();

        // メールベースクエリ対応userの名前
        OdrUsers petitionUser = getRelationsContentMapper
                .RelationsContentListDataSearch(caseRelations.getPetitionUserInfo_Email());

        if (petitionUser != null) {
            // 申立人氏名
            relationsContent.setPetitionUserName(petitionUser.getFirstName() + " " + petitionUser.getLastName());
            // 申立人氏名（カナ）
            relationsContent
                    .setPetitionUserkana(petitionUser.getFirstName_kana() + " " + petitionUser.getLastName_kana());
            // 申立人所属会社
            relationsContent.setPetitionUsercompanyName(petitionUser.getCompanyName());
            // 申立人メールアドレス
            relationsContent.setPetitionUserEmail(caseRelations.getPetitionUserInfo_Email());
        }

        if (caseRelations.getAgent1_Email() != null) {
            OdrUsers users = getRelationsContentMapper.RelationsContentListDataSearch(caseRelations.getAgent1_Email());
            if (users != null) {
                // 代理人1氏名
                relationsContent.setAgent1Name(users.getFirstName() + " " + users.getLastName());
                // 代理人1氏名（カナ）
                relationsContent.setAgent1kana(users.getFirstName_kana() + " " + users.getLastName_kana());
                // 代理人1メールアドレス
                relationsContent.setAgent1Email(caseRelations.getAgent1_Email());
            } else {
                // 取得なしの場合
                relationsContent.setAgent1Flag(1);
            }
        }

        if (caseRelations.getAgent2_Email() != null) {
            OdrUsers users = getRelationsContentMapper.RelationsContentListDataSearch(caseRelations.getAgent2_Email());
            if (users != null) {
                // 代理人2氏名
                relationsContent.setAgent2Name(users.getFirstName() + " " + users.getLastName());
                // 代理人2氏名（カナ）
                relationsContent.setAgent2kana(users.getFirstName_kana() + " " + users.getLastName_kana());
                // 代理人3メールアドレス
                relationsContent.setAgent2Email(caseRelations.getAgent2_Email());
            } else {
                // 取得なしの場合
                relationsContent.setAgent2Flag(1);
            }
        }

        if (caseRelations.getAgent3_Email() != null) {
            OdrUsers users = getRelationsContentMapper.RelationsContentListDataSearch(caseRelations.getAgent3_Email());
            if (users != null) {
                // 代理人3氏名
                relationsContent.setAgent3Name(users.getFirstName() + " " + users.getLastName());
                // 代理人3氏名（カナ）
                relationsContent.setAgent3kana(users.getFirstName_kana() + " " + users.getLastName_kana());
                // 代理人3メールアドレス
                relationsContent.setAgent3Email(caseRelations.getAgent3_Email());
            } else {
                // 取得なしの場合
                relationsContent.setAgent3Flag(1);
            }
        }

        if (caseRelations.getAgent4_Email() != null) {
            OdrUsers users = getRelationsContentMapper.RelationsContentListDataSearch(caseRelations.getAgent4_Email());
            if (users != null) {
                // 代理人4氏名
                relationsContent.setAgent4Name(users.getFirstName() + " " + users.getLastName());
                // 代理人4氏名（カナ）
                relationsContent.setAgent4kana(users.getFirstName_kana() + " " + users.getLastName_kana());
                // 代理人4メールアドレス
                relationsContent.setAgent4Email(caseRelations.getAgent4_Email());
            } else {
                // 取得なしの場合
                relationsContent.setAgent4Flag(1);
            }
        }

        if (caseRelations.getAgent5_Email() != null) {
            OdrUsers users = getRelationsContentMapper.RelationsContentListDataSearch(caseRelations.getAgent5_Email());
            if (users != null) {
                // 代理人5氏名
                relationsContent.setAgent5Name(users.getFirstName() + " " + users.getLastName());
                // 代理人5氏名（カナ）
                relationsContent.setAgent5kana(users.getFirstName_kana() + " " + users.getLastName_kana());
                // 代理人5メールアドレス
                relationsContent.setAgent5Email(caseRelations.getAgent5_Email());
            } else {
                // 取得なしの場合
                relationsContent.setAgent5Flag(1);
            }
        }

        OdrUsers traderUser = getRelationsContentMapper
                .RelationsContentListDataSearch(caseRelations.getTraderUserEmail());

        if (traderUser != null) {
            // 相手方氏名
            relationsContent.setTraderUserName(traderUser.getFirstName() + " " + traderUser.getLastName());
            // 相手方氏名（カナ）
            relationsContent.setTraderUserkana(traderUser.getFirstName_kana() + " " + traderUser.getLastName_kana());
            // 相手方所属会社
            relationsContent.setTraderUsercompanyName(traderUser.getCompanyName());
            // 相手方メールアドレス
            relationsContent.setTraderUserEmail(caseRelations.getTraderUserEmail());
        }

        if (caseRelations.getTraderAgent1_UserEmail() != null) {

            OdrUsers users = getRelationsContentMapper
                    .RelationsContentListDataSearch(caseRelations.getTraderAgent1_UserEmail());
            if (users != null) {
                // 代理人1氏名
                relationsContent.setTrader1Name(users.getFirstName() + " " + users.getLastName());
                // 代理人1氏名（カナ）
                relationsContent.setTrader1kana(users.getFirstName_kana() + " " + users.getLastName_kana());
                // 代理人1メールアドレス
                relationsContent.setTrader1Email(caseRelations.getTraderAgent1_UserEmail());
            } else {
                // 取得なしの場合
                relationsContent.setTrader1Flag(1);
            }
        }

        if (caseRelations.getTraderAgent2_UserEmail() != null) {
            OdrUsers users = getRelationsContentMapper
                    .RelationsContentListDataSearch(caseRelations.getTraderAgent2_UserEmail());
            if (users != null) {
                // 代理人2氏名
                relationsContent.setTrader2Name(users.getFirstName() + " " + users.getLastName());
                // 代理人2氏名（カナ）
                relationsContent.setTrader2kana(users.getFirstName_kana() + " " + users.getLastName_kana());
                // 代理人2メールアドレス
                relationsContent.setTrader2Email(caseRelations.getTraderAgent2_UserEmail());
            } else {
                // 取得なしの場合
                relationsContent.setTrader2Flag(1);
            }
        }

        if (caseRelations.getTraderAgent3_UserEmail() != null) {
            OdrUsers users = getRelationsContentMapper
                    .RelationsContentListDataSearch(caseRelations.getTraderAgent3_UserEmail());
            if (users != null) {
                // 代理人3氏名
                relationsContent.setTrader3Name(users.getFirstName() + " " + users.getLastName());
                // 代理人3氏名（カナ）
                relationsContent.setTrader3kana(users.getFirstName_kana() + " " + users.getLastName_kana());
                // 代理人3メールアドレス
                relationsContent.setTrader3Email(caseRelations.getTraderAgent3_UserEmail());
            } else {
                // 取得なしの場合
                relationsContent.setTrader3Flag(1);
            }
        }

        if (caseRelations.getTraderAgent4_UserEmail() != null) {
            OdrUsers users = getRelationsContentMapper
                    .RelationsContentListDataSearch(caseRelations.getTraderAgent4_UserEmail());
            if (users != null) {
                // 代理人4氏名
                relationsContent.setTrader4Name(users.getFirstName() + " " + users.getLastName());
                // 代理人4氏名（カナ）
                relationsContent.setTrader4kana(users.getFirstName_kana() + " " + users.getLastName_kana());
                // 代理人4メールアドレス
                relationsContent.setTrader4Email(caseRelations.getTraderAgent4_UserEmail());
            } else {
                // 取得なしの場合
                relationsContent.setTrader4Flag(1);
            }
        }

        if (caseRelations.getTraderAgent5_UserEmail() != null) {
            OdrUsers users = getRelationsContentMapper
                    .RelationsContentListDataSearch(caseRelations.getTraderAgent5_UserEmail());
            if (users != null) {
                // 代理人5氏名
                relationsContent.setTrader5Name(users.getFirstName() + " " + users.getLastName());
                // 代理人5氏名（カナ）
                relationsContent.setTrader5kana(users.getFirstName_kana() + " " + users.getLastName_kana());
                // 代理人5メールアドレス
                relationsContent.setTrader5Email(caseRelations.getTraderAgent5_UserEmail());
            } else {
                // 取得なしの場合
                relationsContent.setTrader5Flag(1);
            }
        }

        return relationsContent;
    }

    /**
     * 調停人退出メッセージ登録
     *
     * @param caseId         フロントエンド転送の案件ID
     * @param uid            フロントエンド転送のユーザーID
     * @param platformId     フロントエンド転送のplatformId
     * @param messageGroupId フロントエンド転送のmessageGroupId
     * @return 調停人退出メッセージ登録の状態
     */
    @Override
    public int AddMessages(String caseId, String uid, String platformId, String messageGroupId) {

        // セッション情報のCaseId対応な申立人・相手方・代理人のuserid
        List<String> result = addMessagesMapper.usersId(messageGroupId, platformId, uid);

        // GUID呼び出し
        String id = utilService.GetGuid();
        // 「メッセージ」新規登録
        int insertMessageNum = addMessagesMapper.messagesInsert(caseId, uid, id);
        // 「ユーザメッセージ」新規登録
        List<UsersMessages> usersMessagesList = new ArrayList<>();

        for (String item : result) {
            UsersMessages usersMessages = new UsersMessages();
            usersMessages.setId(utilService.GetGuid());
            usersMessages.setMessageId(id);
            usersMessages.setUserId(item);
            usersMessages.setCaseId(caseId);
            usersMessages.setPlatformId(platformId);
            usersMessagesList.add(usersMessages);
        }

        int insertUMessageNum = addMessagesMapper.usersMessagesInsert(usersMessagesList);

        if (insertMessageNum == 0 || insertUMessageNum == 0) {
            return 0;
        }
        return 1;
    }

    /**
     * ケースの状態を取り下げに変更する。
     *
     * @param caseId 渡し項目.CaseId
     * @param uid    渡し項目.uid
     * @return 変更結果
     */
    @Override
    @Transactional
    public WithdrawalReturn applyWithdraw(String caseId, String uid) {
        // 戻り値オブジェクトの作成
        WithdrawalReturn withdrawalReturn = new WithdrawalReturn();
        // 取り下げ対象ケースの状態の判定
        Withdrawal withdrawal = applyWithdrawMapper.getCaseStage(caseId);
        // 上記取得したCaseStageが0（回答）の場合、以下の処理を実行
        if (withdrawal.getCaseStage() == Constants.STR_CASES_CASESTAGE_0) {
            // ケース状態の更新
            int res = applyWithdrawMapper.updateWithdrawal(withdrawal.getCid());
            if (res != Constants.NUM_0) {
                // 正常に更新の場合、更新済Flgに0（正常更新）を設定して
                withdrawalReturn.setUpdateFlag(Constants.MOSDETAIL_UPDATEFLAG_0);

                ActionHistories actionHistory = new ActionHistories();

                actionHistory.setId(utilService.GetGuid());
                actionHistory.setPlatformId(Constants.PLATFORMID_0001);
                actionHistory.setCaseId(caseId);
                actionHistory.setActionType("MediatiorResigned");
                actionHistory.setCaseStage(Constants.NUM_1);
                actionHistory.setUserId(uid);
                actionHistory.setUserType(Constants.NUM_1);
                actionHistory.setHaveFile(false);
                actionHistory.setLastModifiedBy(uid);

                Boolean insertFlag = commonService.InsertActionHistories(actionHistory, null, true, false);

                if (!insertFlag) {
                    log.error(MessageConstants.C00018E);
                }

                // メール送信用関係者メアドの取得 TODO
                withdrawalReturn.setCaseRelations(new CaseRelations());

                // 上記APIから返された項目を戻り項目として画面へ返す
                return withdrawalReturn;
            }
        }

        // 上記取得したCaseStageが0（回答）以外の場合、以下の処理を実行
        // 正常に更新の場合、更新済Flgに1（更新不可）を設定し、画面へ返す
        withdrawalReturn.setUpdateFlag(Constants.MOSDETAIL_UPDATEFLAG_1);
        return withdrawalReturn;
    }
}
