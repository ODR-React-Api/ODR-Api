package com.web.app.service;

import com.web.app.domain.ReturnResult;

public interface QueryDetailCaseService {

  ReturnResult getQueryDetailCase(String caseId, String petitionUserId, Integer positionFlag, String queryString);
  
}
