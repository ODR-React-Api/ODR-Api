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

        CaseRelations caseRelations = caseRelationsMapper.RelationsListDataSearch(caseId);

        OdrUsers petitionUser = relationsContentMapper
                .RelationsContentListDataSearch(caseRelations.getPetitionUserInfo_Email());

        RelationsContent relationsContent = new RelationsContent();

        relationsContent.setPetitionUserName(petitionUser.getFirstName() + "　" + petitionUser.getLastName());

        relationsContent.setPetitionUserkana(petitionUser.getFirstName_kana() + "　" + petitionUser.getLastName_kana());

        relationsContent.setPetitionUsercompanyName(petitionUser.getCompanyName());

        relationsContent.setPetitionUserEmail(caseRelations.getPetitionUserInfo_Email());

        if (caseRelations.getAgent1_Email() != null) {
            OdrUsers users = relationsContentMapper.RelationsContentListDataSearch(caseRelations.getAgent1_Email());

            relationsContent.setAgent1Name(users.getFirstName() + "　" + users.getLastName());

            relationsContent.setAgent1kana(users.getFirstName_kana() + "　" + users.getLastName_kana());

            relationsContent.setAgent1Email(caseRelations.getAgent1_Email());

        } else {
            relationsContent.setAgent1Flag(1);
        }

        if (caseRelations.getAgent2_Email() != null) {
            OdrUsers users = relationsContentMapper.RelationsContentListDataSearch(caseRelations.getAgent2_Email());

            relationsContent.setAgent2Name(users.getFirstName() + "　" + users.getLastName());

            relationsContent.setAgent2kana(users.getFirstName_kana() + "　" + users.getLastName_kana());

            relationsContent.setAgent2Email(caseRelations.getAgent2_Email());

        } else {
            relationsContent.setAgent2Flag(1);
        }

        if (caseRelations.getAgent3_Email() != null) {
            OdrUsers users = relationsContentMapper.RelationsContentListDataSearch(caseRelations.getAgent3_Email());

            relationsContent.setAgent3Name(users.getFirstName() + "　" + users.getLastName());

            relationsContent.setAgent3kana(users.getFirstName_kana() + "　" + users.getLastName_kana());

            relationsContent.setAgent3Email(caseRelations.getAgent3_Email());

        } else {
            relationsContent.setAgent3Flag(1);
        }

        if (caseRelations.getAgent4_Email() != null) {
            OdrUsers users = relationsContentMapper.RelationsContentListDataSearch(caseRelations.getAgent4_Email());

            relationsContent.setAgent4Name(users.getFirstName() + "　" + users.getLastName());

            relationsContent.setAgent4kana(users.getFirstName_kana() + "　" + users.getLastName_kana());

            relationsContent.setAgent4Email(caseRelations.getAgent4_Email());

        } else {
            relationsContent.setAgent4Flag(1);
        }

        if (caseRelations.getAgent5_Email() != null) {
            OdrUsers users = relationsContentMapper.RelationsContentListDataSearch(caseRelations.getAgent5_Email());

            relationsContent.setAgent5Name(users.getFirstName() + "　" + users.getLastName());

            relationsContent.setAgent5kana(users.getFirstName_kana() + "　" + users.getLastName_kana());

            relationsContent.setAgent5Email(caseRelations.getAgent5_Email());

        } else {
            relationsContent.setAgent5Flag(1);
        }

        OdrUsers traderUser = relationsContentMapper.RelationsContentListDataSearch(caseRelations.getTraderUserEmail());

        relationsContent.setTraderUserName(traderUser.getFirstName() + "　" + traderUser.getLastName());

        relationsContent.setTraderUserkana(traderUser.getFirstName_kana() + "　" + traderUser.getLastName_kana());

        relationsContent.setTraderUsercompanyName(traderUser.getCompanyName());

        relationsContent.setTraderUserEmail(caseRelations.getTraderUserEmail());

        if (caseRelations.getTraderAgent1_UserEmail() != null) {
            OdrUsers users = relationsContentMapper
                    .RelationsContentListDataSearch(caseRelations.getTraderAgent1_UserEmail());

            relationsContent.setTrader1Name(users.getFirstName() + "　" + users.getLastName());

            relationsContent.setTrader1kana(users.getFirstName_kana() + "　" + users.getLastName_kana());

            relationsContent.setTrader1Email(caseRelations.getTraderAgent1_UserEmail());

        } else {
            relationsContent.setTrader1Flag(1);
        }

        if (caseRelations.getTraderAgent2_UserEmail() != null) {
            OdrUsers users = relationsContentMapper
                    .RelationsContentListDataSearch(caseRelations.getTraderAgent2_UserEmail());

            relationsContent.setTrader2Name(users.getFirstName() + "　" + users.getLastName());

            relationsContent.setTrader2kana(users.getFirstName_kana() + "　" + users.getLastName_kana());

            relationsContent.setTrader2Email(caseRelations.getTraderAgent2_UserEmail());

        } else {
            relationsContent.setTrader2Flag(1);
        }

        if (caseRelations.getTraderAgent3_UserEmail() != null) {
            OdrUsers users = relationsContentMapper
                    .RelationsContentListDataSearch(caseRelations.getTraderAgent3_UserEmail());

            relationsContent.setTrader3Name(users.getFirstName() + "　" + users.getLastName());

            relationsContent.setTrader3kana(users.getFirstName_kana() + "　" + users.getLastName_kana());

            relationsContent.setTrader3Email(caseRelations.getTraderAgent3_UserEmail());

        } else {
            relationsContent.setTrader3Flag(1);
        }

        if (caseRelations.getTraderAgent4_UserEmail() != null) {
            OdrUsers users = relationsContentMapper
                    .RelationsContentListDataSearch(caseRelations.getTraderAgent4_UserEmail());

            relationsContent.setTrader4Name(users.getFirstName() + "　" + users.getLastName());

            relationsContent.setTrader4kana(users.getFirstName_kana() + "　" + users.getLastName_kana());

            relationsContent.setTrader4Email(caseRelations.getTraderAgent4_UserEmail());

        } else {
            relationsContent.setTrader4Flag(1);
        }

        if (caseRelations.getTraderAgent5_UserEmail() != null) {
            OdrUsers users = relationsContentMapper
                    .RelationsContentListDataSearch(caseRelations.getTraderAgent5_UserEmail());

            relationsContent.setTrader5Name(users.getFirstName() + "　" + users.getLastName());

            relationsContent.setTrader5kana(users.getFirstName_kana() + "　" + users.getLastName_kana());

            relationsContent.setTrader5Email(caseRelations.getTraderAgent5_UserEmail());

        } else {
            relationsContent.setTrader5Flag(1);
        }
        return relationsContent;
    }

    /**
     * 調停人退出メッセージ登録
     *
     * @param caseId フロントエンド転送
     * @param uid フロントエンド転送
     * @param platformId フロントエンド転送
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
            List<String> result = mediatorHistoriesMapper.usersId(messageGroupId, platformId, uid);

            int updateNum = mediatorHistoriesMapper.mediatorHistoriesUpdate(caseId, uid);

            String id = utilService.GetGuid();
            int insertMessageNum = mediatorHistoriesMapper.messagesInsert(caseId, uid, id);
            System.out.println("insertMessageNum:" + insertMessageNum);

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
