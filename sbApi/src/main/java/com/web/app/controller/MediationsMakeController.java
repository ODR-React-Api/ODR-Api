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
            int id;
            // 「調停案」、「案件-添付ファイル」、「添付ファイル」テーブルを関連付けてデータが存在するかどうかを判断する
            ArrayList<InsMediationsData> dataSearch = mediationsMakeService.dataSearch(insMediationsData);

            // 表関連データが存在しない場合
            if (dataSearch.isEmpty()) {
                // 「調停案」にデータが存在するかどうかを判断する（ファイルが添付されていない場合がある）
                InsMediationsData mediationsCount = mediationsMakeService.mediationDataCount(insMediationsData);
                //「調停案」データが存在する場合
                if ( mediationsCount != null) {
                    //調停案データ更新API
                    id = 1;
                }else{
                    //「調停案」は、レコード新規登録（insert）で行う
                    int insMediationsData2 = mediationsMakeService.insMediationsData2(insMediationsData);
                    id=2;
                }
            } else {
                id = 3;
            }
            return id;
        } catch (Exception e) {
            return 0;
        }

    }
}
