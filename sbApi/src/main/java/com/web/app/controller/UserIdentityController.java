package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.NegotiatPreview.NegotiationsData;
import com.web.app.service.UserIdentity.FindUserIdentityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Api(tags = "身份查询")
@RestController
@RequestMapping("/UserIdentity")
public class UserIdentityController {
    @Autowired
    private FindUserIdentityService findUserIdentityService;
    
    @ApiOperation("用户身份查询")
    @PostMapping("FindUserIdentity")
    public String FindUserIdentityService (NegotiationsData addSettlementPlan){
        try{
            String status = findUserIdentityService.FindUserIdentity(addSettlementPlan);
            return status;
        }catch(Exception e){
            return null;
        }
    }
    
}
