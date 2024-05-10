package com.web.app.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.PetitionsData;
import com.web.app.service.AnswerLoginService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
// 声明当前controller需要生成文档，并且指定在文档中的标签为“用户模块”
@Api(tags = "申立データ取得2")
@RestController
@RequestMapping("/getData")
public class AnswerLoginController {

    @Autowired
    AnswerLoginService getPetitionsDataService;


    @ApiOperation("申立データ取得API")
    @PostMapping("/getPetitionsData")
    List<PetitionsData> getPetitionsData(String caseId, String plateFormId){
        List<PetitionsData> list;
        list = getPetitionsDataService.getPetitionData(caseId, plateFormId);
        return list;
    }
    
}
