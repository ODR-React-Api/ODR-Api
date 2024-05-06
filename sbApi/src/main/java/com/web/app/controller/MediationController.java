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
import com.web.app.service.MediationService;;

@CrossOrigin(origins = "*")
@Api(tags = "調停案ステータス取得")
@RestController
@RequestMapping("/mediate")
public class MediationController {
    @Autowired
    private MediationService mediationService;

    @ApiOperation("調停案ステータス取得API")
    @PostMapping("/Mediationstatus")
    public int Mediationstatus(@RequestBody MediateUser mediateUser) {
        try {
            int num = mediationService.Mediationstatus(mediateUser);
            return num;
        } catch (Exception e) {
            return 0;
        }
    }
}
