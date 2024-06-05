package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.QuesAnswer.QuestionnaireData;
import com.web.app.domain.QuesAnswer.QuestionnaireList;
import com.web.app.domain.QuesAnswer.QuestionnaireMails;
import com.web.app.mapper.GetQuestionnairesMapper;
import com.web.app.service.QuesAnswerService;

/**
 * アンケート情報取得データ
 * 
 * @author DUC 王亞テイ
 * @since 2024/04/17
 * @version 1.0
 */
@Service
public class QuesAnswerServiceImpl implements QuesAnswerService {

    @Autowired
    private GetQuestionnairesMapper getQuestionnairesMapper;

    /**
     * アンケート情報取得
     *
     * @param Id         サーバコンフィグ.プラットフォームID
     * @param platformId プラットフォームID
     * @return アンケート情報取得必要なすべてのデータ
     */
    @Override
    public QuestionnaireMails getQuestionnaires(String id, String platformId) {

        QuestionnaireMails allQuestionnaire = new QuestionnaireMails();

        QuestionnaireData questionnaireData = getQuestionnairesMapper.questionnaieDataSearch(id);

        // 確認画面用データ
        allQuestionnaire.setQuestionnaireData(questionnaireData);

        // アンケート回答済みかフラグ
        int count = getQuestionnairesMapper.questionnaieCountSearch(questionnaireData.getCaseId(),
                questionnaireData.getQuestionId(), questionnaireData.getUserType());

        // レコードのカウントが0の場合、FALSE;レコードのカウントが>0の場合、TRUE
        if (count > 0) {
            allQuestionnaire.setFlag(true);
        } else {
            allQuestionnaire.setFlag(false);
        }

        // アンケートの問題リスト
        List<QuestionnaireList> questionnaireList = getQuestionnairesMapper
                .searchQuestionnaieList(questionnaireData.getQuestionId(), platformId);

        allQuestionnaire.setQuestionnaireList(questionnaireList);

        return allQuestionnaire;
    }

    /**
     * @return Guid List
     */
    @Override
    public List<String> getQuestionnairesGuidList() {
        List<String> GuidList = getQuestionnairesMapper.getQuestionnairesGuidList();
        Set<String> set = new HashSet<>(GuidList);
        return new ArrayList<>(set);
    }
}
