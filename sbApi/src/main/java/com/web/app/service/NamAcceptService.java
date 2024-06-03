package com.web.app.service;

import com.web.app.domain.NamAccept.UpdMediatorHistories;

/**
 * S33_指名受理画面
 * Service層
 * NamAcceptService
 * 
 * @author DUC 閆文静 耿浩哲
 * @since 2024/05/08
 * @version 1.0
 */
public interface NamAcceptService {
    // 申立状態を更新
    // 調停人履歴レコードを更新
    int updCaseStatusForAccept(String caseId);

    // 調停人変更履歴変更
    int updMediatorHistories(UpdMediatorHistories updMediatorHistories) throws Exception;

}