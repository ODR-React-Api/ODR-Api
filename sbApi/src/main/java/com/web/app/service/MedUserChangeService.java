package com.web.app.service;

public interface MedUserChangeService {
    Boolean delAboutCasesMediations(String caseId);

    Boolean updAboutCasesInfo(String caseId, String userType, Boolean withReason);

}
