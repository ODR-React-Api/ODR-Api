package com.web.app.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.MediatorDisclosureRequest;

@Mapper
public interface InfoPublicSeigenMapper {
    Date getMediatorDisclosureDate(String caseId);

    Boolean updMediatorDisclosureFlag(MediatorDisclosureRequest mediatorDisclosureRequest);
}
