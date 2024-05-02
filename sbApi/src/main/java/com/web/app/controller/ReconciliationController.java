package com.web.app.controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.druid.sql.visitor.functions.Now;
import com.web.app.domain.ReconciliationUser;
import com.web.app.service.ReconciliationService;
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
  public ReconciliationUser reconciliationUpdate(@RequestBody ReconciliationUser reconciliationuser) {
    try {
      ReconciliationUser reconciliationData = ReconciliationSerce.reconciliationUserSearch(reconciliationuser);   
     if (reconciliationData.getStatus() ==2||reconciliationData.getStatus() ==9||reconciliationData.getStatus() ==12||reconciliationData.getStatus() ==15) 
     {
      String HtmlContextYear=reconciliationData.getHtmlContext().replace("#YYYY#",String.valueOf(LocalDate.now().getYear()));
      String HtmlContextMonth=HtmlContextYear.replace("#MM#",String.valueOf(LocalDate.now().getMonth()));
      String HtmlContextDay =HtmlContextMonth.replace("#DD#",String.valueOf(LocalDate.now().getDayOfMonth()));

      String HtmlContextAll = HtmlContextDay;
     }
      // int ReconciliationUpdateStatus = ReconciliationSerce.reconciliationUpdate(reconciliationuser);
        // if(num.getStatus() == 15) {
        //   a.setStatus(111);
        // }else{
        //   a.setStatus(222);
        // }
        return reconciliationData;
    } catch (Exception e) {
      return null;
    }
  }
  }