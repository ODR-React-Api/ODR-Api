package com.web.app.service;

import com.web.app.domain.ReconciliationUser;
import com.web.app.domain.ActionHistories;
import com.web.app.domain.Entity.Cases;;

public interface ReconciliationService {
    ReconciliationUser reconciliationUserSearch(ReconciliationUser reconciliationuser);
    int reconciliationUpdate(ReconciliationUser reconciliationuser);
    int ActionHistoriesInsert(ActionHistories actionhistories);
    String CaseTitleSearch(Cases cases);
}

