package com.web.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.web.app.tool.AjaxResult;
import com.web.app.service.InsRepliesTempService;
import com.web.app.domain.Response;
import com.web.app.domain.ScreenInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
// 声明当前controller需要生成文档，并且指定在文档中的标签为“申立て下書きデータ登録”
@Api(tags = "申立て下書きデータ登録") 
@RestController
@RequestMapping("/InsRepliesTemp")
public class InsRepliesTempController {

    @Autowired
    private InsRepliesTempService insRepliesTempService;
    
 
    @SuppressWarnings("rawtypes")
    @ApiOperation("申立て下書きデータ取得")
    @PostMapping("/InsRepliesTempList")
    public Response getPetitionTemp(@RequestBody ScreenInfo screenInfo) {
      try {
        int num = insRepliesTempService.repliesTempIns(screenInfo);
        if(num == 0) {
          return AjaxResult.success("失败!");
        }
        return AjaxResult.success("成功!");
      } catch (Exception e) {
        AjaxResult.fatal("查询失败!", e);
        return null;
      }
    }
}
