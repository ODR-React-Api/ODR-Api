package com.web.app.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GetMediatorDisclosureDateMapper {
    Date getMediatorDisclosureDate(String caseId);

}
