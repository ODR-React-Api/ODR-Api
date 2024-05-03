package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.UserIdentity.UserIdentity;
import com.web.app.service.UserIdentity.FindUserIdentityService;

import io.swagger.annotations.Api;

@CrossOrigin(origins = "*")
@Api(tags = "身份查询")
@RestController
@RequestMapping("/UserIdentity")
public class UserIdentityController {
    @Autowired
    private FindUserIdentityService findUserIdentityService;
    
    public UserIdentity FindUserIdentityService (String email){
        try{
        UserIdentity userIdentity = findUserIdentityService.FindUserIdentity(email);
        return userIdentity;
        }catch(Exception e){
            return null;
        }
    }
    
}
