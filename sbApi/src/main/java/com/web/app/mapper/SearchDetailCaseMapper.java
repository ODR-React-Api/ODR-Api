package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.SearchDetail;
import com.web.app.domain.SelectCondition;

@Mapper
public interface SearchDetailCaseMapper {
  SearchDetail searchDetail(SelectCondition searchCase);

  Integer getMediatorDisclosureFlag(String caseId);

  Integer getMsgCountByFlag1(String caseId, String petitionUserId);

  Integer getMsgCountByFlagNo1(String caseId, String petitionUserId);
}