package com.web.app.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.Entity.CaseRelations;



@Mapper
public interface CaseRelationsMapper {

    CaseRelations RelationsListDataSearch(String caseId);

}
