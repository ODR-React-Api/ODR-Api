package com.web.app.service;

import java.util.Date;

public interface CasesMediationsService {
    Boolean delAboutCasesMediations(String caseId);

    Date getMediatorDisclosureDate(String caseId);

    Boolean updMediatorDisclosureFlag(String caseId);

}
