package com.web.app.mapper;

import com.web.app.domain.Casecase;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CasecaseMapper {
    int updateCasecase(Casecase Casecase);
}
