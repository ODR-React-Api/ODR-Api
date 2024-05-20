package com.web.app.service;

import com.web.app.domain.QuesAnswer.Questionnaire_Mails;

/**
 * アンケート回答画面
 * 
 * @author DUC 王亞テイ
 * @since 2024/04/23
 * @version 1.0
 */
public interface QuesAnswerService {
    // アンケート情報取得
    Questionnaire_Mails selectQuestionnaireData(String Id, String platformId);
}
