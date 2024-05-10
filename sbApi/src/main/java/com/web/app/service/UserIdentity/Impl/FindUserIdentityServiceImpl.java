package com.web.app.service.UserIdentity.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.S19.NegotiationsData;
import com.web.app.domain.UserIdentity.UserIdentity;
import com.web.app.mapper.UserIdentity.UserIdentityMapper;
import com.web.app.service.UserIdentity.FindUserIdentityService;

@Service
public class FindUserIdentityServiceImpl implements FindUserIdentityService{
    @Autowired
    private UserIdentityMapper userIdentityMapper;

    @Override
    public String FindUserIdentity(NegotiationsData addSettlementPlan) {
        UserIdentity userIdentity = userIdentityMapper.FindUserIdentity(addSettlementPlan.getCaseId());
        if (addSettlementPlan.getEmail().equals(userIdentity.getPetitionUserInfo_Email()) ||
                addSettlementPlan.getEmail().equals(userIdentity.getAgent1_Email()) ||
                addSettlementPlan.getEmail().equals(userIdentity.getAgent2_Email()) ||
                addSettlementPlan.getEmail().equals(userIdentity.getAgent3_Email()) ||
                addSettlementPlan.getEmail().equals(userIdentity.getAgent4_Email()) ||
                addSettlementPlan.getEmail().equals(userIdentity.getAgent5_Email())) 
            {
                return "15";
            } else if(
                addSettlementPlan.getEmail().equals(userIdentity.getTraderUserEmail()) ||
                addSettlementPlan.getEmail().equals(userIdentity.getTraderAgent1_UserEmail()) ||
                addSettlementPlan.getEmail().equals(userIdentity.getTraderAgent2_UserEmail()) ||
                addSettlementPlan.getEmail().equals(userIdentity.getTraderAgent3_UserEmail()) ||
                addSettlementPlan.getEmail().equals(userIdentity.getTraderAgent4_UserEmail()) ||
                addSettlementPlan.getEmail().equals(userIdentity.getTraderAgent5_UserEmail())
            ){
                return "2";
            }
        return null;
    }
    
}
