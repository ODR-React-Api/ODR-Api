package com.web.app.service;

import java.util.List;

import com.web.app.domain.MediationsConCon.MediationsTemplate;
import com.web.app.domain.MediationsConCon.MediationsUserData;

public interface MediationsConConService {
    
    List<MediationsUserData> findAllUser(String caseId, String platformId);

    List<MediationsTemplate> findMediationsTemplate(String platformId,String languageId);
}
