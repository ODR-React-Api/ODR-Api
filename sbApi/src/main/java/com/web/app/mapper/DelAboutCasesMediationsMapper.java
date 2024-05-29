package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DelAboutCasesMediationsMapper {
    /**
     * 
     * @param caseId
     * @return true false
     */
    int delAboutCasesMediations(String caseId);
}
