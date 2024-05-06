package com.web.app.service.SettlementPlan.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.SettlementPlan.AddSettlementPlan;
import com.web.app.mapper.SettlementPlan.SettlementPlanMapper;
import com.web.app.service.SettlementPlan.AddSettlementPlanService;

@Service
public class AddSettlementPlanServiceImpl implements AddSettlementPlanService{
    @Autowired
    private  SettlementPlanMapper settlementPlanMapper;

    @Override
    public int AddSettlementPlan(AddSettlementPlan AddSettlementPlan) {
        return settlementPlanMapper.AddSettlementPlan(AddSettlementPlan);
    }

    @Override
    public int AddFile(AddSettlementPlan addSettlementPlan) {
        return settlementPlanMapper.AddFile(addSettlementPlan);
    }

    @Override
    public int AddCaseFileRelations(AddSettlementPlan addSettlementPlan) {
        return settlementPlanMapper.AddCaseFileRelations(addSettlementPlan);
    }
}
