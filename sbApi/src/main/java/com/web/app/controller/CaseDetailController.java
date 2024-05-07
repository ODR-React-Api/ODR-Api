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
// 声明当前controller需要生成文档，并且指定在文档中的标签为“用户模块”
@Api(tags = "ケース詳細模块")
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
            AjaxResult.fatal("查询失败!", e);
            return null;
        }
    }
}
