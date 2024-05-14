package com.web.app.mapper.QuesAnswerConfirm;
import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.QuesAnswerConfirm.InsQuestionnaireResults;

/**
 * アンケート回答確認画面
 * Mapper层
 * QuesAnswerConfirmMapper
 */
@Mapper
public interface QuesAnswerConfirmMapper {
    // API_アンケート入力結果新規登録
    int insQuestionnairesResults(InsQuestionnaireResults insQuestionnaireResults);
}
