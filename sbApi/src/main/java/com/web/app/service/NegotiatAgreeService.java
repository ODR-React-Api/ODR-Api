package com.web.app.service;

import com.web.app.domain.UpdNegotiatAgree.ReconciliationUser;;

/**
 * 和解案合意更新
 * 
 * @author DUC 賈文志
 * @since 2024/05/13
 * @version 1.0
 */

public interface NegotiatAgreeService {

    // 和解案合意更新
    int reconciliationUpdate(ReconciliationUser reconciliationuser);

}
