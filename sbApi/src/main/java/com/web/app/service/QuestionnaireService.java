package com.web.app.service;

import com.web.app.domain.Questionnaire_Mails;

public interface QuestionnaireService {

  Questionnaire_Mails selectQuestionnaireData(String Id,String platformId);

  // int selectQuestionnairecount(String caseId,String questionId,Integer userType);

  // List<QuestionnaireData> selectQuestionnaireData(String Id);

  // int selectQuestionnairecount(String Id);

  // List<QuestionnaireList> selectQuestionnaireList(String Id, String PlatformId);

}
