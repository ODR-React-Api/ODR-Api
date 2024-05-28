package com.web.app.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.domain.Response;
import com.web.app.domain.constants.Constants;
import com.web.app.service.MedDateExtensionService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
  @SuppressWarnings("unchecked")
  public Response<Integer> updCasesForMediationEndDate(Date mediationEndDate, String cid) {
    System.out.println(mediationEndDate);
    try {
      if (medDateExtensionService.updCasesForMediationEndDate(mediationEndDate, cid) != 0) {
        return AjaxResult.success("案件情報が更新されました!", Constants.RESULT_CODE_SUCCESS);
      }
      return AjaxResult.success("案件情報が更新されませんでした!", Constants.RESULT_CODE_ERROR);
    } catch (Exception e) {
      return AjaxResult.fatal("更新に失敗しました!", e);
    }
  }
}
