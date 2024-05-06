package com.web.app.service.impl;

import com.web.app.service.MediationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.mapper.MediationMapper;

import com.web.app.domain.MediateUser;

@Service
public class MediationServiceImpl implements MediationService {
    @Autowired
    private MediationMapper mediationMapper;

    @Override
    public MediateUser Mediationstatus(MediateUser mediateUser) {
        MediateUser num = mediationMapper.Mediationstatus(mediateUser);
        return num;
    }
}
