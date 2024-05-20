package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.MosDetail.ParticipatedStatusChangeResultInfo;
import com.web.app.service.MosDetailService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 申立て詳細画面_概要Controller
 * 
 * @author DUC 朱暁芳
 * @since 2024/04/23
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "申立て詳細画面_概要")
@RestController
@RequestMapping("/MosDetail")
public class MosDetailController {
    @Autowired
    private MosDetailService mosDetailService;

    /**
     * 参加済状態変更
     * 
     * @param 参加表明する渡された引数: 案件ID
     * @return 戻り値は「 参照表明更新済FLG」に返される
     * @throws Exception エラーの説明内容
     */
    @ApiOperation("参加済状態変更")
    @PostMapping("/participation")
    public ParticipatedStatusChangeResultInfo participation(String caseId) {
        try {
            ParticipatedStatusChangeResultInfo participatedInfo = mosDetailService
                    .participatedStatusChangeInfoSearch(caseId);
            return participatedInfo;
        } catch (Exception e) {
            AjaxResult.fatal("失敗しました。", e);
            return null;
        }
    }
}