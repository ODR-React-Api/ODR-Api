package com.web.app.service;

import com.web.app.domain.QuesAnswer.Questionnaire_Mails;

public interface QuesAnswerService {

    Questionnaire_Mails selectQuestionnaireData(String Id, String platformId);
}
