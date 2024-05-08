package com.web.app.controller;

import com.web.app.domain.CaseRelations;
import com.web.app.domain.Response;
import com.web.app.service.CaseRelationsService;

import io.swagger.annotations.Api;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
// 声明当前controller需要生成文档，并且指定在文档中的标签为“用户模块”
@Api(tags = "関係者メアド取得")
@RestController
// @RequestMapping("/getQuestionnaires")
public class RelationsController {

  @Autowired
  DataSource dataSource;

  @Autowired
  private CaseRelationsService caseRelationsService;

  @SuppressWarnings("rawtypes")
  @GetMapping("/getRelations")
  public Response getRelations(String caseId) {


      // 申立ての内容取得
      CaseRelations caseRelations = caseRelationsService.selectRelationsData(caseId);

      if(caseRelations != null) {
        return Response.success(caseRelations);
    }
    return Response.error("失败");
  }
}
