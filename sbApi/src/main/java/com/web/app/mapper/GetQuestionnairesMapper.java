package com.web.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.QuestionnaireData;
import com.web.app.domain.QuestionnaireList;

@Mapper
public interface GetQuestionnairesMapper {

    // 確認画面用データ
    QuestionnaireData questionnaieDataSearch(String Id);

    // アンケート回答済みかフラグ
    int questionnaieCountSearch(String caseId, String questionId, Integer userType);

    // アンケートの問題リスト
    List<QuestionnaireList> searchQuestionnaieList(String questionId, String platformId);

}
