package com.web.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.web.app.tool.AjaxResult;
import com.web.app.service.UpdCaseEstablishService;
import com.web.app.domain.CaseEstablish;
import com.web.app.domain.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
// 声明当前controller需要生成文档，并且指定在文档中的标签为“申立て下書きデータ登録”
@Api(tags = "案件成立更新") 
@RestController
@RequestMapping("/UpdCaseEstablish")
public class UpdCaseEstablishController {

    @Autowired
    private UpdCaseEstablishService updCaseEstablishService;
    
 
    @SuppressWarnings("rawtypes")
    @ApiOperation("案件成立更新")
    @PostMapping("/UpdCases")
    public Response getPetitionTemp(@RequestBody CaseEstablish caseEstablish) {
      try {
        int num = updCaseEstablishService.UpdCases(caseEstablish);
        if(num == 0) {
          return AjaxResult.success("更新失败!");
        }
        return AjaxResult.success("更新成功!");
      } catch (Exception e) {
        AjaxResult.fatal("查询失败!", e);
        return null;
      }
    }
}
