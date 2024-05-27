package com.web.app.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.Entity.OdrUserUtil;
import com.web.app.domain.constants.MailConstants;
import com.web.app.domain.constants.MessageConstants;
import com.web.app.domain.util.SendMailRequest;
import com.web.app.mapper.RegisterUserMapper;
import com.web.app.service.UserInfoConfirmService;
import com.web.app.service.UtilService;

@Service
public class UserInfoConfirmServiceImpl implements UserInfoConfirmService {
    private static final Logger log = LogManager.getLogger(UserInfoConfirmServiceImpl.class);

    @Autowired
    private RegisterUserMapper registerUserMapper;

    @Autowired
    private UtilService utilService;

  /*
   * 测试事务的使用
   */
  @Transactional(noRollbackFor = { ArithmeticException.class }) // 设置当出现ArithmeticException时，不回滚
  @Override
  @SuppressWarnings("rawtypes")
  public boolean RegisterUserMapper(OdrUserUtil odrUserUtil) {

    odrUserUtil.setUid(utilService.GetGuid());
    odrUserUtil.setPlatformId("0001");
    odrUserUtil.setLanguageId("JP");
    int result = registerUserMapper.RegisterUserMapper(odrUserUtil);

    SendMailRequest sendMailRequest=new SendMailRequest();
    sendMailRequest.setPlatformId(odrUserUtil.getPlatformId());
    sendMailRequest.setLanguageId(odrUserUtil.getLanguageId());

    List<String> emailList = new ArrayList<String>();
    emailList.add(odrUserUtil.getEmail());
    sendMailRequest.setRecipientEmail(emailList);

    List<String> parameter = new ArrayList<String>();
    parameter.add(odrUserUtil.getFirstName()+" "+odrUserUtil.getLastName());
    parameter.add("http://localhost:3000/");
    sendMailRequest.setParameter(parameter);

    sendMailRequest.setUserId(odrUserUtil.getUid());
    sendMailRequest.setControlType(2);
    sendMailRequest.setTempId(MailConstants.MailId_M002);
    boolean bool = utilService.SendMail(sendMailRequest);
    if(bool){
      log.error(MessageConstants.C00002I);
    }
    if (result != 1) {
      return false;
    }
    return true;
  }
    
}
