package com.web.app.service.impl;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.Entity.ActionHistories;
import com.web.app.domain.Entity.CaseMediations;
import com.web.app.domain.Entity.CaseNegotiations;
import com.web.app.domain.Entity.CasePetitions;
import com.web.app.domain.Entity.CaseRelations;
import com.web.app.domain.Entity.Cases;
import com.web.app.domain.Entity.Files;
import com.web.app.domain.Entity.MasterPlatforms;
import com.web.app.domain.Entity.MediatorHistories;
import com.web.app.domain.Entity.OdrUsers;
import com.web.app.domain.Entity.UsersMessages;
import com.web.app.domain.MosDetail.CaseClaimrepliesMosDetail;
import com.web.app.domain.MosDetail.CaseInfo;
import com.web.app.domain.MosDetail.CaseMediationsData;
import com.web.app.domain.MosDetail.CaseNegotiationsData;
import com.web.app.domain.MosDetail.CaseRepliesMosDetail;
import com.web.app.domain.MosDetail.CasesData;
import com.web.app.domain.MosDetail.ParticipatedStatusChangeResultInfo;
import com.web.app.domain.MosDetail.PetitionsContent;
import com.web.app.domain.MosDetail.RelationsContent;
import com.web.app.domain.MosDetail.UpdShowTuritorParameter;
import com.web.app.domain.MosDetail.Withdrawal;
import com.web.app.domain.MosDetail.WithdrawalReturn;
import com.web.app.domain.constants.Constants;
import com.web.app.domain.constants.MessageConstants;
import com.web.app.mapper.AddMessagesMapper;
import com.web.app.mapper.ApplyWithdrawMapper;
import com.web.app.mapper.GetCaseClaimrepliesMosDetailMapper;
import com.web.app.mapper.GetCaseInfoMapper;
import com.web.app.mapper.GetCaseMediationsDataMapper;
import com.web.app.mapper.GetCaseNegotiationsDataMapper;
import com.web.app.mapper.GetCaseRelationsMapper;
import com.web.app.mapper.GetCaseRepliesMosDetailMapper;
import com.web.app.mapper.GetPetitionsContentMapper;
import com.web.app.mapper.GetRelationsContentMapper;
import com.web.app.mapper.UpdCasesStatusMapper;
import com.web.app.mapper.UpdMediatorHistoriesResignMapper;
import com.web.app.mapper.UpdShowTuritorMapper;
import com.web.app.service.CommonService;
import com.web.app.service.MosDetailService;
import com.web.app.service.UtilService;

/**
 * S04_申立て概要画面
 * Service層実装クラス
 * MosDetailServiceImpl
 * 
 * @author DUC 張明慧 耿浩哲 張万超 王亞テイ 朱暁芳 楊バイバイ
 * @since 2024/04/22
 * @version 1.0
 */
@Service
public class MosDetailServiceImpl implements MosDetailService {

    private static final Logger log = LogManager.getLogger(MosDetailServiceImpl.class);
    @Autowired
    private ApplyWithdrawMapper applyWithdrawMapper;

    @Autowired
    private GetPetitionsContentMapper getPetitionsContentMapper;

    @Autowired
    private AddMessagesMapper addMessagesMapper;

    @Autowired
    private GetCaseRelationsMapper getCaseRelationsMapper;

    @Autowired
    private GetRelationsContentMapper getRelationsContentMapper;

    @Autowired
    private UpdCasesStatusMapper updCasesStatusMapper;

    @Autowired
    private CommonService commonService;

    @Autowired
    private UtilService utilService;
    @Autowired
    private GetCaseRepliesMosDetailMapper getCaseRepliesMosDetailMapper;
    @Autowired
    private GetCaseClaimrepliesMosDetailMapper getCaseClaimrepliesMosDetailMapper;

    @Autowired
    private GetCaseInfoMapper getCaseInfoMapper;

    @Autowired
    private UpdShowTuritorMapper updShowTuritorMapper;

    @Autowired
    private GetCaseNegotiationsDataMapper getCaseNegotiationsDataMapper;

    @Autowired
    private GetCaseMediationsDataMapper getCaseMediationsDataMapper;

    @Autowired
    private UpdMediatorHistoriesResignMapper updMediatorHistoriesResign;

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
        System.out.println("2222222" + caseRelations);

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

