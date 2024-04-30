package com.web.app.service;

import com.web.app.domain.CaseDetailResultInfo;
import com.web.app.domain.CaseIdListInfo;

public interface CaseDetailService {
    CaseDetailResultInfo CaseDetailCasesInfoSearch(CaseIdListInfo caseIdListInfo);
}
