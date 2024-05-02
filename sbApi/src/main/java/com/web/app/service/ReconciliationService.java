package com.web.app.service;

import com.web.app.domain.ReconciliationUser;

public interface ReconciliationService {
    ReconciliationUser reconciliationUserSearch(ReconciliationUser reconciliationuser);
    int reconciliationUpdate(ReconciliationUser reconciliationuser);
}

