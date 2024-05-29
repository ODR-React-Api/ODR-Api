package com.web.app.controller;

import com.web.app.domain.Response;
import com.web.app.domain.MosContentConfirm.S09ScreenIntelligence;
import com.web.app.service.MosContentConfirmService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@Api(tags = "申立て情報登録")
@RestController
@RequestMapping("/registrationInformationRegistration")

/**
 * 申立て情報登録
 * 
 * @author DUC 王魯興
 * @since 2024/05/28
 * @version 1.0
 */
public class MosContentConfirmController {

  @Autowired
  private MosContentConfirmService insPetitionsDataService;

  /**
   * 申立て情報登録
   *
   * @param s09ScreenIntelligence 画面の項目
   * @return
   */
  @SuppressWarnings("rawtypes")
  @ApiOperation("申立て情報登録")
  @PostMapping("/registrationInformationRegistration")
  public Response RegistrationInformationRegistration(@RequestBody S09ScreenIntelligence s09ScreenIntelligence) {
    try {
      Integer num = insPetitionsDataService.LoginIntelligence(s09ScreenIntelligence);
      if (num == 0) {
        return AjaxResult.success("失败");
      }
      return AjaxResult.success("成功");
      // 異常な場合
    } catch (Exception e) {
      // 異常を処置した場合
      AjaxResult.fatal("失败!", e);
      return null;
    }
  }
}