package com.web.app.service;

import java.util.List;

import com.web.app.domain.QuesAnswer.QuestionnaireMails;

/**
 * アンケート回答画面
 * 
 * @author DUC 王亞テイ
 * @since 2024/04/23
 * @version 1.0
 */
public interface QuesAnswerService {
    // アンケート情報取得
    QuestionnaireMails getQuestionnaires(String id, String platformId);

    List<String> getQuestionnairesGuidList();
}
