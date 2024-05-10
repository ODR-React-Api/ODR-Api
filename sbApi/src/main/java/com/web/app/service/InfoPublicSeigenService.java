package com.web.app.service;

import java.util.Date;

import com.web.app.domain.MediatorDisclosureRequest;

public interface InfoPublicSeigenService {
    Date getMediatorDisclosureDate(String caseId);

    Boolean updMediatorDisclosureFlag(MediatorDisclosureRequest mediatorDisclosureRequest);
}
