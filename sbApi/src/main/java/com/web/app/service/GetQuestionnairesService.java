package com.web.app.service;

import com.web.app.domain.Questionnaire_Mails;

public interface GetQuestionnairesService {

    Questionnaire_Mails selectQuestionnaireData(String Id, String platformId);
}
