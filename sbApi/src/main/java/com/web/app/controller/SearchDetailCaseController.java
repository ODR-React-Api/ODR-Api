package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.ReturnResult;
import com.web.app.domain.SelectCondition;
import com.web.app.service.SearchDetailCaseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@Api(tags = "检索模块")
@RequestMapping("/search")
public class SearchDetailCaseController {

  @Autowired
  private SearchDetailCaseService searchDetailCaseService;

  @PostMapping("/detail")
  @ApiOperation("検索用ケース詳細取得")
  public ReturnResult searchDetail(@RequestBody SelectCondition searchCase) {
    ReturnResult result = searchDetailCaseService.searchSetailCase(searchCase);

    return result;
  }

}