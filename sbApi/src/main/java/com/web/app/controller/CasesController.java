package com.web.app.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.domain.Caserelations;
import com.web.app.domain.Casecase;

import com.web.app.service.CaserelationsService;
import com.web.app.service.CasecaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
// import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin(origins = "*")
// 声明当前controller需要生成文档，并且指定在文档中的标签为“用户模块”
@Api(tags = "CASESについて")
@RestController
@RequestMapping("/Cases")
public class CasesController {

  @Autowired
  private CaserelationsService caserelationsService;
  @Autowired
  private CasecaseService casecaseService;

  @ApiOperation("代理人更新処理")
  @PostMapping("/CaserelationsUpdate")

  public String updateCaserelations(@RequestBody Caserelations caserelations) {

    List<String> traderagentuserListAll = new ArrayList<>(caserelations.traderagentuserList);
    // 五つのメールアドレス 必要⇒足りない場合：nullで補足
    while (traderagentuserListAll.size() < 5) {
      traderagentuserListAll.add(null);
    }
    // 補足後のList serviceに追加
    caserelations.setTraderagentuserListAll(traderagentuserListAll);

    int count = caserelationsService.updateCaserelations(caserelations);
    if (count > 0) {
      return count + "件を更新しました(代理人更新)。";
    }
    return "更新がありません(代理人更新)。";

  }

  @ApiOperation("案件状態更新処理")
  @PostMapping("/Case")

  public String updateCasecase(@RequestBody Casecase casecase) {

    List<String> traderagentuserListAll = new ArrayList<>(casecase.traderagentuserList);
    // 五つのメールアドレス 必要⇒足りない場合：nullで補足
    while (traderagentuserListAll.size() < 5) {
      traderagentuserListAll.add(null);
    }
    
    if(casecase.counterclaimFlag == 1 ){
      casecase.setCaseStage(2);
      casecase.setCaseStatus("0200");
      casecase.setCounterclaimFlag(1);
      casecase.setCounterclaimStartDate(casecase.newDate);
      casecase.setCounterclaimEndDate(casecase.newDate);
    }
    else if(casecase.counterclaimFlag == 0 && casecase.phaseNegotiation == true){
      casecase.setCaseStage(3);
      casecase.setCaseStatus("0300");
    }
    else if(casecase.counterclaimFlag == 0 && (casecase.phaseReply == true || casecase.phaseReply == false) 
         && casecase.phaseNegotiation == true){
      casecase.setNegotiationStartDate(casecase.newDate);
      casecase.setNegotiationEndDate(casecase.newDate);
      casecase.setNegotiationEndDateChangeStatus("0");
      casecase.setNegotiationEndDateChangeCount("0");
    }
    // 補足後のList serviceに追加
    casecase.setTraderagentuserListAll(traderagentuserListAll);
    int count = casecaseService.updateCasecase(casecase);
    if (count > 0) {
      return count + "件を更新しました(案件状態更新)。";
    }
    return "更新がありません(案件状態更新)。";

  }
}
