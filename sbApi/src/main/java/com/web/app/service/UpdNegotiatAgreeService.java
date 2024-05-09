package com.web.app.service;

import com.web.app.domain.ReconciliationUser;
import com.web.app.domain.Entity.Cases;

public interface UpdNegotiatAgreeService {

    int reconciliationUpdate(ReconciliationUser reconciliationuser);

    int ActionHistoriesInsert(ReconciliationUser reconciliationuser);

    String CaseTitleSearch(Cases cases);

    Boolean sendMailRequest(ReconciliationUser reconciliationuser);

}
