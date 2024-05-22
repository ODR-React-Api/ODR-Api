package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.Entity.OdrUserUtil;
import com.web.app.service.OdrUserService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

    /**
     *
     * @author lixiaoyue
     * @since 2024/05/020
     * @version 1.0
     */
@CrossOrigin(origins = "*")
// 声明当前controller需要生成文档，并且指定在文档中的标签为“用户模块”
@Api(tags = "odr用户模块") 
@RestController
@RequestMapping("/odrUser")
public class OdrUserController {
  
  private static final long serialVersionUID = 1L;

  @Autowired
  OdrUserService odrUserService;

    /**
     *
     * @param OdrUserUtil类包含OdrUser中的部分参数
     * @return 返回是否成功信息
     * @throws 
     */
  @ApiOperation("注册")
  @PostMapping("/addUser")
  @SuppressWarnings("rawtypes")
  public Response addUser(OdrUserUtil odrUserUtil) {
    try {
      odrUserService.addUser(odrUserUtil);
      return AjaxResult.success("添加用户成功!");
    } catch (Exception e) {
      AjaxResult.fatal("上传单文件失败!", e);
      return null;
    }
  }

}
