package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.PoliciesInfo;

@Mapper
public interface GetPoliciesInfoMapper {
    PoliciesInfo selectPoliciesInfo();

    PoliciesInfo selectPrivacyPolicyInfo();
    
}
