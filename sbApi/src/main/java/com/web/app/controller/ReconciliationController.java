package com.web.app.controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.authenticator.SpnegoAuthenticator.AcceptAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.web.app.domain.ReconciliationUser;
import com.web.app.service.ReconciliationService;
import com.web.app.domain.ActionHistories;
import java.time.LocalDate;;
@CrossOrigin(origins = "*")
@Api(tags = "和解案合意更新模块") 
@RestController
@RequestMapping("/reconciliation")
public class ReconciliationController {
  @Autowired
  private ReconciliationService ReconciliationSerce;

  @ApiOperation("「和解案」更新")
  @PostMapping("/reconciliationInsertUpdateDeleteTransactional")
  public int reconciliationUpdate(@RequestBody ReconciliationUser reconciliationuser) {
    try {
      int ReconciliationUpdateStatus = ReconciliationSerce.reconciliationUpdate(reconciliationuser);
   
      if (ReconciliationUpdateStatus == 1) {
      //caseid赋予
      ActionHistories actionHistories =new ActionHistories();
      actionHistories.setCaseId(reconciliationuser.getCaseId());
      actionHistories.setPlatformId(reconciliationuser.getPlatformId());
      actionHistories.setEmail(reconciliationuser.getEmail());
      actionHistories.setUserId(reconciliationuser.getUserId());
      actionHistories.setLastModifiedBy(reconciliationuser.getLastModifiedBy());
      //履历新规登陆
      int ActionHistoriesInsertStatus = ReconciliationSerce.ActionHistoriesInsert(actionHistories);
      if (ActionHistoriesInsertStatus==1) {
        int a=111;
      }else{
        int a=222;
      }
    }   
    // String HtmlContextAll =new String();
    // String HtmlContext2All =new String();
    // ReconciliationUser reconciliationData = ReconciliationSerce.reconciliationUserSearch(reconciliationuser);   
    //   if (reconciliationData.getStatus() ==2||reconciliationData.getStatus() ==9||reconciliationData.getStatus() ==12||reconciliationData.getStatus() ==15) 
    //  {
    //   //年替换
    //   String HtmlContextYear =new String();
    //   String HtmlContext2Year =new String();
    //    HtmlContextYear=reconciliationData.getHtmlContext().replace("#YYYY#",String.valueOf(LocalDate.now().getYear()));  
    //    HtmlContext2Year=reconciliationData.getHtmlContext2().replace("#YYYY#",String.valueOf(LocalDate.now().getYear()));  
    //   //月替换
    //   String HtmlContextMonth =new String();
    //   String HtmlContext2Month =new String();
    //   if (LocalDate.now().getMonth().getValue()<10) { 
    //        HtmlContextMonth=HtmlContextYear.replace("#MM#","0"+String.valueOf(LocalDate.now().getMonth().getValue()));
    //        HtmlContext2Month=HtmlContext2Year.replace("#MM#","0"+String.valueOf(LocalDate.now().getMonth().getValue()));
    //     }else{
    //       HtmlContextMonth=HtmlContextYear.replace("#MM#",String.valueOf(LocalDate.now().getMonth().getValue()));
    //       HtmlContext2Month=HtmlContext2Year.replace("#MM#",String.valueOf(LocalDate.now().getMonth().getValue()));
    //     }
    //   //日替换
    //   String HtmlContextDay =new String();
    //   String HtmlContext2Day =new String();
    //   if (LocalDate.now().getDayOfMonth()<10) {
    //      HtmlContextDay =HtmlContextMonth.replace("#DD#","0"+String.valueOf(LocalDate.now().getDayOfMonth()));
    //      HtmlContext2Day =HtmlContext2Month.replace("#DD#","0"+String.valueOf(LocalDate.now().getDayOfMonth()));
    //   }else{
    //     HtmlContextDay =HtmlContextMonth.replace("#DD#",String.valueOf(LocalDate.now().getDayOfMonth()));
    //     HtmlContext2Day =HtmlContext2Month.replace("#DD#",String.valueOf(LocalDate.now().getDayOfMonth()));
    //   }
    //   //HtmlContext替换后&HtmlContext2替换后
    //   HtmlContextAll = HtmlContextDay;
    //   HtmlContext2All = HtmlContext2Day;
    //  }else{
    //   HtmlContextAll=reconciliationData.getHtmlContext();
    //   HtmlContext2All=reconciliationData.getHtmlContext();
    //  }
  
    // if(num.getStatus() == 15) {
    //   a.setStatus(111);
    // }else{
    //   a.setStatus(222);
    // }


    return ReconciliationUpdateStatus;
    } catch (Exception e) {
      return 0;
    }
  }
  }