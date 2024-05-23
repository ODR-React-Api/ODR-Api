package com.web.app.service;

/**
 * S33_指名受理画面
 * Service層
 * NamAcceptService
 * 
 * @author DUC 閆文静
 * @since 2024/05/14
 * @version 1.0
 */
public interface NamAcceptService {
    // 申立状態を更新
    // 調停人履歴レコードを更新
    int updCaseStatusForAccept(String caseId);

}
