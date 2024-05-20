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
 * @since 2024/05/20
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
     * @param insMediationsData フロントから転送されたデータを受信する
     * @return 調停案データ登録または更新状態 (1：調停案データ新規登録成功2：調停案データ更新成功)
     */
    @ApiOperation("調停案データ新規登録")
    @PostMapping("/InsMediationsData")
    public int InsMediationsData(@RequestBody InsMediationsData insMediationsData) {
        try {
            // 調停案データ新規登録
            int insmediationsData = mediationsMakeService.insMediationsData(insMediationsData);
            return insmediationsData;
        } catch (Exception e) {
            return 0;
        }
    }
}
