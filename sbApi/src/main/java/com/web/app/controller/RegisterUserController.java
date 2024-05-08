package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.UserInfoModel;
import com.web.app.service.RegisterUserService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@CrossOrigin(origins = "*")
@Api(tags = "用户登陆模块") 
@RestController
@RequestMapping("/")
public class RegisterUserController {
  
  @Autowired
  private RegisterUserService userInsertService;

  @SuppressWarnings("rawtypes")
  @PostMapping("RegisterUser")
  @ApiOperation("ユーザ新規登録")
  public Response RegisterUser(UserInfoModel userInfo) {
      try {
      int userInsertRep = userInsertService.UserInsert(userInfo);
      if(userInsertRep != 0){
        return AjaxResult.success("添加用户成功!");
      }
      } catch (Exception e) {
        AjaxResult.fatal("添加用户失败!", e);
        return null;
      }
      AjaxResult.error("添加用户失败!");
      return null;
  }
  


}
