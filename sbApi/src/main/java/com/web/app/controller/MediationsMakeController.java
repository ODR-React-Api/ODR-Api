package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.domain.Response;
import com.web.app.domain.mediationsMake.ResultMediation;
import com.web.app.domain.constants.Constants;
import com.web.app.service.MediationsMakeService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 調停案作成画面
 * 
 * @author DUC 賈文志
 * @since 2024/05/24
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
    @SuppressWarnings("unchecked")
    public Response<ResultMediation> returnMediationsDetails(@RequestBody ResultMediation resultMediation) {
        try {
            // DBデータ取得
            resultMediation = mediationsMakeService.getMediationsData(resultMediation);
            return AjaxResult.success("調停案データ取得しました", resultMediation);
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
    @SuppressWarnings("rawtypes")
    @ApiOperation("調停案データ更新")
    @PostMapping("/saveMediton")
    public Response SaveMediton(@RequestBody ResultMediation resultMediation) {
        try {
            if (mediationsMakeService.isExistMediations(resultMediation.getMediationId()) != 0) {
                mediationsMakeService.saveMediton(resultMediation);
                return AjaxResult.success("調停案が更新されました!");
            } else {
                // 調停案データ新規登録
               int insMediationsData = mediationsMakeService.insMediationsData(resultMediation);
                if (insMediationsData == Constants.NUM_1) {
                    return AjaxResult.success("調停案データ新規登録成功");
                }else{
                    return AjaxResult.success("調停案データ新規登録失敗");
                }
            }
        } catch (Exception e) {
            AjaxResult.fatal("更新に失敗しました!", e);
            return null;
        }
    }
}