package com.web.app.service;

import com.web.app.domain.ReturnResult;
import com.web.app.domain.CaseIdListInfo;

public interface CaseDetailService {
    ReturnResult CaseDetailCasesInfoSearch(CaseIdListInfo caseIdListInfo);
}
