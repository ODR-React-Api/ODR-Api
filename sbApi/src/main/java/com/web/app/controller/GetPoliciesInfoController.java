package com.web.app.controller;

import com.web.app.domain.PoliciesInfo;
import com.web.app.service.GetPoliciesInfoService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "情报取得")
@RestController
@RequestMapping("/user")
public class GetPoliciesInfoController {

  @Autowired
  DataSource dataSource;

  @Autowired
  private GetPoliciesInfoService getPoliciesInfoService;

  
  // 利用規約情報取得
  @ApiOperation("利用規約情報取得")
  @PostMapping("/loginUser")
  public List<PoliciesInfo> selectPoliciesInfoList() {
    try {
      List<PoliciesInfo> policiesInfoList = getPoliciesInfoService.getPoliciesInfoList();
      return policiesInfoList;
    } catch (Exception e) {
      AjaxResult.fatal("利用規約情報取得失败!", e);
      return null;
    }
  }
}