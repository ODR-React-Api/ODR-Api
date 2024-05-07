package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.SettlementPlan.AddSettlementPlan;
import com.web.app.domain.SettlementPlan.CaseNegotiations;
import com.web.app.domain.UserIdentity.UserIdentity;
import com.web.app.service.SettlementPlan.AddSettlementPlanService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin(origins = "*")
@Api(tags = "和解案提出")
@RestController
@RequestMapping("/SettlementPlan")
public class SettlementPlanSubmissioncontroller {
    @Autowired
    private AddSettlementPlanService addSettlementPlanService;

    @Autowired
    private UserIdentityController userIdentityController;

    @ApiOperation("和解案提出登録")
    @PostMapping("AddSettlementPlan")
    //@SuppressWarnings("")
    public Response addSettlementPlan(@RequestBody AddSettlementPlan addSettlementPlan){
        try{
            //判断和解案是否保存过
            CaseNegotiations caseNegotiations = addSettlementPlanService.SearchCaseNegotiations(addSettlementPlan.getCaseId());
            if (caseNegotiations != null) {
                String status = UpStatus(caseNegotiations);
                addSettlementPlan.setStatus(status);
                addSettlementPlanService.UpCaseNegotiations(addSettlementPlan);
                addSettlementPlanService.UpFile(addSettlementPlan);
                addSettlementPlanService.UpCaseFileRelations(addSettlementPlan);
                add(addSettlementPlan);
            } else {
                //Status：申立人15 相手方2
                String status = userIdentityController.FindUserIdentityService(addSettlementPlan);
                addSettlementPlan.setStatus(status);
                add(addSettlementPlan);
            }
            return AjaxResult.success("和解案提出成功!");
        }catch (Exception e){
            AjaxResult.fatal("和解案提出失败!", e);
            return null;
        }
    }

    //判断更新和解案表中的status的值
    public String UpStatus(CaseNegotiations caseNegotiations){
        if (caseNegotiations.getStatus().equals("0") ||
            caseNegotiations.getStatus().equals("1") ||
            caseNegotiations.getStatus().equals("2")) 
        {
            return "2";
        } 
        else if (caseNegotiations.getStatus().equals("7") ||
            caseNegotiations.getStatus().equals("8") ||
            caseNegotiations.getStatus().equals("9")) 
        {
            return "9";
        }
        else if (caseNegotiations.getStatus().equals("10") ||
            caseNegotiations.getStatus().equals("11") ||
            caseNegotiations.getStatus().equals("12"))
        {
            return "12";
        }
        else if(caseNegotiations.getStatus().equals("13") ||
            caseNegotiations.getStatus().equals("14") ||
            caseNegotiations.getStatus().equals("15")) 
        {
            return "15";
        }
        else
        {
            return caseNegotiations.getStatus();
        }

    }

    //和解案、文件、案件文件关联，三个表添加
    public void add(AddSettlementPlan addSettlementPlan){
        addSettlementPlanService.AddSettlementPlan(addSettlementPlan);
        addSettlementPlanService.AddFile(addSettlementPlan);
        addSettlementPlanService.AddCaseFileRelations(addSettlementPlan);
    }
}
