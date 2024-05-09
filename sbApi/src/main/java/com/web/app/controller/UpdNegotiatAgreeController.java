package com.web.app.controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.web.app.domain.ReconciliationUser;
import com.web.app.domain.util.SendMailRequest;
import com.web.app.service.UpdNegotiatAgreeService;
import com.web.app.service.UtilService;
import com.web.app.domain.ActionHistories;
import com.web.app.domain.Entity.Cases;
import java.util.ArrayList;;
@CrossOrigin(origins = "*")
@Api(tags = "和解案合意更新模块") 
@RestController
@RequestMapping("/reconciliation")
public class UpdNegotiatAgreeController {
  @Autowired
  private UpdNegotiatAgreeService ReconciliationSerce;
  @Autowired
  private UtilService utilService;

  @ApiOperation("「和解案」更新")
  @PostMapping("/reconciliationInsertUpdateDeleteTransactional")
  public int reconciliationUpdate(@RequestBody ReconciliationUser reconciliationuser) {
    try {
      //和解案合意更新
      int ReconciliationUpdateStatus = ReconciliationSerce.reconciliationUpdate(reconciliationuser);
      
      if (ReconciliationUpdateStatus == 1) {
      //「アクロン履歴」新規登録時に必要な属性の設定
      ActionHistories actionHistories =new ActionHistories();
      actionHistories.setCaseId(reconciliationuser.getCaseId());
      actionHistories.setPlatformId(reconciliationuser.getPlatformId());
      actionHistories.setEmail(reconciliationuser.getEmail());
      actionHistories.setUserId(reconciliationuser.getUserId());
      actionHistories.setLastModifiedBy(reconciliationuser.getLastModifiedBy());
      //「アクロン履歴」新規登録
      int ActionHistoriesInsertStatus = ReconciliationSerce.ActionHistoriesInsert(actionHistories);
    
      if (ActionHistoriesInsertStatus==1) {
        //casesテーブルのCaseTitleを検索し、メール送信に設定する
        Cases cases =new Cases();
        cases.setCid(reconciliationuser.getCaseId());
        String CaseTitle = ReconciliationSerce.CaseTitleSearch(cases);
        //メール送信
        SendMailRequest sendMailRequest = new SendMailRequest();
  
          sendMailRequest.setPlatformId("0001");
  
          sendMailRequest.setLanguageId("JP");
  
          sendMailRequest.setTempId("M029");
  
          sendMailRequest.setCaseId(reconciliationuser.getCaseId());
  
          ArrayList<String> recipientEmail = new ArrayList<String>();
  
          recipientEmail.add("jia.wenzhi@trans-cosmos.com.cn");
  
          sendMailRequest.setRecipientEmail(recipientEmail);
  
          ArrayList<String> parameter = new ArrayList<String>();
  
          parameter.add(reconciliationuser.getCaseId());
          parameter.add(CaseTitle);
          parameter.add("https://http://localhost:3000/");
  
          sendMailRequest.setParameter(parameter);
  
          sendMailRequest.setUserId("001");
  
          sendMailRequest.setControlType(1);
  
      utilService.SendMail(sendMailRequest);
      }else{
        return 0;
      }
    } 
    return ReconciliationUpdateStatus;
    } catch (Exception e) {
      return 0;
    }
  }
  }