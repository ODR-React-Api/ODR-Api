package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.MediationsUserData;
import com.web.app.mapper.GetMediationsUserDataMapper;
import com.web.app.service.GetMediationsUserDataService;

@Service
public class GetMediationsUserDataServiceImpl implements GetMediationsUserDataService {

    @Autowired
    private GetMediationsUserDataMapper getMediationsUserDataMapper;

    @Override
    public List<MediationsUserData> findAllUser(String caseId, String platformId) {
        List<MediationsUserData> list = new ArrayList<MediationsUserData>();
        list = getMediationsUserDataMapper.findAllUser(caseId, platformId);
        return list;
    }

}
