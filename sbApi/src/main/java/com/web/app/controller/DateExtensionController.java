package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.DateExtension.DateExtension;
import com.web.app.service.DateExtensionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Api(tags = "期日延長画面")
@RestController
@RequestMapping("/DateExtension")
public class DateExtensionController {


    @Autowired
    private DateExtensionService dateExtensionService;

    @SuppressWarnings("rawtypes")
    @ApiOperation("案件情報取得API")
    @PostMapping("/getCaseInfo")
    public Response getCaseInfo(@RequestBody DateExtension dateExtension) {
        dateExtension.setNegotiationEndDate(dateExtensionService.getCaseInfo(dateExtension.getCaseId(), dateExtension.getPlatformId()));
        return Response.success(dateExtension);
    }
}
