package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.web.app.domain.MediateUser;
import com.web.app.service.MediationService;

@CrossOrigin(origins = "*")
@Api(tags = "調停案ステータス取得")
@RestController
@RequestMapping("/mediate")
public class MediationController {
    @Autowired
    private MediationService mediationService;

    @ApiOperation("調停案ステータス取得API")
    @PostMapping("/Mediationstatus")
    public MediateUser Mediationstatus(@RequestBody MediateUser mediateUser) {
        try {
            MediateUser num = mediationService.Mediationstatus(mediateUser);
            return num;
        } catch (Exception e) {
            return null;
        }
    }

    @ApiOperation("調停人メール取得")
    @PostMapping("/MediationEmail")
    public MediateUser MediationEmail(@RequestBody MediateUser mediateUser) {
        MediateUser mediationemail = mediationService.MediationEmail(mediateUser);
        return mediationemail;
    }

}
