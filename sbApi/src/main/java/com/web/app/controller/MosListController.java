package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.MosList.CaseIdListInfo;
import com.web.app.domain.MosList.ReturnResult;
import com.web.app.service.MosListService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * API_検索用ケース詳細取得
 * 
 * @author DUC 朱暁芳
 * @since 2024/04/17
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "ケース詳細模块")
@RestController
@RequestMapping("/MosList")
public class MosListController {
    @Autowired
    private MosListService mosListService;

    @ApiOperation("ケース詳細取得API")
    @PostMapping("/getCaseDetailnfo")
    public ReturnResult getCaseDetailnfo(@RequestBody CaseIdListInfo caseListInfo) {
        try {
            ReturnResult caseDetail = mosListService.CaseDetailCasesInfoSearch(caseListInfo);
            return caseDetail;
        } catch (Exception e) {
            AjaxResult.fatal("失敗しました。", e);
            return null;
        }
    }
}
