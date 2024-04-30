package com.web.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.web.app.domain.Position;
import com.web.app.domain.ReturnResult;
import com.web.app.service.GetSelectListInfoService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@CrossOrigin(origins = "*")
// 声明当前controller需要生成文档，并且指定在文档中的标签为“検索用一覧取得”
@Api(tags = "検索用一覧取得") 
@RestController
@RequestMapping("/mos")
public class GetSelectListInfoController {
    @Autowired
    private GetSelectListInfoService getSelectListInfoService;
 

    @ApiOperation("サブ画面List检索")
    @PostMapping("/position")
    public List<ReturnResult> testMosList(@RequestBody Position position) {
  
      try {
        List<ReturnResult> testMosList = getSelectListInfoService.selectMosList( position);
        return testMosList;
      } catch (Exception e) {
        AjaxResult.fatal("查询失败!", e);
        return null;
      }
    }
}