package com.web.app.service;

import java.util.Date;

import com.web.app.domain.MediatorDisclosureRequest;

public interface InfoPublicSeigenService {
    /**
     * 
     * @param caseId
     * @return
     */
    Date getMediatorDisclosureDate(String caseId);

    /**
     * 
     * @param mediatorDisclosureRequest
     * @return
     */
    Boolean updMediatorDisclosureFlag(MediatorDisclosureRequest mediatorDisclosureRequest);
}
