package com.web.app.service.impl;

import com.web.app.service.UpdNegotiatAgreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.web.app.domain.ReconciliationUser;
import com.web.app.mapper.UpdNegotiatAgreeMapper;

@Service
public class UpdNegotiatAgreeImpl implements UpdNegotiatAgreeService {

    @Autowired
    private UpdNegotiatAgreeMapper ReconciliationUpdate;

    /**
     * 和解案合意更新
     * 
     * @param reconciliationuser 前台伝出のデータ
     * @return 和解案合意更新は成功したか
     */
    @Transactional
    @Override
    public int reconciliationUpdate(ReconciliationUser reconciliationuser) {
        // 和解案合意更新
        int ReconciliationUpdateStatus = ReconciliationUpdate.reconciliationUpdate(reconciliationuser);
        return ReconciliationUpdateStatus;
    }
}
