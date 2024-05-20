package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.MedDateExtension.CasesForMediationEndDate;
import com.web.app.service.MedDateExtensionService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

/**
 * S29_調停期日延長画面
 * Controller层
 * MedDateExtensionController
 * 
 * @author DUC 王 エンエン
 * @since 2024/05/16
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "調停期日延長画面")
@RestController
@RequestMapping("/medDateExtension")
@SuppressWarnings("rawtypes")
public class MedDateExtensionController {
  
     @Autowired
     private MedDateExtensionService medDateExtensionService;
     
     /**
     * API_案件情報更新
     * サービスメソッドを呼び出して案件を更新し、
     * 更新結果を判断してページに戻る
     * 
     * @param casesForMediationEndDate 案件情報更新の引数
     * @return Response 案件情報更新の状況
     */
     @ApiOperation("案件情報更新")
     @PostMapping("/updCasesForMediationEndDate")
     public Response updCasesForMediationEndDate(@RequestBody CasesForMediationEndDate casesForMediationEndDate) {
    try {
      if (medDateExtensionService.updCasesForMediationEndDate(casesForMediationEndDate) != 0) {
        System.out.println("====================successed================================");
        return AjaxResult.success("案件情報が更新されました!");
      }
      System.out.println("====================failed================================");
      return AjaxResult.success("案件情報が更新されませんでした!");
    } catch (Exception e) {
      System.out.println("=====================throw Exception=====================");
      System.out.println(e.toString());
      AjaxResult.fatal("更新に失敗しました!", e);
      return null;
    }
  }
}