package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.Entity.OdrUsers;
import com.web.app.domain.Entity.UsersMessages;
import com.web.app.domain.Entity.CasePetitions;
import com.web.app.domain.Entity.CaseRelations;
import com.web.app.domain.MosDetail.PetitionsContent;
import com.web.app.domain.MosDetail.RelationsContent;
import com.web.app.domain.constants.MailConstants;
import com.web.app.domain.util.SendMailRequest;
import com.web.app.mapper.CaseRelationsMapper;
import com.web.app.mapper.GetPetitionsContentMapper;
import com.web.app.mapper.MediatorResignMapper;
import com.web.app.mapper.GetRelationsContentMapper;
import com.web.app.service.MosDetailService;
import com.web.app.service.UtilService;

/**
 * 申立て詳細画面_概要
 * 
 * @author DUC 王亞テイ
 * @since 2024/04/23
 * @version 1.0
 */

@Service
public class MosDetailServiceImpl implements MosDetailService {

    @Autowired
    private GetPetitionsContentMapper petitionsContentMapper;

    private static final Logger log = LogManager.getLogger(MosDetailServiceImpl.class);

    @Autowired
    private MediatorResignMapper mediatorHistoriesMapper;

    @Autowired
    private CaseRelationsMapper caseRelationsMapper;

    @Autowired
    private GetRelationsContentMapper relationsContentMapper;

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
        CasePetitions casePetitions = petitionsContentMapper.PetitionListDataSearch(caseId);

        petitionsContent.setCasePetitions(casePetitions);

        // 添付資料取得
        petitionsContent.setAttachedFile(petitionsContentMapper.PetitionFileSearch(casePetitions.getCaseId()));

        // 拡張項目取得
        petitionsContent.setExtensionItem(petitionsContentMapper.PetitionExtensionitemSearch(caseId));

