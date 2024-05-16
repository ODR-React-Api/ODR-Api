package com.web.app.service;

import com.web.app.domain.AnswerLoginConfirm.UpdCases;
import com.web.app.domain.AnswerLoginConfirm.UpdCasesRelations;

/**
 * S12 回答内容確認画面
 * Service層
 * AnswerLoginConfirmService
 * 
 * @author DUC 楊バイバイ
 * @since 2024/05/01
 * @version 1.0
 */
public interface AnswerLoginConfirmService {

    // API_案件別個人情報リレーションデータ更新
    int updateCaserelations(UpdCasesRelations caserelations);
    // API_案件更新
    int updateCasecase(UpdCases casecase);

}
