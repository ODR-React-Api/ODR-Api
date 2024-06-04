package com.web.app.service;

import com.web.app.domain.Question.InsQuestionnairesResults;

/**
 * C09_アンケート回答確認画面
 * Service層
 * QuesAnswerConfirmService
 * 
 * @author DUC zzy
 * @since 2024/05/27
 * @version 1.0
 */
public interface QuesAnswerConfirmService {
    int InsQuestionnairesResults(InsQuestionnairesResults insQuestionnaireResults);
}
