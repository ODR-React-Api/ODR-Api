package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.UserInfoModel;
import com.web.app.service.UserInsertService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@CrossOrigin(origins = "*")
@Api(tags = "用户登陆模块") 
@RestController
@RequestMapping("/")
public class UserInsertController {
  
  @Autowired
  private UserInsertService userInsertService;

  @SuppressWarnings("rawtypes")
  @PostMapping("RegisterUser")
  @ApiOperation("添加用户")
  public Response RegisterUser(UserInfoModel userInfo) {
      //TODO: process POST request
      try {
      int userInsertRep = userInsertService.UserInsert(userInfo);
      if(userInsertRep != 0){
        return AjaxResult.success("添加用户成功!");
      }
      } catch (Exception e) {
        // TODO: handle exception
        AjaxResult.fatal("添加用户失败!", e);
        return null;
      }
      AjaxResult.error("添加用户失败!");
      return null;
  }
  


}
