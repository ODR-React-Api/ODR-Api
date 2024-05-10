package com.web.app.service;

import com.web.app.domain.ReconciliationUser;

/**
 * 和解案合意更新
 * 
 * @author DUC jiawenzhi
 * @since 2024/05/10
 * @version 1.0
 */

public interface UpdNegotiatAgreeService {
    
    // 和解案合意更新
    int reconciliationUpdate(ReconciliationUser reconciliationuser);

}
