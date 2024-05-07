package com.web.app.service.impl;

import com.web.app.service.MediationService;

import java.util.ArrayList;

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

    @Override
    public MediateUser MediationEmail(MediateUser mediateUser) {
        MediateUser MediationEmail = new MediateUser();
        // 检索调停人Email
        String MediatorUserEmail = mediationMapper.MediatorUserEmail(mediateUser);
        MediationEmail.setMediatorUserEmail(MediatorUserEmail);
        // 检索Uid
        String MediatorUserUid = mediationMapper.MediatorUserUid(MediatorUserEmail);
        MediationEmail.setUid(MediatorUserUid);
        return MediationEmail;
    }

    @Override
    public ArrayList<MediateUser> MediatorIntelligence(MediateUser mediateUser) {
        ArrayList<MediateUser> MediatorIntelligence = mediationMapper.MediatorIntelligence(mediateUser);
        return MediatorIntelligence;
    }
}
