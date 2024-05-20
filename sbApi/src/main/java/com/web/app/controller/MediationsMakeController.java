package com.web.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.domain.Response;
import com.web.app.domain.mediationsMake.ResultMediation;
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
    @SuppressWarnings("unchecked")
    public Response<ResultMediation> returnMediationsDetails(@RequestBody ResultMediation resultMediation){
        try {
            //DBデータ取得
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
    @ApiOperation("調停案データ更新")
    @PostMapping("/saveMediton")
    public Response SaveMediton(@RequestBody ResultMediation resultMediation, HttpServletRequest request,HttpServletResponse response){
        try {
            if (mediationsMakeService.isExistMediations(resultMediation.getMediationId()) != 0) {
                mediationsMakeService.saveMediton(resultMediation);
                return AjaxResult.success("調停案が更新されました!");
            }else{
                return AjaxResult.success("調停案が更新されていません!");
            }
        } catch (Exception e) {
            AjaxResult.fatal("更新に失敗しました!", e);
            return null;
        }
    }
}
