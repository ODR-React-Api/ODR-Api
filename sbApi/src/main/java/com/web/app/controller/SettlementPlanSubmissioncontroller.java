package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.SettlementPlan.AddSettlementPlan;
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


    @ApiOperation("和解案提出登録")
    @PostMapping("AddSettlementPlan")
    //@SuppressWarnings("")
    public Response addSettlementPlan(@RequestBody AddSettlementPlan addSettlementPlan){
        try{
            addSettlementPlanService.AddSettlementPlan(addSettlementPlan);
            return AjaxResult.success("和解案提出成功!");
        }catch (Exception e){
            AjaxResult.fatal("和解案提出失败!", e);
            return null;
        }
    }
}
