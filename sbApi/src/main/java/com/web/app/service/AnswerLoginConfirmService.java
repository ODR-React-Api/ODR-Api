package com.web.app.service;

import com.web.app.domain.AnswerLoginConfirm.UpdCases;
import com.web.app.domain.AnswerLoginConfirm.UpdCasesRelations;

/**
 * S12 回答内容确认画面
 * Service层
 * AnswerLoginConfirmService
 * 
 * @author DUC 李文涛
 * @since 2024/05/30
 * @version 1.0
 */
public interface AnswerLoginConfirmService {

    /**
     * 修改邮箱
     * @param caseRelations
     * @return 更新行数
     */
    int updCasesRelations(UpdCasesRelations updCasesRelations);
    /**
     * 案件修改
     * @param caseDto
     * @return 更新行数
     */
    int updCases(UpdCases updCases);
} 