        return petitionsContent;
    }

    @Override
    public CaseRelations selectRelationsData(String caseId) {

        CaseRelations caseRelations = caseRelationsMapper.RelationsListDataSearch(caseId);

        return caseRelations;
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
        CaseRelations caseRelations = caseRelationsMapper.RelationsListDataSearch(caseId);

        // メールベースクエリ対応userの名前
        OdrUsers petitionUser = relationsContentMapper
                .RelationsContentListDataSearch(caseRelations.getPetitionUserInfo_Email());

        RelationsContent relationsContent = new RelationsContent();
        // 申立人氏名
        relationsContent.setPetitionUserName(petitionUser.getFirstName() + "　" + petitionUser.getLastName());
        // 申立人氏名（カナ）
        relationsContent.setPetitionUserkana(petitionUser.getFirstName_kana() + "　" + petitionUser.getLastName_kana());
        // 申立人所属会社
        relationsContent.setPetitionUsercompanyName(petitionUser.getCompanyName());
        // 申立人メールアドレス
        relationsContent.setPetitionUserEmail(caseRelations.getPetitionUserInfo_Email());

        if (caseRelations.getAgent1_Email() != null) {
            OdrUsers users = relationsContentMapper.RelationsContentListDataSearch(caseRelations.getAgent1_Email());
            // 代理人1氏名
            relationsContent.setAgent1Name(users.getFirstName() + "　" + users.getLastName());
            // 代理人1氏名（カナ）
            relationsContent.setAgent1kana(users.getFirstName_kana() + "　" + users.getLastName_kana());
            // 代理人1メールアドレス
            relationsContent.setAgent1Email(caseRelations.getAgent1_Email());

        } else {
            // 取得なしの場合
            relationsContent.setAgent1Flag(1);
        }

        if (caseRelations.getAgent2_Email() != null) {
            OdrUsers users = relationsContentMapper.RelationsContentListDataSearch(caseRelations.getAgent2_Email());
            // 代理人2氏名
            relationsContent.setAgent2Name(users.getFirstName() + "　" + users.getLastName());
            // 代理人2氏名（カナ）
            relationsContent.setAgent2kana(users.getFirstName_kana() + "　" + users.getLastName_kana());
            // 代理人3メールアドレス
            relationsContent.setAgent2Email(caseRelations.getAgent2_Email());

        } else {
            // 取得なしの場合
            relationsContent.setAgent2Flag(1);
        }

        if (caseRelations.getAgent3_Email() != null) {
            OdrUsers users = relationsContentMapper.RelationsContentListDataSearch(caseRelations.getAgent3_Email());
            // 代理人3氏名
            relationsContent.setAgent3Name(users.getFirstName() + "　" + users.getLastName());
            // 代理人3氏名（カナ）
            relationsContent.setAgent3kana(users.getFirstName_kana() + "　" + users.getLastName_kana());
            // 代理人3メールアドレス
            relationsContent.setAgent3Email(caseRelations.getAgent3_Email());

        } else {
            // 取得なしの場合
            relationsContent.setAgent3Flag(1);
        }

        if (caseRelations.getAgent4_Email() != null) {
            OdrUsers users = relationsContentMapper.RelationsContentListDataSearch(caseRelations.getAgent4_Email());
            // 代理人4氏名
            relationsContent.setAgent4Name(users.getFirstName() + "　" + users.getLastName());
            // 代理人4氏名（カナ）
            relationsContent.setAgent4kana(users.getFirstName_kana() + "　" + users.getLastName_kana());
            // 代理人4メールアドレス
            relationsContent.setAgent4Email(caseRelations.getAgent4_Email());

        } else {
            // 取得なしの場合
            relationsContent.setAgent4Flag(1);
        }

        if (caseRelations.getAgent5_Email() != null) {
            OdrUsers users = relationsContentMapper.RelationsContentListDataSearch(caseRelations.getAgent5_Email());
            // 代理人5氏名
            relationsContent.setAgent5Name(users.getFirstName() + "　" + users.getLastName());
            // 代理人5氏名（カナ）
            relationsContent.setAgent5kana(users.getFirstName_kana() + "　" + users.getLastName_kana());
            // 代理人5メールアドレス
            relationsContent.setAgent5Email(caseRelations.getAgent5_Email());

        } else {
            // 取得なしの場合
            relationsContent.setAgent5Flag(1);
        }

        OdrUsers traderUser = relationsContentMapper.RelationsContentListDataSearch(caseRelations.getTraderUserEmail());
        // 相手方氏名
        relationsContent.setTraderUserName(traderUser.getFirstName() + "　" + traderUser.getLastName());
        // 相手方氏名（カナ）
        relationsContent.setTraderUserkana(traderUser.getFirstName_kana() + "　" + traderUser.getLastName_kana());
        // 相手方所属会社
        relationsContent.setTraderUsercompanyName(traderUser.getCompanyName());
        // 相手方メールアドレス
        relationsContent.setTraderUserEmail(caseRelations.getTraderUserEmail());

        if (caseRelations.getTraderAgent1_UserEmail() != null) {

            OdrUsers users = relationsContentMapper
                    .RelationsContentListDataSearch(caseRelations.getTraderAgent1_UserEmail());
            // 代理人1氏名
            relationsContent.setTrader1Name(users.getFirstName() + "　" + users.getLastName());
            // 代理人1氏名（カナ）
            relationsContent.setTrader1kana(users.getFirstName_kana() + "　" + users.getLastName_kana());
            // 代理人1メールアドレス
            relationsContent.setTrader1Email(caseRelations.getTraderAgent1_UserEmail());

        } else {
            // 取得なしの場合
            relationsContent.setTrader1Flag(1);
        }

        if (caseRelations.getTraderAgent2_UserEmail() != null) {
            OdrUsers users = relationsContentMapper
                    .RelationsContentListDataSearch(caseRelations.getTraderAgent2_UserEmail());
            // 代理人2氏名
            relationsContent.setTrader2Name(users.getFirstName() + "　" + users.getLastName());
            // 代理人2氏名（カナ）
            relationsContent.setTrader2kana(users.getFirstName_kana() + "　" + users.getLastName_kana());
            // 代理人2メールアドレス
            relationsContent.setTrader2Email(caseRelations.getTraderAgent2_UserEmail());

        } else {
            // 取得なしの場合
            relationsContent.setTrader2Flag(1);
        }

        if (caseRelations.getTraderAgent3_UserEmail() != null) {
            OdrUsers users = relationsContentMapper
                    .RelationsContentListDataSearch(caseRelations.getTraderAgent3_UserEmail());
            // 代理人3氏名
            relationsContent.setTrader3Name(users.getFirstName() + "　" + users.getLastName());
            // 代理人3氏名（カナ）
            relationsContent.setTrader3kana(users.getFirstName_kana() + "　" + users.getLastName_kana());
            // 代理人3メールアドレス
            relationsContent.setTrader3Email(caseRelations.getTraderAgent3_UserEmail());

        } else {
            // 取得なしの場合
            relationsContent.setTrader3Flag(1);
        }

        if (caseRelations.getTraderAgent4_UserEmail() != null) {
            OdrUsers users = relationsContentMapper
                    .RelationsContentListDataSearch(caseRelations.getTraderAgent4_UserEmail());
            // 代理人4氏名
            relationsContent.setTrader4Name(users.getFirstName() + "　" + users.getLastName());
            // 代理人4氏名（カナ）
            relationsContent.setTrader4kana(users.getFirstName_kana() + "　" + users.getLastName_kana());
            // 代理人4メールアドレス
            relationsContent.setTrader4Email(caseRelations.getTraderAgent4_UserEmail());

        } else {
            // 取得なしの場合
            relationsContent.setTrader4Flag(1);
        }

        if (caseRelations.getTraderAgent5_UserEmail() != null) {
            OdrUsers users = relationsContentMapper
                    .RelationsContentListDataSearch(caseRelations.getTraderAgent5_UserEmail());
            // 代理人5氏名
            relationsContent.setTrader5Name(users.getFirstName() + "　" + users.getLastName());
            // 代理人5氏名（カナ）
            relationsContent.setTrader5kana(users.getFirstName_kana() + "　" + users.getLastName_kana());
            // 代理人5メールアドレス
            relationsContent.setTrader5Email(caseRelations.getTraderAgent5_UserEmail());

        } else {
            // 取得なしの場合
            relationsContent.setTrader5Flag(1);
        }
        return relationsContent;
    }

    /**
     * 調停人退出メッセージ登録
     *
     * @param caseId         フロントエンド転送
     * @param uid            フロントエンド転送
     * @param platformId     フロントエンド転送
     * @param messageGroupId フロントエンド転送
     * @return 調停人退出メッセージ登録
     */

    @Override
    public int updateMediatorHistoriesData(String caseId, String uid, String platformId, String messageGroupId) {

        CaseRelations caseRelations = caseRelationsMapper.RelationsListDataSearch(caseId);

        SendMailRequest sendMailRequest = new SendMailRequest();

        sendMailRequest.setPlatformId("0001");

        sendMailRequest.setLanguageId("JP");

        sendMailRequest.setTempId(MailConstants.MailId_M072);

        sendMailRequest.setCaseId(caseId);

        ArrayList<String> recipientEmail = new ArrayList<String>();

        recipientEmail.add(caseRelations.getMediatorUserEmail());

        sendMailRequest.setRecipientEmail(recipientEmail);

        ArrayList<String> parameter = new ArrayList<>();

        parameter.add("http://localhost:3000/");

        sendMailRequest.setParameter(parameter);

        sendMailRequest.setUserId("ODR_Front");

        sendMailRequest.setControlType(2);

        boolean bool = utilService.SendMail(sendMailRequest);

        if (!bool) {
            log.error("通知メールの送信に失敗しました。");
        } else {
            // セッション情報のCaseId対応な申立人・相手方・代理人のuserid
            List<String> result = mediatorHistoriesMapper.usersId(messageGroupId, platformId, uid);
            // 調停人変更履歴の変更
            int updateNum = mediatorHistoriesMapper.mediatorHistoriesUpdate(caseId, uid);
            // GUID呼び出し
            String id = utilService.GetGuid();
            // 「メッセージ」新規登録
            int insertMessageNum = mediatorHistoriesMapper.messagesInsert(caseId, uid, id);
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

            int insertUMessageNum = mediatorHistoriesMapper.usersMessagesInsert(usersMessagesList);

            if (updateNum == 0 || insertMessageNum == 0 || insertUMessageNum == 0) {
                return 0;
            }

        }
        return 1;
    }
}
