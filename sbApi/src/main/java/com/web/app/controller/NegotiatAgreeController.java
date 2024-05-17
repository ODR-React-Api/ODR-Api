package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.web.app.tool.AjaxResult;
import com.web.app.service.NegotiatAgreeService;
import com.web.app.domain.Response;
import com.web.app.domain.NegotiatAgree.CaseEstablish;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * S20_和解案合意画面
 * Controller層
 * NegotiatAgreeController
 * 
 * @author DUC 馮淑慧
 * @since 2024/05/09
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "和解案合意画面")
@RestController
@RequestMapping("/negotiatAgree")
public class NegotiatAgreeController {

  @Autowired
  private NegotiatAgreeService negotiatAgreeService;

  /**
   * API_案件成立更新
   * 和解案テーブルから取得した和解案Statusが6の場合、API_案件成立更新をコールし、案件のステータスを「成立」に更新する
   *
   * @param caseEstablish 更新に必要なセッション情報の和解案id、セッション情報の案件Caseとログインユーザ
   * @return num 案件成立更新成功件数
   * @throws Exception 更新失敗時異常
   */
  @SuppressWarnings("rawtypes")
  @ApiOperation("案件成立更新")
  @PostMapping("/UpdCases")
  public Response caseEstablish(@RequestBody CaseEstablish caseEstablish) {
    try {
      // 案件成立更新
      int num = negotiatAgreeService.updCaseEstablish(caseEstablish);
      if (num == 0) {
        return AjaxResult.success("更新0件!");
      }
      return AjaxResult.success("更新成功有り件!", num);
    } catch (Exception e) {
      AjaxResult.fatal("查询失败!", e);
      return null;
    }
  }
}
