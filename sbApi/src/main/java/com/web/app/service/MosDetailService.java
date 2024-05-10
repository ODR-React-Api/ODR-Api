package com.web.app.service;

import com.web.app.domain.Entity.CaseRelations;

public interface MosDetailService {

    
    CaseRelations selectCaseRelationsByCaseId(String CaseId);

}
