package com.web.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.QuesAnswer.QuestionnaireData;
import com.web.app.domain.QuesAnswer.QuestionnaireList;
import com.web.app.domain.QuesAnswer.Questionnaire_Mails;
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

    /**
     * アンケート情報取得
     *
     * @param Id         サーバコンフィグ.プラットフォームID
     * @param platformId プラットフォームID
     * @return アンケート情報取得必要なすべてのデータ
     */

    @Autowired
    private GetQuestionnairesMapper getQuestionnairesMapper;

    @Override
    public Questionnaire_Mails selectQuestionnaireData(String Id, String platformId) {

        QuestionnaireData questionnaireData = getQuestionnairesMapper.questionnaieDataSearch(Id);
        Questionnaire_Mails allQuestionnaire = new Questionnaire_Mails();

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
}
