package com.web.app.service;

import com.web.app.domain.QuesAnswerConfirm.InsQuestionnaireResults;

/**
 * C09_アンケート回答確認画面
 * Service层
 * QuesAnswerConfirmService
 * 
 * @author DUC 張明慧
 * @since 2024/04/14
 * @version 1.0
 */
public interface QuesAnswerConfirmService {
    // API_アンケート入力結果新規登録
    int InsQuestionnairesResults(InsQuestionnaireResults insQuestionnaireResults);
}