        if (caseRelations.getTraderUserEmail() != null) {
            OdrUsers traderUser = getRelationsContentMapper
                    .RelationsContentListDataSearch(caseRelations.getTraderUserEmail());
            if (traderUser != null) {
                // 相手方氏名
                relationsContent.setTraderUserName(traderUser.getFirstName() + " " + traderUser.getLastName());
                // 相手方氏名（カナ）
                relationsContent
                        .setTraderUserkana(traderUser.getFirstName_kana() + " " + traderUser.getLastName_kana());
                // 相手方所属会社
                relationsContent.setTraderUsercompanyName(traderUser.getCompanyName());
                // 相手方メールアドレス
                relationsContent.setTraderUserEmail(caseRelations.getTraderUserEmail());
            } else {
                // 取得なしの場合
                relationsContent.setTraderUserFlag(1);
            }
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

        if (caseRelations.getMediatorUserEmail() != null) {
            OdrUsers users = getRelationsContentMapper
                    .RelationsContentListDataSearch(caseRelations.getMediatorUserEmail());
            System.out.println("11111111111111111" + users);
            if (users != null) {
                // 調停人氏名
                relationsContent.setMediatorUserName(users.getFirstName() + " " + users.getLastName());
                // 調停人氏名（カナ）
                relationsContent
                        .setMediatorUserkana(users.getFirstName_kana() + " " + users.getLastName_kana());
                // 調停人所属会社
                relationsContent.setMediatorUsercompanyName(users.getCompanyName());
                // 調停人メールアドレス
                relationsContent.setMediatorUserEmail(caseRelations.getMediatorUserEmail());
            } else {
                // 取得なしの場合
                relationsContent.setMediatorUserFlag(1);
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

        // セッション情報のCaseId対応な申立人・相手方・代理人のuserId
        // List<String> result = addMessagesMapper.usersId(messageGroupId, platformId,
        // uid);
        // 関係者メアド取得
        CaseRelations caseRelations = addMessagesMapper.getCaseRelatedUserInfo(caseId, platformId);
        if (caseRelations == null) {
            return 0;
        }
        // Emailリスト
        List<String> beRecipientEmail = new ArrayList<String>();
        // userIdリスト
        List<String> userIdList = new ArrayList<String>();
        // 申立人
        beRecipientEmail.add(caseRelations.getPetitionUserInfo_Email());
        // 申立代理人
        beRecipientEmail.add(caseRelations.getAgent1_Email());
        beRecipientEmail.add(caseRelations.getAgent2_Email());
        beRecipientEmail.add(caseRelations.getAgent3_Email());
        beRecipientEmail.add(caseRelations.getAgent4_Email());
        beRecipientEmail.add(caseRelations.getAgent5_Email());
        // 相手方
        beRecipientEmail.add(caseRelations.getTraderUserEmail());
        // 相手代理人
        beRecipientEmail.add(caseRelations.getTraderAgent1_UserEmail());
        beRecipientEmail.add(caseRelations.getTraderAgent2_UserEmail());
        beRecipientEmail.add(caseRelations.getTraderAgent3_UserEmail());
        beRecipientEmail.add(caseRelations.getTraderAgent4_UserEmail());
        beRecipientEmail.add(caseRelations.getTraderAgent5_UserEmail());
        for (String item : beRecipientEmail) {
            if (item != null) {
                // ユーザ情報取得API
                OdrUsers odrUsers = utilService.GetOdrUsersByUidOrEmail(null, item, null);
                if (odrUsers != null) {
                    userIdList.add(odrUsers.getUid());
                }
            }
        }

        // GUID呼び出し
        String id = utilService.GetGuid();
        // 「メッセージ」新規登録
        int insertMessageNum = addMessagesMapper.messagesInsert(caseId, uid, id);
        // 「ユーザメッセージ」新規登録
        List<UsersMessages> usersMessagesList = new ArrayList<>();

        for (String item : userIdList) {
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

                try {
                    withdrawalReturn.setCaseRelations(getCaseRelations(uid));
                } catch (Exception e) {
                    log.error(e);
                }

                // 上記APIから返された項目を戻り項目として画面へ返す
                return withdrawalReturn;
            }
        }

        // 上記取得したCaseStageが0（回答）以外の場合、以下の処理を実行
        // 正常に更新の場合、更新済Flgに1（更新不可）を設定し、画面へ返す
        withdrawalReturn.setUpdateFlag(Constants.MOSDETAIL_UPDATEFLAG_1);
        return withdrawalReturn;
    }

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
     * 関係者メアド取得ControllerAPI
     *
     * @param caseId 案件ID
     * @return 案件別個人情報リレーション
     * @throws Exception エラーの説明内容
     */
    @Override
    public CaseRelations getCaseRelations(String caseId) throws Exception {
        return getCaseRelationsMapper.getCaseRelations(caseId);
    }

    /**
     * 回答の内容取得
     * API_GetCaseRepliesMosDetail
     * 
     * @param caseId 渡し項目.CaseId
     * @return caseRepliesMosDetail API「回答の内容取得」を呼び出すData
     */
    @Override
    public CaseRepliesMosDetail getCaseRepliesMosDetail(String caseId) {

        CaseRepliesMosDetail caseRepliesMosDetail = new CaseRepliesMosDetail();

        // 下書き保存データを取得 個数
        int dataCnt = getCaseRepliesMosDetailMapper.selectCasereplies(caseId);
        // draftFlgを設定して、画面へ返す。
        int draftFlg;
        if (dataCnt > Constants.CLAIM_REPLIES_CNT_0) {
            draftFlg = Constants.REPLIES_DRAFT_FLG_1;
        } else {
            draftFlg = Constants.REPLIES_DRAFT_FLG_0;
        }

        // 回答・反訴の内容の取得
        if (dataCnt == Constants.CLAIM_REPLIES_CNT_0) {
            caseRepliesMosDetail = getCaseRepliesMosDetailMapper.getCaserepliesAnswerContent(caseId);
        }

        caseRepliesMosDetail.setDraftFlg(draftFlg);

        // 添付資料の取得
        if (caseRepliesMosDetail != null) {
            List<Files> files = getCaseRepliesMosDetailMapper.getFiles(caseId);
            // 添付資料リストの設定
            caseRepliesMosDetail.setFile(files);
        }

        return caseRepliesMosDetail;
    }

    /**
     * 反訴への回答取得
     * API_GetCaseClaimrepliesMosDetail
     * 
     * @param caseId 渡し項目.CaseId
     * @return caseClaimrepliesMosDetail API「反訴への回答取得」を呼び出すData
     */
    @Override
    public CaseClaimrepliesMosDetail getCaseClaimrepliesMosDetail(String caseId) {

        CaseClaimrepliesMosDetail caseClaimrepliesMosDetail = new CaseClaimrepliesMosDetail();

        // 下書き保存データを取得 個数
        int claimrepliesCnt = getCaseClaimrepliesMosDetailMapper.selectCaseClaimreplies(caseId);
        // claimrepliesDraftFlgを設定して、画面へ返す。
        int claimrepliesDraftFlg;
        if (claimrepliesCnt > Constants.CLAIM_REPLIES_CNT_0) {
            claimrepliesDraftFlg = Constants.CLAIM_REPLIES_DRAFT_FLG_1;
        } else {
            claimrepliesDraftFlg = Constants.CLAIM_REPLIES_DRAFT_FLG_0;
        }

        // 回答・反訴の内容の取得
        if (claimrepliesCnt == Constants.CLAIM_REPLIES_CNT_0) {
            caseClaimrepliesMosDetail = getCaseClaimrepliesMosDetailMapper.getCaseClaimrepliesMosDetailContent(caseId);
        }

        caseClaimrepliesMosDetail.setClaimrepliesDraftFlg(claimrepliesDraftFlg);

        // 添付資料の取得
        if (caseClaimrepliesMosDetail != null) {
            List<Files> files = getCaseClaimrepliesMosDetailMapper.getFiles(caseId);
            // 添付資料リストの設定
            caseClaimrepliesMosDetail.setFile(files);
        }

        return caseClaimrepliesMosDetail;
    }

    /**
     * API_案件状態取得
     * 申立て一覧画面より渡されたCaseIdを引数として、DBから該当する案件のステータスを取得する。
     * 
     * @param caseId     渡し項目.CaseId
     * @param platformId セッション.PlatformId
     * @param userId     セッション.ユーザID
     * @return caseInfo API「案件状態取得」を呼び出すData
     */
    @Override
    public CaseInfo GetCaseInfo(String caseId, String platformId, String userId) {
        CaseInfo caseInfo = new CaseInfo();
        // 該当案件のステータスを取得
        CasesData casesData = getCaseInfoMapper.getCasesData(caseId);

        // 案件内容の設定
        if (casesData != null) {
            caseInfo = setCaseInfoCaseStatus(casesData);
        } else {
            // （網羅外ステータス）を設定する
            caseInfo.setCaseStatus(Constants.S9A9B9C9);
        }

        // 利用モジュール状況取得
        MasterPlatforms masterPlatforms = getCaseInfoMapper.getPhases(platformId);
        // 利用モジュール状況の設定
        if (masterPlatforms != null) {
            int moudleFlg = setCaseInfoMoudleFlg(masterPlatforms);
            caseInfo.setMoudleFlg(moudleFlg);
        }

        // チュートリアル表示制御取得
        OdrUsers odrUsers = getCaseInfoMapper.getShowTuritor(userId, platformId);
        if (odrUsers != null) {
            // チュートリアル表示（申立）
            int showTuritor1 = odrUsers.getShowTuritor1();
            // チュートリアル表示（回答）
            int showTuritor2 = odrUsers.getShowTuritor2();
            // チュートリアル表示（調停）
            int showTuritor3 = odrUsers.getShowTuritor3();
            caseInfo.setShowTuritor1(showTuritor1);
            caseInfo.setShowTuritor2(showTuritor2);
            caseInfo.setShowTuritor3(showTuritor3);
        } else {
            // 初期値を設定する 99: チュートリアルポップアップを表示しない
            caseInfo.setShowTuritor1(99);
            caseInfo.setShowTuritor2(99);
            caseInfo.setShowTuritor3(99);
        }

        return caseInfo;
    }

    /**
     * API_チュートリアル表示制御変更
     * 
     * @param updShowTuritorParameter API_チュートリアル表示制御変更の引数
     * @return int チュートリアル表示制御変更の状況
     */
    @Override
    public int UpdShowTuritor(UpdShowTuritorParameter updShowTuritorParameter) {
        return updShowTuritorMapper.updShowTuritor(updShowTuritorParameter);
    }

    /**
     * API_和解内容取得
     * 渡し項目.CaseIdを引数に、DBよりケースに該当する和解の内容を取得して、画面へ返す。
     * 
     * @param caseId 渡し項目.CaseId
     * @return negotiationsData API「和解内容取得」を呼び出すData
     */
    @Override
    public CaseNegotiationsData GetCaseNegotiationsData(String caseId) {
        CaseNegotiationsData caseNegotiationsData = new CaseNegotiationsData();
        // 和解内容の取得
        CaseNegotiations caseNegotiations = getCaseNegotiationsDataMapper.getCaseNegotiations(caseId);
        // 和解内容の設定
        if (caseNegotiations != null) {
            caseNegotiationsData = setCaseNegotiationsData(caseNegotiations);

            if (caseNegotiations.getCaseId() != null) {
                // 和解案 添付資料の取得
                List<Files> files = getCaseNegotiationsDataMapper.getFiles(caseNegotiations.getCaseId());
                // 添付資料リストの設定
                caseNegotiationsData.setFile(files);
            }
        }

        return caseNegotiationsData;
    }

    /**
     * API_調停内容取得
     * 渡し項目.CaseIdを引数に、DBよりケースに該当する調停の内容を取得して、画面へ返す。
     * 
     * @param caseId 渡し項目.CaseId
     * @return caseMediationsData API「調停内容取得」を呼び出すData
     */
    @Override
    public CaseMediationsData GetCaseMediationsData(String caseId) {
        CaseMediationsData caseMediationsData = new CaseMediationsData();
        // 調停内容の取得
        CaseMediations caseMediations = getCaseMediationsDataMapper.getCaseMediations(caseId);
        // 調停内容の設定
        if (caseMediations != null) {
            caseMediationsData = setCaseMediationsData(caseMediations);

            if (caseMediations.getCaseId() != null) {
                // 調停案 添付資料の取得
                List<Files> files = getCaseMediationsDataMapper.getFiles(caseMediations.getCaseId());
                // 添付資料リストの設定
                caseMediationsData.setFile(files);
            }
        }

        return caseMediationsData;
    }

    /**
     * 案件内容の設定 下記項目を返す
     * CaseTitle タイトル名
     * 案件ステータス
     * ・Stage
     * ・CaseStatus
     * ・DateRequestStatus
     * ・MessageStatus
     * 期日用項目
     * ・ReplyEndDate 回答期限日
     * ・CounterclaimEndDate 反訴の回答期限日
     * ・CancelDate 手続き中止日
     * ・ResolutionDate 解決日時
     * ・MediationEndDate 調停期限日
     * 
     * @param casesData 案件のステータスを取得
     * @return caseInfo API「案件状態取得」を呼び出すData---案件内容の設定
     */
    public CaseInfo setCaseInfoCaseStatus(CasesData casesData) {
        CaseInfo caseInfo = new CaseInfo();
        // タイトル名
        String caseTitle = casesData.getCaseTitle();
        // 案件ステージ
        int caseStage = casesData.getCaseStage();
        // 案件ステータス
        String status = casesData.getCaseStatus();
        // 和解案.ステータス
        String negotiationStatus = casesData.getNegotiationStatus();
        // 案件別個人情報リレーション.調停人
        String mediatorUserEmail = casesData.getMediatorUserEmail();
        // 調停案.ステータス
        String mediationStatus = casesData.getMediationStatus();
        // 交渉期限日変更ステータス
        int negotiationEndDateChangeStatus = casesData.getNegotiationEndDateChangeStatus();
        // 交渉期限日変更回数
        int negotiationEndDateChangeCount = casesData.getNegotiationEndDateChangeCount();
        // 調停期限変更回数
        int mediationEndDateChangeCount = casesData.getMediationEndDateChangeCount();
        // 個別やりとりステータス（申立人↔調停人）
        int groupMessageFlag1 = casesData.getGroupMessageFlag1();
        // 個別やりとりステータス（相手方↔調停人）
        int groupMessageFlag2 = casesData.getGroupMessageFlag2();
        String caseStatus = status;
        int dateRequestStatus = 99;
        int messageStatus = 99;
        int stage = caseStage;
        // 案件ステータスの判定
        if (caseStage == 0) {
            // CaseStatus
            if (status.equals(Constants.WAIT_FOR_JOIN)) {
                caseStatus = Constants.S01;
            } else if (status.equals(Constants.WAIT_FOR_REPLY)) {
                caseStatus = Constants.S02;
            } else {
                caseStatus = Constants.S9A9B9C9;
            }
        } else if (caseStage == 3) {
            // CaseStatus
            if (negotiationStatus == null) {
                caseStatus = Constants.S3B99;
            } else if (negotiationStatus.equals("0")) {
                caseStatus = Constants.S3B0;
            } else if (negotiationStatus.equals("1")) {
                caseStatus = Constants.S3B1;
            } else if (negotiationStatus.equals("2")) {
                caseStatus = Constants.S3B2;
            } else if (negotiationStatus.equals("3")) {
                caseStatus = Constants.S3B3;
            } else if (negotiationStatus.equals("4")) {
                caseStatus = Constants.S3B4;
            } else if (negotiationStatus.equals("5")) {
                caseStatus = Constants.S3B5;
            } else if (negotiationStatus.equals("7")) {
                caseStatus = Constants.S3B7;
            } else if (negotiationStatus.equals("8")) {
                caseStatus = Constants.S3B8;
            } else if (negotiationStatus.equals("9")) {
                caseStatus = Constants.S3B9;
            } else if (negotiationStatus.equals("10")) {
                caseStatus = Constants.S3B10;
            } else if (negotiationStatus.equals("11")) {
                caseStatus = Constants.S3B11;
            } else if (negotiationStatus.equals("12")) {
                caseStatus = Constants.S3B12;
            } else if (negotiationStatus.equals("13")) {
                caseStatus = Constants.S3B13;
            } else if (negotiationStatus.equals("14")) {
                caseStatus = Constants.S3B14;
            } else if (negotiationStatus.equals("15")) {
                caseStatus = Constants.S3B15;
            } else {
                caseStatus = Constants.S9A9B9C9;
            }
            // DateRequestStatus
            if (negotiationEndDateChangeStatus == 0 && negotiationEndDateChangeCount == 0) {
                dateRequestStatus = Constants.S3A0;
            } else if (negotiationEndDateChangeStatus == 1) {
                dateRequestStatus = Constants.S3A1;
            } else if (negotiationEndDateChangeStatus == 2) {
                dateRequestStatus = Constants.S3A2;
            } else if (negotiationEndDateChangeStatus == 0 && negotiationEndDateChangeCount == 1) {
                dateRequestStatus = Constants.S3A3;
            }
        } else if (caseStage == 6) {
            // CaseStatus
            if (mediatorUserEmail == null) {
                caseStatus = Constants.S61;
            } else {
                caseStatus = Constants.S62;
            }
        } else if (caseStage == 7) {
            // CaseStatus
            if (mediationStatus == null) {
                caseStatus = Constants.S7C99;
            } else if (mediationStatus.equals("0")) {
                caseStatus = Constants.S7C0;
            } else if (mediationStatus.equals("1")) {
                caseStatus = Constants.S7C1;
            } else if (mediationStatus.equals("2")) {
                caseStatus = Constants.S7C2;
            } else if (mediationStatus.equals("3")) {
                caseStatus = Constants.S7C3;
            } else if (mediationStatus.equals("4")) {
                caseStatus = Constants.S7C4;
            } else if (mediationStatus.equals("7")) {
                caseStatus = Constants.S7C5;
            } else if (mediationStatus.equals("8")) {
                caseStatus = Constants.S7C6;
            } else {
                caseStatus = Constants.S9A9B9C9;
            }
            // DateRequestStatus
            if (mediationEndDateChangeCount == 0) {
                dateRequestStatus = Constants.S7A1;
            } else if (mediationEndDateChangeCount == 1) {
                dateRequestStatus = Constants.S7A2;
            }
            // MessageStatus
            if (groupMessageFlag1 == 0 && groupMessageFlag2 == 0) {
                messageStatus = Constants.S7B0;
            } else if (groupMessageFlag1 == 1) {
                messageStatus = Constants.S7B1;
            } else if (groupMessageFlag1 == 3) {
                messageStatus = Constants.S7B2;
            } else if (groupMessageFlag1 == 2) {
                messageStatus = Constants.S7B3;
            } else if (groupMessageFlag2 == 1) {
                messageStatus = Constants.S7B4;
            } else if (groupMessageFlag2 == 3) {
                messageStatus = Constants.S7B5;
            } else if (groupMessageFlag2 == 2) {
                messageStatus = Constants.S7B6;
            }
        }
        // タイトル名の設定
        caseInfo.setCaseTitle(caseTitle);
        // 案件ステータスの設定
        caseInfo.setStage(stage);
        caseInfo.setCaseStatus(caseStatus);
        caseInfo.setDateRequestStatus(dateRequestStatus);
        caseInfo.setMessageStatus(messageStatus);

        // 日付フォーマット変換
        // 回答期限日
        Date replyEndDate = casesData.getReplyEndDate();
        // 反訴の回答期限日
        Date counterclaimEndDate = casesData.getCounterclaimEndDate();
        // 手続き中止日
        Date cancelDate = casesData.getCancelDate();
        // 解決日時
        Date resolutionDate = casesData.getResolutionDate();
        // 交渉期限日
        Date negotiationEndDate = casesData.getNegotiationEndDate();
        // 調停期限日
        Date mediationEndDate = casesData.getMediationEndDate();
        // 期日用項目の設定
        if (replyEndDate != null) {
            caseInfo.setReplyEndDate(stringToStringFormat(dateToString(replyEndDate)));
        }
        if (counterclaimEndDate != null) {
            caseInfo.setCounterclaimEndDate(stringToStringFormat(dateToString(counterclaimEndDate)));
        }
        if (cancelDate != null) {
            caseInfo.setCancelDate(stringToStringFormat(dateToString(cancelDate)));
        }
        if (resolutionDate != null) {
            caseInfo.setResolutionDate(stringToStringFormat(dateToString(resolutionDate)));
        }
        if (negotiationEndDate != null) {
            caseInfo.setNegotiationEndDate(stringToStringFormat(dateToString(negotiationEndDate)));
        }
        if (mediationEndDate != null) {
            caseInfo.setMediationEndDate(stringToStringFormat(dateToString(mediationEndDate)));
        }

        return caseInfo;
    }

    /**
     * モジュール利用状況Flgの設定
     * 
     * @param masterPlatforms 利用モジュール状況取得
     * @return int モジュール利用状況Flg
     */
    public int setCaseInfoMoudleFlg(MasterPlatforms masterPlatforms) {
        // モジュール利用状況Flg
        int moudleFlg = 0;
        // 交渉機能利用有無
        int phaseNegotiation = masterPlatforms.getPhase_negotiation();
        // 調停機能利用有無
        int phaseMediation = masterPlatforms.getPhase_mediation();
        // 反訴機能利用有無
        int phaseReply = masterPlatforms.getPhase_reply();
        // モジュール利用状況Flgの設定
        if (phaseNegotiation == 1 && phaseMediation == 1 && phaseReply == 1) {
            moudleFlg = 1;
        } else if (phaseNegotiation == 1 && phaseMediation == 1 && phaseReply == 0) {
            moudleFlg = 2;
        } else if (phaseNegotiation == 1 && phaseMediation == 0 && phaseReply == 1) {
            moudleFlg = 3;
        } else if (phaseNegotiation == 1 && phaseMediation == 0 && phaseReply == 0) {
            moudleFlg = 4;
        } else if (phaseNegotiation == 0 && phaseMediation == 1 && phaseReply == 1) {
            moudleFlg = 5;
        } else if (phaseNegotiation == 0 && phaseMediation == 1 && phaseReply == 0) {
            moudleFlg = 6;
        } else if (phaseNegotiation == 0 && phaseMediation == 0 && phaseReply == 1) {
            moudleFlg = 7;
        } else if (phaseNegotiation == 0 && phaseMediation == 0 && phaseReply == 0) {
            moudleFlg = 8;
        }
        return moudleFlg;
    }

    /**
     * 和解内容の戻り項目の設定 下記項目を返す
     * ①Overview (概要)
     * ②PayAmount （申し立て支払い金額）
     * ③CounterClaimPayment （反訴支払い金額）
     * ④PaymentEndDate （支払期日）
     * ⑤ShipmentPayType （返送時送料負担区分）
     * ⑥SpecialItem （特記事項）
     * ⑦Status
     * 
     * @param caseNegotiations 和解内容の取得
     * @return caseNegotiationsData API「和解内容取得」を呼び出すData---和解内容の設定
     */
    public CaseNegotiationsData setCaseNegotiationsData(CaseNegotiations caseNegotiations) {
        CaseNegotiationsData caseNegotiationsData = new CaseNegotiationsData();

        // ステータス
        int status = caseNegotiations.getStatus();
        // 希望する解決方法
        String expectResloveTypeValue = caseNegotiations.getExpectResloveTypeValue();
        // その他 内容
        String otherContext = caseNegotiations.getOtherContext();
        // 支払金額
        Double payAmount = caseNegotiations.getPayAmount();
        // 反訴の支払金額
        Double counterClaimPayment = caseNegotiations.getCounterClaimPayment();
        // 支払期日
        String paymentEndDate = caseNegotiations.getPaymentEndDate();
        // 返送時送料負担区分
        int shipmentPayType = caseNegotiations.getShipmentPayType();
        // 特記事項
        String specialItem = caseNegotiations.getSpecialItem();
        // 最終変更日
        String lastModifiedDate = caseNegotiations.getLastModifiedDate();

        // Overview 概要
        String overview = null;
        // 概要を設定
        // OtherContextがNullでない場合
        if (otherContext != null) {
            // Overviewに ExpectResloveTypeValue + '、' + OtherContext を設定
            overview = expectResloveTypeValue + "、" + otherContext;
        }
        // OtherContextがNullの場合
        else {
            // Overviewに ExpectResloveTypeValue を設定
            overview = expectResloveTypeValue;
        }
        // 和解内容の戻り項目の設定
        caseNegotiationsData.setStatus(status);
        caseNegotiationsData.setOverview(overview);
        if (payAmount != null) {
            caseNegotiationsData.setPayAmount(doubleToStringFormat(payAmount));
        }
        if (counterClaimPayment != null) {
            caseNegotiationsData.setCounterClaimPayment(doubleToStringFormat(counterClaimPayment));
        }
        if (paymentEndDate != null) {
            caseNegotiationsData.setPaymentEndDate(stringToStringFormat(paymentEndDate));
        }
        caseNegotiationsData.setShipmentPayType(shipmentPayType);
        caseNegotiationsData.setSpecialItem(specialItem);
        caseNegotiationsData.setLastModifiedDate(lastModifiedDate);

        return caseNegotiationsData;
    }

    /**
     * 調停内容の戻り項目の設定 下記項目を返す
     * ①ExpectResloveTypeValue （対応方法）
     * ②PayAmount （申し立て支払い金額）
     * ③CounterClaimPayment （反訴支払い金額）
     * ④PaymentEndDate （支払期日）
     * ⑤ShipmentPayType （返送時送料負担区分）
     * ⑥SpecialItem （特記事項）
     * ⑦Status
     * 
     * @param caseMediations
     * @return caseMediationsData API「調停内容取得」を呼び出すData---調停内容の設定
     */
    public CaseMediationsData setCaseMediationsData(CaseMediations caseMediations) {
        CaseMediationsData caseMediationsData = new CaseMediationsData();

        // ステータス
        int status = caseMediations.getStatus();
        // 希望する解決方法
        String expectResloveTypeValue = caseMediations.getExpectResloveTypeValue();
        // 支払金額
        Double payAmount = caseMediations.getPayAmount();
        // 反訴の支払金額
        Double counterClaimPayment = caseMediations.getCounterClaimPayment();
        // 支払期日
        String paymentEndDate = caseMediations.getPaymentEndDate();
        // 返送時送料負担区分
        int shipmentPayType = caseMediations.getShipmentPayType();
        // 特記事項
        String specialItem = caseMediations.getSpecialItem();
        // 最終変更日
        String lastModifiedDate = caseMediations.getLastModifiedDate();

        // 調停内容の戻り項目の設定
        caseMediationsData.setStatus(status);
        caseMediationsData.setExpectResloveTypeValue(expectResloveTypeValue);
        if (payAmount != null) {
            caseMediationsData.setPayAmount(doubleToStringFormat(payAmount));
        }
        if (counterClaimPayment != null) {
            caseMediationsData.setCounterClaimPayment(doubleToStringFormat(counterClaimPayment));
        }
        if (paymentEndDate != null) {
            caseMediationsData.setPaymentEndDate(stringToStringFormat(paymentEndDate));
        }
        caseMediationsData.setShipmentPayType(shipmentPayType);
        caseMediationsData.setSpecialItem(specialItem);
        caseMediationsData.setLastModifiedDate(lastModifiedDate);

        return caseMediationsData;
    }

    // 共通メソッド
    /**
     * 日付書式設定
     * Date to String (yyyyMMdd)
     * 
     * @param formatDate Date
     * @return String String (yyyyMMdd)
     */
    public static String dateToString(Date formatDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.FORMAT);
        return simpleDateFormat.format(formatDate);
    }

