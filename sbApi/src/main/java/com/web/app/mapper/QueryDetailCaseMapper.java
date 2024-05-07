package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.SearchDetail;

@Mapper
public interface QueryDetailCaseMapper {

  SearchDetail getQueryDetailCase(String caseId, String queryString);

  int getMediatorDisclosureFlag(String caseId);

  int getMsgCountByFlag1(String caseId, String petitionUserId);

  int getMsgCountByFlagNo1(String caseId, String petitionUserId);
  
}
