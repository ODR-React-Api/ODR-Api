package com.web.app.controller;

import com.web.app.domain.RelationsContent;
import com.web.app.domain.Response;
import com.web.app.service.RelationsContentService;


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

  @SuppressWarnings("rawtypes")
  @GetMapping("/getRelationsContent")
  public Response getRelationsContent(String caseId) {

    // 申立ての内容取得
    RelationsContent relationsContent = relationsContentService.selectRelationsContentData(caseId);

    if (relationsContent != null) {
      return Response.success(relationsContent);
    }
    return Response.error("失败");
  }

}
