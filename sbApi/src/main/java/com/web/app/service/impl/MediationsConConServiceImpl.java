package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.MediationsConCon.MediationsTemplate;
import com.web.app.domain.MediationsConCon.MediationsUserData;
import com.web.app.mapper.GetMediationsTemplateMapper;
import com.web.app.mapper.GetMediationsUserDataMapper;
import com.web.app.service.MediationsConConService;

@Service
public class MediationsConConServiceImpl implements MediationsConConService{

    @Autowired
    private GetMediationsTemplateMapper getMediationsTemplateMapper;
    private GetMediationsUserDataMapper getMediationsUserDataMapper;


    @Override
    public List<MediationsTemplate> findMediationsTemplate(String platformId, String languageId) {
        List<MediationsTemplate> list = new ArrayList<MediationsTemplate>();
        list = getMediationsTemplateMapper.findMediationsTemplate(platformId, languageId);
        return list;
    }

    @Override
    public List<MediationsUserData> findAllUser(String caseId, String platformId) {
        List<MediationsUserData> list = new ArrayList<MediationsUserData>();
        list = getMediationsUserDataMapper.findAllUser(caseId, platformId);
        return list;
    }

}
