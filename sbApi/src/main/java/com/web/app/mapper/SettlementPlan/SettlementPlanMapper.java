package com.web.app.mapper.SettlementPlan;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.SettlementPlan.AddSettlementPlan;

@Mapper
public interface SettlementPlanMapper {
    int AddSettlementPlan(AddSettlementPlan addSettlementPlan);
}
