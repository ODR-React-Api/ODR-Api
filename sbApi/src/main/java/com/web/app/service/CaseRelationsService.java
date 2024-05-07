package com.web.app.service;

import com.web.app.domain.CaseRelations;

public interface CaseRelationsService {

  CaseRelations selectRelationsData(String caseId);
  
}
