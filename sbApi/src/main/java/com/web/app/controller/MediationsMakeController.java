package com.web.app.controller;

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

/**
 * 調停案データ新規登録
 * 
 * @author DUC 賈文志
 * @since 2024/05/16
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "調停案作成模块")
@RestController
@RequestMapping("/MediationsMake")
public class MediationsMakeController {
    @Autowired
    private MediationsMakeService mediationsMakeService;

    /**
     * 
     * 調停案データ新規登録
     * 
     * @param insMediationsData 
     * @return 調停案データ新規登録成功
     * @throws Exception 調停案データ新規登録失敗
     */
    @ApiOperation("調停案データ新規登録")
    @PostMapping("/InsMediationsData")
    public int InsMediationsData(@RequestBody InsMediationsData insMediationsData) {
        try {
            //調停案データ新規登録
            int insmediationsData = mediationsMakeService.insMediationsData(insMediationsData);
            return insmediationsData;
        } catch (Exception e) {
            return 0;
        }
    }
}
