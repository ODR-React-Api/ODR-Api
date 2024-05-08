package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.Caserelations;

@Mapper
public interface CaserelationsMapper {
    //更新数据
    int updateCaserelations(Caserelations Caserelations);
}
