package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.UserInfoModel;
import com.web.app.domain.UserInsertModel;
import com.web.app.domain.constants.MailConstants;
import com.web.app.domain.util.SendMailRequest;
import com.web.app.mapper.UserInsertMapper;
import com.web.app.service.UserInsertService;
import com.web.app.service.UtilService;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Service
public class UserInsertServiceImpl implements UserInsertService {

  private static final Logger log = LogManager.getLogger(UserInsertServiceImpl.class);

  @Autowired
  private UserInsertMapper userInsertMapper;

  @Autowired
  private UtilService utilService;

  @Override
  public int UserInsert(UserInfoModel userInfo) {
    // TODO Auto-generated method stub
    // String uid = userInsertMapper.getMaxUid();

    UserInsertModel userInsert = new UserInsertModel();
    userInsert.setUid(UUID.randomUUID().toString());
    userInsert.setEmail(userInfo.getEmail());
    userInsert.setFirstName(userInfo.getFirstName());
    userInsert.setFirstNameKana(userInfo.getFirstNameKana());
    userInsert.setLastName(userInfo.getLastName());
    userInsert.setLastNameKana(userInfo.getLastNameKana());
    userInsert.setMiddleName(userInfo.getMiddleName());
    userInsert.setMiddleNameKana(userInfo.getMiddleNameKana());
    userInsert.setCompanyName(userInfo.getCompanyName());
    userInsert.setPassword(userInfo.getPassword());
    userInsert.setLastModifiedBy(userInfo.getLastModifiedBy());
    userInsert.setStatus(0);
    userInsert.setMessageFrequency(100);
    userInsert.setTermsConfirmed(1);
    userInsert.setUserType(0);
    userInsert.setDeleteFlag(0);
    userInsert.setPlatformId("0001");
    userInsert.setLanguageId("jp");

    int rertuenData = userInsertMapper.userInsert(userInsert);

    if (rertuenData != 0) {
      SendMailRequest sendMailRequest = new SendMailRequest();

      sendMailRequest.setPlatformId("0001");

      sendMailRequest.setLanguageId("JP");

      sendMailRequest.setTempId(MailConstants.MailId_M002);

      ArrayList<String> recipientEmail = new ArrayList<String>();

      recipientEmail.add(userInsert.getEmail());

      sendMailRequest.setRecipientEmail(recipientEmail);

      sendMailRequest.setUserId(userInsert.getUid());

      boolean bool = utilService.SendMail(sendMailRequest);

      if (!bool) {
        log.error("通知メールの送信に失敗しました。");
      }
    }

    return rertuenData;
  }

}
