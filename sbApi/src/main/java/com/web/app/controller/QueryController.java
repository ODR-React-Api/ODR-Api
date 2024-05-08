package com.web.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.ReturnResult;
import com.web.app.service.QueryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@Api(tags = "检索模块")
@RequestMapping("/query")
public class QueryController {

  @Autowired
  private QueryService queryService;
  
  @PostMapping("/detail")
  @ApiOperation("曖昧検索用一覧取得")
  @SuppressWarnings("rawtypes")
  public Response query(@RequestParam("userId") String uid,@RequestParam("queryString") String queryString){

    List<ReturnResult> returnResults = queryService.queryData(uid,queryString);

    if (returnResults != null) {
      return Response.success(returnResults);
    }
    return Response.error("失败");
  }
}