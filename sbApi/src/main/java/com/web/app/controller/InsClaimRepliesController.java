package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.InsClaimReplies;
import com.web.app.domain.Files;
import com.web.app.domain.CaseFileRelations;
import com.web.app.service.InsClaimRepliesService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
// 声明当前controller需要生成文档，并且指定在文档中的标签为“アンケート回答確認画面”
@Api(tags = "反訴への回答データ新規登録")
@RestController
@RequestMapping("/InsClaimReplies")
public class InsClaimRepliesController {
    @Autowired
    private InsClaimRepliesService insClaimRepliesService;

    @ApiOperation("アンケート入力結果新規登録")
    @PostMapping("/insClaimReplies")
    @SuppressWarnings("rawtypes")
    public Response InsClaimRepliesData(@RequestBody InsClaimReplies insClaimReplies) {
        try {
            insClaimRepliesService.InsClaimRepliesData(insClaimReplies);
            return AjaxResult.success("新規登録成功!");
        } catch (Exception e) {
            AjaxResult.fatal("新規登録失败!", e);
            return null;

        }
    }
    @ApiOperation("添付ファイルの新規登録")
    @PostMapping("/files")
    @SuppressWarnings("rawtypes")
    public Response InsClaimRepliesDataFiles(@RequestBody Files files) {
        try {
            insClaimRepliesService.InsClaimRepliesDataFiles(files);
            return AjaxResult.success("新規登録成功!");
        } catch (Exception e) {
            AjaxResult.fatal("新規登録失败!", e);
            return null;

        }
    }

    @ApiOperation("案件添付ファイルリレーション新規登録")
    @PostMapping("/caseFileRelations")
    @SuppressWarnings("rawtypes")
    public Response InsClaimRepliesDataFilesRelations(@RequestBody CaseFileRelations caseFileRelations) {
        try {
            insClaimRepliesService.InsClaimRepliesDataFilesRelations(caseFileRelations);
            return AjaxResult.success("新規登録成功!");
        } catch (Exception e) {
            AjaxResult.fatal("新規登録失败!", e);
            return null;

        }
    }
}

