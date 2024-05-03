package com.web.app.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author HHH
 * @description 针对表【user】的数据库操作Mapper
 * @createDate 2023-01-31 20:38:15
 * @Entity com.web.app.domain.User
 */
@Mapper
public interface CasesMediationsMapper {
    Boolean delAboutCasesMediations(String caseId);

    Date getMediatorDisclosureDate(String caseId);

    Boolean updMediatorDisclosureFlag(String caseId);

}
