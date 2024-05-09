package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.NegotiatPreview.CaseNegotiations;
import com.web.app.domain.NegotiatPreview.File;
import com.web.app.domain.NegotiatPreview.NegotiationsData;
import com.web.app.service.NegotiatPreview.NegotiatPreviewService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin(origins = "*")
@Api(tags = "和解案提出")
@RestController
@RequestMapping("/SettlementPlan")
public class NegotiatPreviewController {
    @Autowired
    private NegotiatPreviewService negotiatPreviewService;

    @ApiOperation("和解案提出登録")
    @PostMapping("AddSettlementPlan")
    //@SuppressWarnings("")
    public Response NegotiationData(CaseNegotiations caseNegotiations, File file){
        try{
            negotiatPreviewService.SearchCaseNegotiations(caseNegotiations, file);
            return AjaxResult.success("和解案提出成功!");
        }catch (Exception e){
            AjaxResult.fatal("和解案提出失败!", e);
            return null;
        }
    }

    // //和解案、文件、案件文件关联，三个表添加
    // public void InsNegotiationData(CaseNegotiations caseNegotiations, File file){
    //     addSettlementPlanService.AddSettlementPlan(addSettlementPlan);
    //     addSettlementPlanService.AddFile(addSettlementPlan);
    //     addSettlementPlanService.AddCaseFileRelations(addSettlementPlan);
    // }

    // public void UpdNegotiationsData(NegotiationsData addSettlementPlan){
    //     addSettlementPlanService.UpCaseNegotiations(addSettlementPlan);
    //     addSettlementPlanService.UpFile(addSettlementPlan);
    //     addSettlementPlanService.UpCaseFileRelations(addSettlementPlan);
    // }
}
