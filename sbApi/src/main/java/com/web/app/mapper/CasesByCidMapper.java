package com.web.app.mapper;

import com.web.app.domain.CasesByCid;

import java.util.List;

public interface CasesByCidMapper {

  List<CasesByCid> casesByCid(String CaseId,String PlatformId);

}


