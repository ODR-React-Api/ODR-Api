package com.web.app.controller;

import com.web.app.domain.RelationsContent;
import com.web.app.service.RelationsContentService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
// 声明当前controller需要生成文档，并且指定在文档中的标签为“用户模块”
@Api(tags = "関係者内容取得")
@RestController
// @RequestMapping("/getQuestionnaires")
public class RelationsContentController {

  @Autowired
  DataSource dataSource;

  @Autowired
  private RelationsContentService relationsContentService;

  @GetMapping("/getRelationsContent")
  public RelationsContent getRelationsContent(String caseId) {

    try {

      // 申立ての内容取得
      RelationsContent relationsContent = relationsContentService.selectRelationsContentData(caseId);

      return relationsContent;
    } catch (Exception e) {
      AjaxResult.fatal("查询失败!", e);
      return null;
    }
  }

}
