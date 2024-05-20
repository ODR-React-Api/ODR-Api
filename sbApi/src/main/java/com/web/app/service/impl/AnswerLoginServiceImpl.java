package com.web.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.PetitionsData;
import com.web.app.mapper.GetPetitionsDataMapper;
import com.web.app.service.AnswerLoginService;


@Service
public class AnswerLoginServiceImpl implements AnswerLoginService{
    @Autowired
    GetPetitionsDataMapper getPetitionsDataMapper;

    @Override
    public List<PetitionsData> getPetitionData(String caseId, String plateFormId) {
        return getPetitionsDataMapper.getPetitionsData(caseId, plateFormId);
    }
    
}
