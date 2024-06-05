package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Entity.Cases;
import com.web.app.service.DateExtensionService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/DateExtension")
public class DateExtensionController {
    
    @Autowired DateExtensionService dateExtensionService;

    @GetMapping("/GetCaseInfo")
    public Cases GetCaseInfo( String caseId, String platformId) {
        Cases cases=dateExtensionService.GetCaseInfo(caseId, platformId);
        return cases;
    }
    
}
