package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.MediatorDisclosureRequest;

@Mapper
public interface UpdMediatorDisclosureFlagMapper {
    Boolean updMediatorDisclosureFlag(MediatorDisclosureRequest mediatorDisclosureRequest);

}
