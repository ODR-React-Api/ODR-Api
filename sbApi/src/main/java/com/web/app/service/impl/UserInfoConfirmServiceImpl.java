package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.Response;
import com.web.app.domain.Entity.OdrUserUtil;
import com.web.app.domain.constants.MailConstants;
import com.web.app.domain.util.SendMailRequest;
import com.web.app.mapper.RegisterUserMapper;
import com.web.app.service.UserInfoConfirmService;
import com.web.app.service.UtilService;
import com.web.app.tool.AjaxResult;

@Service
public class UserInfoConfirmServiceImpl implements UserInfoConfirmService {
    @Autowired
    RegisterUserMapper registerUserMapper;

    @Autowired
    UtilService utilService;

     /*
   * 测试事务的使用
   */
  @Transactional(noRollbackFor = { ArithmeticException.class }) // 设置当出现ArithmeticException时，不回滚
  @Override
  @SuppressWarnings("rawtypes")
  public Response addUser(OdrUserUtil odrUserUtil) {

    odrUserUtil.setUid(utilService.GetGuid());
    odrUserUtil.setPlatformId("0001");
    odrUserUtil.setLanguageId("JP");
    int result = registerUserMapper.insertUser(odrUserUtil);

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
    System.err.println("邮件发送1");
    boolean bool = utilService.SendMail(sendMailRequest);
    if (result != 1) {
      
      return AjaxResult.error("添加用户失败!");
    }
    System.err.println(bool);
    if(bool != true){
      System.err.println("邮件发送");
      return AjaxResult.error("邮件发送失败!");
    }
    System.err.println("邮件发送2");
    return AjaxResult.error("邮件发送、添加用户成功!");
  }
    
}
