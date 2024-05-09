package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.CaseRelations;
import com.web.app.domain.UsersMessages;
import com.web.app.domain.constants.MailConstants;
import com.web.app.domain.util.SendMailRequest;
import com.web.app.mapper.CaseRelationsMapper;
import com.web.app.mapper.MediatorHistoriesMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.web.app.service.MediatorHistoriesService;
import com.web.app.service.UtilService;

@Service
public class MediatorHistoriesServiceImpl implements MediatorHistoriesService {

    private static final Logger log = LogManager.getLogger(MediatorHistoriesServiceImpl.class);

    @Autowired
    private CaseRelationsMapper caseRelationsMapper;

    @Autowired
    private MediatorHistoriesMapper mediatorHistoriesMapper;

    @Autowired
    private UtilService utilService;

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

        // parameter.add(userInfo.getLastName() + userInfo.getFirstName());
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

            String id = UUID.randomUUID().toString();
            int insertMessageNum = mediatorHistoriesMapper.messagesInsert(caseId, uid, id);
            System.out.println("insertMessageNum:" + insertMessageNum);

            List<UsersMessages> usersMessagesList = new ArrayList<>();

            for (String item : result) {
                UsersMessages usersMessages = new UsersMessages();
                usersMessages.setId(UUID.randomUUID().toString());
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
