package com.web.app.service;

import com.web.app.domain.MedUserChange.InsertFileInfo;

public interface MedUserChangeService {
    /**
     * API_調停案削除
     * 
     * @param caseId セッション.案件ID
     * @return true false
     */
    Boolean delAboutCasesMediations(String caseId);

    Boolean updAboutCasesInfo(String caseId, String userType, Boolean withReason);

    int insertFileInfo(InsertFileInfo insertFileInfo);

}
