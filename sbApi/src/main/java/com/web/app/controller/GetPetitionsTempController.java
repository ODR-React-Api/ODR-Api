package com.web.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.web.app.tool.AjaxResult;
import com.web.app.service.GetPetitionsTempService;
import com.web.app.domain.GetPetitionTemp;
import com.web.app.domain.SessionInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
// 声明当前controller需要生成文档，并且指定在文档中的标签为“申立て下書き保存データ取得”
@Api(tags = "申立て下書き保存データ取得") 
@RestController
@RequestMapping("/getPetitionsTemp")
public class GetPetitionsTempController {

    @Autowired
    private GetPetitionsTempService getPetitionsTempService;
    
 
    @ApiOperation("申立て下書き保存データ取得List")
    @PostMapping("/petitionsTempList")
    public GetPetitionTemp getPetitionTemp(@RequestBody SessionInfo sessionInfo) {
      try {
        GetPetitionTemp petitionsTempList = getPetitionsTempService.petitionsTempSearch(sessionInfo);
        return petitionsTempList;
      } catch (Exception e) {
        AjaxResult.fatal("查询失败!", e);
        return null;
      }
    }
}
