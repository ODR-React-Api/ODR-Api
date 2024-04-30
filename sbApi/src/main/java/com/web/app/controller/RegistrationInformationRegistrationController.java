package com.web.app.controller;
import com.web.app.domain.Response;
import com.web.app.domain.Entity.S09ScreenIntelligence;
import com.web.app.service.RegistrationInformationRegistrationService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// 跨域注解
@CrossOrigin(origins = "*")

// API接口文档识别
@Api(tags = "申立て情報登録") 
// 当前类可接受HTTP请求
@RestController
// 接受请求URL
@RequestMapping("/registrationInformationRegistration")
public class RegistrationInformationRegistrationController {

  // Service接口引入
  @Autowired
  private RegistrationInformationRegistrationService registrationInformationRegistrationService;

  @SuppressWarnings("rawtypes")
  @ApiOperation("申立て情報登録")
  @PostMapping("/registrationInformationRegistration")
  public Response RegistrationInformationRegistration(@RequestBody S09ScreenIntelligence s09ScreenIntelligence) {
    try {
      String uid = null;
      String platformId = null;
      Integer num = registrationInformationRegistrationService.LoginIntelligence(s09ScreenIntelligence, uid,  platformId);
      if (num == 0) {
        return AjaxResult.success("失败");
      }
      return AjaxResult.success("成功");
      // 异常的场合
    } catch (Exception e) {
      // 处理异常的场合
      AjaxResult.fatal("失败!", e);
      return null;
    }
  }
}

