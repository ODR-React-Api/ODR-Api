package com.web.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.QuestionnaireData;
import com.web.app.domain.QuestionnaireList;
import com.web.app.domain.Questionnaire_Mails;
import com.web.app.mapper.QuestionnaireMapper;
import com.web.app.service.QuestionnaireService;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

  @Autowired
  private QuestionnaireMapper testQuestionnaieMapper;

  @Override
  public Questionnaire_Mails selectQuestionnaireData(String Id, String platformId) {

    QuestionnaireData questionnaireData = testQuestionnaieMapper.questionnaieListDataSearch(Id);
    Questionnaire_Mails allQuestionnaire = new Questionnaire_Mails();
    // 確認画面用データ
    allQuestionnaire.setQuestionnaireData(questionnaireData);

    // アンケート回答済みかフラグ
    int count = testQuestionnaieMapper.questionnaieCountSearch(questionnaireData.getCaseId(),
        questionnaireData.getQuestionId(), questionnaireData.getUserType());

    if (count > 0) {
      allQuestionnaire.setFlag(true);
    } else {
      allQuestionnaire.setFlag(false);
    }
    // アンケートの問題リスト
    List<QuestionnaireList> questionnaireList = testQuestionnaieMapper
        .searchQuestionnaieList(questionnaireData.getQuestionId(), platformId);

    allQuestionnaire.setQuestionnaireList(questionnaireList);
    return allQuestionnaire;
  }

  // @Override
  // public List<QuestionnaireData> selectQuestionnaireData(String Id) {
  //   return testQuestionnaieMapper.questionnaieListDataSearch(Id);
  // }

  // @Override
  // public int selectQuestionnairecount(String Id) {
  // return testQuestionnaieMapper.questionnaieCountSearch(Id);
  // }

  // @Override
  // public List<QuestionnaireList> selectQuestionnaireList(String Id, String PlatformId) {
  // return testQuestionnaieMapper.searchQuestionnaieList(Id, PlatformId);
  // }
}
