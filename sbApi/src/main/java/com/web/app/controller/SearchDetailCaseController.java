package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.ReturnResult;
import com.web.app.domain.SelectCondition;
import com.web.app.service.SearchDetailCaseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * API「 検索用一覧取得」より渡された引数によって、DBからケース詳細を取得する。
 * 
 * @author DUC 張万超
 * @since 2024/4/22
 * @version 1.0
 */

@CrossOrigin(origins = "*")
@RestController
@Api(tags = "检索模块")
@RequestMapping("/search")
public class SearchDetailCaseController {

    @Autowired
    private SearchDetailCaseService searchDetailCaseService;

    /**
     * API「 検索用一覧取得」より渡された引数によって、DBからケース詳細を取得する
     *
     * @param searchCase API「 検索用一覧取得」より渡された引数
     * @return case详细情报
     */

    @PostMapping("/detail")
    @ApiOperation("検索用ケース詳細取得")
    public ReturnResult searchDetail(@RequestBody SelectCondition searchCase) {
        // 詳細caseを呼び出してサービスを取得する
        ReturnResult result = searchDetailCaseService.searchSetailCase(searchCase);

        return result;
    }

}