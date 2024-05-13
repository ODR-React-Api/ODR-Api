package com.web.app.service;

/**
 * API_案件ステータス更新
 * 
 * @author DUC 閆文静
 * @since 2024/05/14
 * @version 1.0
 */
public interface NamAcceptService {
    // 申立状態を更新
    int updCase(String caseId);

    // 調停人履歴レコードを更新
    int updMediatorHistories(String caseId);

}
