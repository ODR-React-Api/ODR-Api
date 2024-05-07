package com.web.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
  @ApiOperation("模糊检索")
  public List<ReturnResult> query(@RequestParam("userId") String uid,@RequestParam("queryString") String queryString){
    System.out.println("uid:" + uid);
    System.out.println("queryString:" + queryString);
    
    List<ReturnResult> returnResults = queryService.queryData(uid,queryString);

    return returnResults;
  }
}
