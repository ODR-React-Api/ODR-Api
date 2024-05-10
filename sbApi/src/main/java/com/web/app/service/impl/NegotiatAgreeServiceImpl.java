package com.web.app.service.impl;

import com.web.app.service.NegotiatAgreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.UpdNegotiatAgree.ReconciliationUser;
import com.web.app.mapper.UpdNegotiatAgreeMapper;

/**
 * 和解案合意更新API
 * 
 * @author DUC jiawenzhi
 * @since 2024/05/10
 * @version 1.0
 */
@Service
public class NegotiatAgreeServiceImpl implements NegotiatAgreeService {

    @Autowired
    private UpdNegotiatAgreeMapper ReconciliationUpdate;

    /**
     * 和解案合意更新
     * 
     * @param reconciliationuser 前台伝出のデータ
     * @return 和解案合意更新状態
     */
    @Transactional
    @Override
    public int reconciliationUpdate(ReconciliationUser reconciliationuser) {
        // 和解案合意更新
        int ReconciliationUpdateStatus = ReconciliationUpdate.reconciliationUpdate(reconciliationuser);
        return ReconciliationUpdateStatus;
    }
}
