package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.Entity.Cases;

@Mapper
public interface UpdAboutCasesInfoMapper {
    int updAboutCasesInfo(Cases cases, Boolean withReason);

    Cases getMediatorChangeableCount(String caseId);

}
