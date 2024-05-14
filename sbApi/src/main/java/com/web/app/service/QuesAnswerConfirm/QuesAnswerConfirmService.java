package com.web.app.service.QuesAnswerConfirm;
import com.web.app.domain.QuesAnswerConfirm.InsQuestionnaireResults;

/**
 * アンケート回答確認画面
 * Service层
 * QuesAnswerConfirmService
 */
public interface QuesAnswerConfirmService {
    // API_アンケート入力結果新規登録
    int insQuestionnairesResults(InsQuestionnaireResults insQuestionnaireResults);
}
