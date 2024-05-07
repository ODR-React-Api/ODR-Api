package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.web.app.domain.Response;
import com.web.app.domain.UpdNegotiatCon;
import com.web.app.service.UpdNegotiatConService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin(origins = "*")
@Api(tags = "和解案確認更新")
@RestController
@RequestMapping("/updNegotiatCon")
@SuppressWarnings("rawtypes")
public class UpdNegotiatConController {

  @Autowired
  private UpdNegotiatConService updNegotiatConService;

  @PostMapping("/updNegotiatCon")
  public Response updNegotiatCon(@RequestBody UpdNegotiatCon updNegotiatCon) {
    try {
      if (updNegotiatConService.updateNegotiatData(updNegotiatCon) != 0) {
        System.out.println("====================successed================================");
        return AjaxResult.success("和解案已更新!");
      }
      System.out.println("====================failed================================");
      return AjaxResult.success("和解案未更新!");
    } catch (Exception e) {
      System.out.println("=====================throw Exception=====================");
      System.out.println(e.toString());
      AjaxResult.fatal("更新失败!", e);
      return null;
    }
  }
}