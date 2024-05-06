package com.web.app.controller;

import com.web.app.domain.CasesByCid;
import com.web.app.domain.Response;
import com.web.app.service.CasesByCidService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@Api(tags = "案件数据取得模块")
@RestController
@RequestMapping("/CasesByCid")
public class CasesByCidController {
  @Autowired
  DataSource dataSource;

  @Autowired
  private CasesByCidService casesByCidService;
  @SuppressWarnings("rawtypes")
  @ApiOperation("案件データ取得")
  @GetMapping("/CasesByCid")
  public Response casesByCid(String CaseId,String PlatformId) {

    try {
      List<CasesByCid> userContextList = new ArrayList<CasesByCid>();
      userContextList = casesByCidService.casesByCid(CaseId,PlatformId);
      return AjaxResult.success("请求成功", userContextList);
    } catch (Exception e) {
      AjaxResult.fatal("查询失败!", e);
      return null;
    }
  }
}

