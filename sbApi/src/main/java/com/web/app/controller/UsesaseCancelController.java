package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.domain.Response;
import com.web.app.domain.constants.Constants;
import com.web.app.service.UsesaseCancelService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 手続き中止画面 Controller
 * 
 * @author DUC 徐義然
 * @since 2024/05/16
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "手続き中止画面") 
@RestController
@RequestMapping("/usesaseCancel")
public class UsesaseCancelController {


    //サービスオブジェクト
    @Autowired
    private UsesaseCancelService usesaseCancelService;
    
    /**
     * 
     * API_ID:手続き中止API
     * 
     * 手続き中止を行う
     * 案件ステージ、案件ステータス、手続き中止日を更新する。
     * 
     * @param mediationId:セッション.案件ID
     * @return 結果コード 0:成功 1:失敗
     */
    @ApiOperation("手続き中止")
    @GetMapping("/updCaseCancelDate")
    @SuppressWarnings("unchecked")
    public Response<Integer> updCaseCancelDate(String mediationId){
        try {
            if(usesaseCancelService.updCaseCancelDate(mediationId) > 0){
                return AjaxResult.success("中止手続き成功", Constants.RESULT_CODE_SUCCESS);
            }else{
                return AjaxResult.success("中止手続き失敗", Constants.RESULT_CODE_ERROR);
            }
        } catch (Exception e) {
            //更新失敗
            return AjaxResult.fatal("手続き中止異常", e);
        }
    }
}
