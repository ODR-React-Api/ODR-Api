package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.ReturnResult;
import com.web.app.service.QueryDetailCaseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@Api(tags = "检索模块")
@RequestMapping("/query")
public class QueryDetailCaseController {

    @Autowired
    private QueryDetailCaseService queryDetailCaseService;

    @PostMapping("/queryDetailCase")
    @ApiOperation("曖昧検索用ケース詳細取得")
    public ReturnResult querydetailCase(String caseId, String petitionUserId, int positionFlag, String queryString) {
        ReturnResult returnResult = queryDetailCaseService.getQueryDetailCase(caseId, petitionUserId, positionFlag,
                queryString);
        return returnResult;
    }
}
