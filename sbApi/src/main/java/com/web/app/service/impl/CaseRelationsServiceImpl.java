package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.app.domain.CaseRelations;
import com.web.app.mapper.CaseRelationsMapper;
import com.web.app.service.CaseRelationsService;

@Service
public class CaseRelationsServiceImpl implements CaseRelationsService {

  @Autowired
  private CaseRelationsMapper caseRelationsMapper;

  @Override
  public CaseRelations selectRelationsData(String caseId) {

    CaseRelations caseRelations = caseRelationsMapper.RelationsListDataSearch(caseId);

    return caseRelations;
  }

}
