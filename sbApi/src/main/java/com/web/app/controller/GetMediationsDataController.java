package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.web.app.domain.ResultMediation;
import com.web.app.service.GetMediationsDataService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;

@CrossOrigin(origins = "*")
@Api(tags = "調停案データ取得") 
@RestController
@RequestMapping("/mediationsMake")
public class GetMediationsDataController {

    @Autowired
    private GetMediationsDataService getMediationsDataService;

    @PostMapping("/getMediationsData")
    public ResultMediation returnMediationsDetails(@RequestBody ResultMediation resultMediation){
        try {
            resultMediation = getMediationsDataService.getResultMediation(resultMediation);
            return resultMediation;
        } catch (Exception e) {
            AjaxResult.fatal("获取失败!", e);
            return null;
        }
    }
}
