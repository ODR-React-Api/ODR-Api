package com.web.app.service;

public interface CasesMediationsService {
    Boolean delAboutCasesMediations(String caseId);

    Boolean updAboutCasesInfo(String caseId, String userType, Boolean withReason);

}
