package com.web.app.mapper;

import java.util.List;

import com.web.app.domain.CouAnswerLogin.CasesByCid;

public interface CasesByCidMapper {

  List<CasesByCid> casesByCid(String CaseId,String PlatformId);

}

