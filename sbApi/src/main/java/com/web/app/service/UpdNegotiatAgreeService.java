package com.web.app.service;

import com.web.app.domain.ReconciliationUser;

/**
 * 和解案合意更新API
 * 「アクロン履歴」新規登録
 * メール送信
 * 
 * @author DUC jiawenzhi
 * @since 2024/05/09
 * @version 1.0
 */

public interface UpdNegotiatAgreeService {

    int reconciliationUpdate(ReconciliationUser reconciliationuser);

    int ActionHistoriesInsert(ReconciliationUser reconciliationuser);

    Boolean sendMailRequest(ReconciliationUser reconciliationuser);

}
