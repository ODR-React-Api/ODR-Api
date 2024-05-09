package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.ReturnResult;

import com.web.app.domain.CaseIdListInfo;
import com.web.app.service.CaseDetailService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Api(tags = "ケース詳細模块")
/**
 * API_検索用ケース詳細取得
 * 
 * @author DUC 朱暁芳
 * @since 2024/04/17
 * @version 1.0
 */
@RestController
public class CaseDetailController {
    @Autowired
    private CaseDetailService caseDetailService;

    @ApiOperation("ケース詳細案件cases取得")
    @PostMapping("/caseDetail")
    public ReturnResult caseDetail(@RequestBody CaseIdListInfo caseListInfo) {
        try {
            ReturnResult caseDetail = caseDetailService.CaseDetailCasesInfoSearch(caseListInfo);
            return caseDetail;
        } catch (Exception e) {
            AjaxResult.fatal("失敗しました。", e);
            return null;
        }
    }
}
