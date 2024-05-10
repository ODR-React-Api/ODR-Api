package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.MedDateExtension.CasesForMediationEndDate;
import com.web.app.service.UpdCasesForMediationEndDateService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

/**
 * 調停期日延長画面_案件情報更新API Controller
 * 
 * @author DUC王エンエン
 * @since 2024/05/09
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "調停期日延長画面")
@RestController
@RequestMapping("/medDateExtension")
@SuppressWarnings("rawtypes")
public class UpdCasesForMediationEndDateController {
     @Autowired
     private UpdCasesForMediationEndDateService updCasesForMediationEndDateService;
     
     /**
     * 
     * サービスメソッドを呼び出して案件を更新し、
     * 更新結果を判断してページに戻る
     * 
     * @param updNegotiatCon 更新に使用するログィンユザと和解案idが含まれています
     * @return Response
     */
     @ApiOperation("案件情報更新")
     @PostMapping("/updCasesForMediationEndDate")
     public Response updCasesForMediationEndDate(@RequestBody CasesForMediationEndDate casesForMediationEndDateResult) {
    try {
      if (updCasesForMediationEndDateService.updCasesForMediationEndDate(casesForMediationEndDateResult) != 0) {
        System.out.println("====================successed================================");
        return AjaxResult.success("案件情報已更新!");
      }
      System.out.println("====================failed================================");
      return AjaxResult.success("案件情報未更新!");
    } catch (Exception e) {
      System.out.println("=====================throw Exception=====================");
      System.out.println(e.toString());
      AjaxResult.fatal("更新失败!", e);
      return null;
    }
  }

}
