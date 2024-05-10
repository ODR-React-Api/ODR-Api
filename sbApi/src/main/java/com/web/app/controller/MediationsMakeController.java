package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.mediationsMake.ResultMediation;
import com.web.app.domain.mediationsMake.SaveMeditonData;
import com.web.app.service.MediationsMakeService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;


/**
 * 調停案作成画面 Controller
 * 
 * @author DUC 徐義然
 * @since 2024/05/07
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "調停案作成画面") 
@RestController
@RequestMapping("/mediationsMake")
@SuppressWarnings("rawtypes")
public class MediationsMakeController {

    //サービスオブジェクト
    @Autowired
    private MediationsMakeService mediationsMakeService;

    /**
     * 
     * API_ID:調停案データ取得
     * 
     * 当案件の調停案下書きデータを検索し、
     * 画面項目へロードする
     * 
     * @param ResultMediation 画面に存在する要素
     * @return 画面用データ
     */
    @ApiOperation("調停案データ取得")
    @PostMapping("/getMediationsData")
    public ResultMediation returnMediationsDetails(@RequestBody ResultMediation resultMediation){
        try {
            //DBデータ取得
            resultMediation = mediationsMakeService.getResultMediation(resultMediation);
            return resultMediation;
        } catch (Exception e) {
            AjaxResult.fatal("取得に失敗しました!", e);
            return null;
        }
    }

    /**
     * 
     * API_ID:調停案データ更新
     * 
     * 画面入力したデータをDBへ更新を行う
     * 
     * @return Response
     */
    @ApiOperation("調停案データ更新")
    @PostMapping("/saveMediton")
    public Response UpdateMediton(@RequestBody SaveMeditonData saveMeditonData){
        
        if (mediationsMakeService.isExistMediations(saveMeditonData.getMediationId()) != 0) {
            try {
                if (1 != 0) {
                    System.out.println("====================");
                    System.out.println("====和解案已更新!=====");
                    return AjaxResult.success("和解案已更新!");
                }
                System.out.println("====================");
                System.out.println("====和解案未更新!=====");
                return AjaxResult.success("和解案未更新!");
            } catch (Exception e) {
                System.out.println("====================");
                System.out.println("====和解案未更新!=====");
                AjaxResult.fatal("更新失败!", e);
                return null;
            }
        }
        return AjaxResult.success("请添加和解案!");
    }
}
