package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



import com.web.app.domain.Response;
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

    String id = "00CD736C359346D58BC2113480783EF4";
    Integer status = 1;
    @PostMapping("/updNegotiatCon")
    public Response updNegotiatCon(@RequestBody String negotiationId){
    // try {
    //   System.out.println("后台访问成功");
    //   System.out.println(negotiationId);
    //   return AjaxResult.success("和解案確認更新成功!");
    // } catch (Exception e) {
    //   AjaxResult.fatal("和解案確認更新失败!", e);
    //   return null;
    // }
    try {
      int num = updNegotiatConService.updNegotiat(id,status);
      if(num == 0) {
        return AjaxResult.success("和解案確認更新失败!");
      }
      return AjaxResult.success("和解案確認更新成功!");
    } catch (Exception e) {
      AjaxResult.fatal("和解案確認更新失败!", e);
      return null;
    }
    }

}
