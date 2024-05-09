package com.web.app.service.QuesAnswerConfirm;

import com.web.app.domain.QuesAnswerConfirm.InsQuestionnaireResults;

/**
 * アンケート回答確認画面
 * Service层
 * QuesAnswerConfirmService
 * 
 * @author DUC 張明慧
 * @since 2024/04/14
 * @version 1.0
 */
public interface QuesAnswerConfirmService {
    // API_アンケート入力結果新規登録
    // 2.2 アンケート回答内容を登録
    int insQuestionnairesResults(InsQuestionnaireResults insQuestionnaireResults);
}
