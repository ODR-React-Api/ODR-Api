package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.InsertRepliesData;
import com.web.app.service.UpdRepliesDataService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
// 声明当前controller需要生成文档，并且指定在文档中的标签为“用户模块”
@Api(tags = "反訴・回答データ更新/登陆")
@RestController
public class UpdRepliesDataController {

    @Autowired
    UpdRepliesDataService updRepliesDataService;

    @ApiOperation("反訴・回答データ更新/登陆")
    @PostMapping("/updRepliesData")
    public String updRepliesData(String platFormId,String caseId,InsertRepliesData insertRepliesData){
        return updRepliesDataService.updateRepliesData(platFormId, caseId,insertRepliesData);

    }
    
}
