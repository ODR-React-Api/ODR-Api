package com.web.app.service.QuesAnswerConfirm.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.app.domain.QuesAnswerConfirm.InsQuestionnaireResults;
import com.web.app.mapper.QuesAnswerConfirm.QuesAnswerConfirmMapper;
import com.web.app.service.QuesAnswerConfirm.QuesAnswerConfirmService;

/**
 * アンケート回答確認画面
 * Service层实现类
 * QuesAnswerConfirmServiceImpl
 * 
 * @author DUC 張明慧
 * @since 2024/04/14
 * @version 1.0
 */
@Service
public class QuesAnswerConfirmServiceImpl implements QuesAnswerConfirmService {
    @Autowired
    private QuesAnswerConfirmMapper quesAnswerConfirmMapper;

    /**
     * API_アンケート入力結果新規登録
     * 2.2 アンケート回答内容を登録
     * 
     * @param insQuestionnaireResults アンケート回答登録処理の引数
     * @return int API_アンケート入力結果新規登録の状況
     */
    @Override
    public int insQuestionnairesResults(InsQuestionnaireResults insQuestionnaireResults) {
        return quesAnswerConfirmMapper.insQuestionnairesResults(insQuestionnaireResults);
    }

}
