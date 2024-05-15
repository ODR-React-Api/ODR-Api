package com.web.app.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.mediationsMake.InsMediationsData;
import com.web.app.service.MediationsMakeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.web.app.domain.Entity.CaseMediations;

@CrossOrigin(origins = "*")
@Api(tags = "調停案作成模块")
@RestController
@RequestMapping("/MediationsMake")
public class MediationsMakeController {
    @Autowired
    private MediationsMakeService mediationsMakeService;

    @ApiOperation("調停案データ新規登録")
    @PostMapping("/InsMediationsData")
    public int InsMediationsData(@RequestBody InsMediationsData insMediationsData) {
        try {
            
            int InsMediationsData =mediationsMakeService.insMediationsData(insMediationsData); 

            return InsMediationsData;
        } catch (Exception e) {
            return 0;
        }

    }
}
