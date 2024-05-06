package com.web.app.service;

import java.util.List;

import com.web.app.domain.MediationsUserData;

public interface GetMediationsUserDataService {
    List<MediationsUserData> findAllUser(String caseId, String platformId);
}
