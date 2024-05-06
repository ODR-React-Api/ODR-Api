package com.web.app.mapper;

import java.util.List;

import com.web.app.domain.MediationsUserData;

public interface GetMediationsUserDataMapper {

    List<MediationsUserData> findAllUser(String caseId,String platformId);

}
