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
@Api(tags = "申立て詳細画面_概要")
/**
 * API_参加済状態変更
 * 
 * @author DUC 朱暁芳
 * @since 2024/04/23
 * @version 1.0
 */
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
            AjaxResult.fatal("失敗しました。", e);
            return null;
            // }
        }
    }
}