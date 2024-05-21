package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.Entity.OdrUserUtil;
import com.web.app.domain.Entity.OdrUsers;
import com.web.app.service.OdrUserService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
// 声明当前controller需要生成文档，并且指定在文档中的标签为“用户模块”
@Api(tags = "odr用户模块") 
@RestController
@RequestMapping("/odrUser")
public class OdrUserController {

    @Autowired
    OdrUserService odrUserService;

  @ApiOperation("注册")
  @PostMapping("/addUser")
  @SuppressWarnings("rawtypes")
  public Response addUser(OdrUserUtil odrUserUtil) {
    odrUserUtil.setPlatformId("001");
    odrUserUtil.setLanguageId("JP");

    try {
      odrUserService.addUser(odrUserUtil);
      return AjaxResult.success("添加用户成功!");
    } catch (Exception e) {
      AjaxResult.fatal("上传单文件失败!", e);
      return null;
    }
  }

}
