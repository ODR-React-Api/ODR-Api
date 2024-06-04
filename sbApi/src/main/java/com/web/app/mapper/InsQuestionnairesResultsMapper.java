package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.Entity.QuestionnaireResults;

/**
 * C09_アンケート回答確認画面
 * Mapper層
 * InsQuestionnairesResultsMapper
 * API_アンケート入力結果新規登録
 * 
 * @author DUC zzy
 * @since 2024/05/27
 * @version 1.0
 */
@Mapper
public interface InsQuestionnairesResultsMapper {
    int insQuestionnairesResults(QuestionnaireResults questionnaireResults);
    // 【画面C8】.caseId対応なCaseStage
    Integer getCaseStage(String caseId);
}
