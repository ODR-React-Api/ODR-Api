package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.User;
import com.web.app.domain.Entity.ActionHistories;
import com.web.app.mapper.CommonMapper;
import com.web.app.service.CommonService;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonMapper commonMapper;

    @Override
    public User GetUserDataFromCaseIdentity(Boolean identity, String languageId, String platformId, String caseId) {
        try {

            return commonMapper.GetUserDataFromCaseIdentity(identity, languageId, platformId, caseId);

        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(noRollbackFor = { ArithmeticException.class })
    @Override
    public Integer InsHistories(ActionHistories actionHistories) {
        Integer result = commonMapper.InsHistories(actionHistories);
        return result;
    }
}
