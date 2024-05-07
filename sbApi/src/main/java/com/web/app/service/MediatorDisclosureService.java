package com.web.app.service;

import java.util.Date;

import com.web.app.domain.MediatorDisclosureRequest;

public interface MediatorDisclosureService {
    Date getMediatorDisclosureDate(String caseId);

    Boolean updMediatorDisclosureFlag(MediatorDisclosureRequest mediatorDisclosureRequest);
}
