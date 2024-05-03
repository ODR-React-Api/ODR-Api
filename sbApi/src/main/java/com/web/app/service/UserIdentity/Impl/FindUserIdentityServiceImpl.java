package com.web.app.service.UserIdentity.Impl;

import org.springframework.stereotype.Service;

import com.web.app.domain.UserIdentity.UserIdentity;
import com.web.app.mapper.UserIdentity.UserIdentityMapper;
import com.web.app.service.UserIdentity.FindUserIdentityService;

@Service
public class FindUserIdentityServiceImpl implements FindUserIdentityService{

    private UserIdentityMapper userIdentityMapper;

    @Override
    public UserIdentity FindUserIdentity(String eamil) {
        UserIdentity userIdentity = userIdentityMapper.FindUserIdentity(eamil);
        return userIdentity;
    }
    
}
