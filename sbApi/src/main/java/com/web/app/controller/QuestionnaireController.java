package com.web.app.controller;

import com.web.app.domain.Questionnaire_Mails;

import com.web.app.service.QuestionnaireService;

import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
// 声明当前controller需要生成文档，并且指定在文档中的标签为“用户模块”
@Api(tags = "アンケート情報取得")
@RestController
// @RequestMapping("/getQuestionnaires")
public class QuestionnaireController {

  @Autowired
  DataSource dataSource;

  @Autowired
  private QuestionnaireService questionnaireService;

  @GetMapping("/getQuestionnaires")
  public Questionnaire_Mails getQuestionnaires(String Id, String PlatformId) {

    try {

      Questionnaire_Mails allQuestionnaire = new Questionnaire_Mails();

      allQuestionnaire = questionnaireService.selectQuestionnaireData(Id, PlatformId);

      // 確認画面用データ
      // allQuestionnaire.setQuestionnaireData(questionnaireService.selectQuestionnaireData(Id));

      // // アンケート回答済みかフラグ
      // int count = questionnaireService.selectQuestionnairecount(Id);
      // if (count > 0) {
      //   allQuestionnaire.setFlag(true);
      // } else {
      //   allQuestionnaire.setFlag(false);
      // }
      // // アンケートの問題リスト
      // allQuestionnaire.setQuestionnaireList(questionnaireService.selectQuestionnaireList(Id,PlatformId));

      return allQuestionnaire;
    } catch (Exception e) {
      AjaxResult.fatal("查询失败!", e);
      return null;
    }
  }

}
