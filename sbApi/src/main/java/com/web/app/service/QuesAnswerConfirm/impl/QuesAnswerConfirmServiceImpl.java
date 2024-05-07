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
 */
@Service
public class QuesAnswerConfirmServiceImpl implements QuesAnswerConfirmService {
    @Autowired
    private QuesAnswerConfirmMapper quesAnswerConfirmMapper;

    @Override
    public int insQuestionnairesResults(InsQuestionnaireResults insQuestionnaireResults) {
        return quesAnswerConfirmMapper.insQuestionnairesResults(insQuestionnaireResults);
    }

}
