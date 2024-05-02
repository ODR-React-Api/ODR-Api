package com.web.app.service.impl;

import com.web.app.domain.TestUser;
import com.web.app.mapper.TestUserMapper;
import com.web.app.service.ReconciliationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.ReconciliationUser;
import com.web.app.mapper.ReconciliationMapper;;
@Service
public class ReconciliationServiceimpl implements ReconciliationService {

    @Autowired
    private ReconciliationMapper ReconciliationUpdate;

    @Override
    public ReconciliationUser reconciliationInsertUpdateDeleteTransactional(ReconciliationUser reconciliationuser) {
        return ReconciliationUpdate.reconciliationInsertUpdateDeleteTransactional(reconciliationuser);
    }
}
