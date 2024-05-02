package com.web.app.service.impl;
import com.web.app.service.ReconciliationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.ReconciliationUser;
import com.web.app.mapper.ReconciliationMapper;;
@Service
public class ReconciliationServiceimpl implements ReconciliationService {

    @Autowired
    private ReconciliationMapper ReconciliationUpdate;

    @Override
    public ReconciliationUser reconciliationUserSearch(ReconciliationUser reconciliationuser) {
        return ReconciliationUpdate.reconciliationUserSearch(reconciliationuser);
    }
    @Transactional
    @Override
    public int reconciliationUpdate(ReconciliationUser reconciliationuser) {
        int ReconciliationUpdateStatus = ReconciliationUpdate.reconciliationUpdate(reconciliationuser);
        if (ReconciliationUpdateStatus == 0) {
            return 0;
        }
        return 1;
    }
}
