package com.web.app.service.SettlementPlan;
import com.web.app.domain.SettlementPlan.AddSettlementPlan;
import com.web.app.domain.SettlementPlan.CaseNegotiations;

public interface AddSettlementPlanService {
    CaseNegotiations SearchCaseNegotiations(String caseId);
    int AddSettlementPlan(AddSettlementPlan AddSettlementPlan);
    int AddFile(AddSettlementPlan addSettlementPlan);
    int AddCaseFileRelations(AddSettlementPlan addSettlementPlan);
    int UpCaseNegotiations(AddSettlementPlan addSettlementPlan);
    int UpFile(AddSettlementPlan addSettlementPlan);
    int UpCaseFileRelations(AddSettlementPlan addSettlementPlan);
}