    /**
     * 日付書式設定
     * String (yyyyMMdd) to String (yyyy年MM月dd日)
     * 
     * @param parseString String (yyyyMMdd)
     * @return String String (yyyy年MM月dd日)型日付
     */
    public static String stringToStringFormat(String parseString) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.FORMAT);
            SimpleDateFormat simpleDateMenuFormat = new SimpleDateFormat(Constants.MENU_FORMAT);
            simpleDateFormat.setLenient(false);
            return simpleDateMenuFormat.format(simpleDateFormat.parse(parseString));
        } catch (ParseException e) {
            return parseString;
        }
    }

    /**
     * 金額書式設定
     * double to String ($x,xxx.xx)
     * 
     * @param money 金額
     * @return String String ($x,xxx.xx)
     */
    public static String doubleToStringFormat(Double money) {
        NumberFormat nf = NumberFormat.getInstance();
        String formattedMoney = Constants.STR_DOLLAR + nf.format(money);
        return formattedMoney;
    }

    /**
     * APIで調停人変更履歴の変更を行う。
     * 
     * @param caseId セッション.案件ID
     * @param userId セッション.ユーザID(ログインユーザーID)
     *               reurn 更新件数
     */
    public int updMediatorHistories(String caseId, String userId) {
        MediatorHistories mediatorHistories = new MediatorHistories();
        mediatorHistories.setCaseId(caseId);
        mediatorHistories.setUserId(userId);
        mediatorHistories.setLastModifiedBy(userId);
        int res = updMediatorHistoriesResign.updMediatorHistoriesResign(mediatorHistories);
        return res;
    }
}
