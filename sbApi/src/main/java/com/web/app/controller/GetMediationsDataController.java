package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.web.app.domain.getMediationsData.ResultMediation;
import com.web.app.service.GetMediationsDataService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;


/**
 * 調停案データ取得API Controller
 * 
 * @author DUC 徐義然
 * @since 2024/05/07
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "調停案データ取得") 
@RestController
@RequestMapping("/mediationsMake")
public class GetMediationsDataController {

    //サービスオブジェクト
    @Autowired
    private GetMediationsDataService getMediationsDataService;

    /**
     * 
     * 当案件の調停案下書きデータを検索し、
     * 画面項目へロードする
     * 
     * @param ResultMediation 画面に存在する要素
     * @return 画面用データ
     */
    @PostMapping("/getMediationsData")
    public ResultMediation returnMediationsDetails(@RequestBody ResultMediation resultMediation){
        try {
            //DBデータ取得
            resultMediation = getMediationsDataService.getResultMediation(resultMediation);
            return resultMediation;
        } catch (Exception e) {
            AjaxResult.fatal("取得に失敗しました!", e);
            return null;
        }
    }
}
