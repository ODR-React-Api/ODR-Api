package com.web.app.service;

import com.web.app.domain.AnswerLoginConfirm.UpdCases;
import com.web.app.domain.AnswerLoginConfirm.UpdCasesRelations;

/**
 * S12 回答内容確認画面
 * Service层
 * AnswerLoginConfirmService
 * 
 * @author DUC 李文涛
 * @since 2024/05/30
 * @version 1.0
 */
public interface AnswerLoginConfirmService {


    //API_案件別個人情報リレーションデータ更新
    int updCasesRelations(UpdCasesRelations updCasesRelations);
    
    // API_案件更新
    int updCases(UpdCases updCases);
} 
