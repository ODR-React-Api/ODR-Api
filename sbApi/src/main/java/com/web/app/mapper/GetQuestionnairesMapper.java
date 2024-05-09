package com.web.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.QuestionnaireData;
import com.web.app.domain.QuestionnaireList;

@Mapper
public interface GetQuestionnairesMapper {

    QuestionnaireData questionnaieListDataSearch(String Id);

    int questionnaieCountSearch(String caseId, String questionId, Integer userType);

    List<QuestionnaireList> searchQuestionnaieList(String questionId, String platformId);

}
