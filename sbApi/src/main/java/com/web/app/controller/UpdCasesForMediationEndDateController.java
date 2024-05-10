package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.CasesForMediationEndDate;
import com.web.app.domain.Response;

import com.web.app.service.UpdCasesForMediationEndDateService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin(origins = "*")
@Api(tags = "調停期日延長画面")
@RestController
@RequestMapping("/medDateExtension")
@SuppressWarnings("rawtypes")
public class UpdCasesForMediationEndDateController {
     @Autowired
     private UpdCasesForMediationEndDateService updCasesForMediationEndDateService;
     
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
