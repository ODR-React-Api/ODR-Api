package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.ParticipatedStatusChangeResultInfo;
import com.web.app.service.ParticipatedStatusChangeService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
// 声明当前controller需要生成文档，并且指定在文档中的标签为“用户模块”
@Api(tags = "申立て詳細画面_概要")
@RestController
public class ParticipatedStatusChangeController {
    // String caseId = "0001";
    @Autowired
    private ParticipatedStatusChangeService participatedStatusChangeService;

    @ApiOperation("参加済状態変更")
    @PostMapping("/participation")
    public ParticipatedStatusChangeResultInfo participation(String caseId) {
        try {
            ParticipatedStatusChangeResultInfo participatedInfo = participatedStatusChangeService
                    .ParticipatedStatusChangeInfoSearch(caseId);
            return participatedInfo;
        } catch (Exception e) {
            AjaxResult.fatal("查询失败!", e);
            return null;
            // }
        }
    }
}