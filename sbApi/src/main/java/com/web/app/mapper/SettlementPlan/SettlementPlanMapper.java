package com.web.app.mapper.SettlementPlan;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.SettlementPlan.AddSettlementPlan;
import com.web.app.domain.SettlementPlan.CaseNegotiations;

@Mapper
public interface SettlementPlanMapper {
    CaseNegotiations SearchCaseNegotiations(String caseId);
    int AddSettlementPlan(AddSettlementPlan addSettlementPlan);
    int AddFile(AddSettlementPlan addSettlementPlan);
    int AddCaseFileRelations(AddSettlementPlan addSettlementPlan);

    int UpCaseNegotiations(AddSettlementPlan addSettlementPlan);
    int UpFile(AddSettlementPlan addSettlementPlan);
    int UpCaseFileRelations(AddSettlementPlan addSettlementPlan);
}
