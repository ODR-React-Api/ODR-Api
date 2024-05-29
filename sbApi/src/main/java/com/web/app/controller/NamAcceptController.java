package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.NamAccept.UpdMediatorHistories;
import com.web.app.domain.constants.Constants;
import com.web.app.service.NamAcceptService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * S33_指名受理画面
 * Controller層
 * NamAcceptController
 * 
 * @author DUC 閆文静 耿浩哲
 * @since 2024/05/08
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "指名受理画面")
@RestController
@RequestMapping("/NamAccept")
public class NamAcceptController {

    @Autowired
    private NamAcceptService namAcceptService;

    @SuppressWarnings("rawtypes")
    @ApiOperation("案件更新")
    @GetMapping("/updCaseStatusForAccept")
    public Response updCaseStatusForAccept(String caseId) {
        int result;
        try {
            // 申立状態を更新
            // 調停人履歴レコードを更新
            int updMedHisCount = namAcceptService.updCaseStatusForAccept(caseId);
            // 更新成功：戻り値
            if(updMedHisCount > 0){
                result = 0;
                return AjaxResult.success("更新成功!", result);  
            }else{
                result = 1;
                return AjaxResult.success("更新失败!", result); 
            }  
        } catch (Exception e) {
            AjaxResult.fatal("更新失败!", e);
            // 更新失败：戻り値
            result = 1;
            return AjaxResult.success("更新失败!", result); 
        }
    }

    /**
     * 調停人変更履歴変更API
     *
     * @param UpdMediatorHistories 調停人変更履歴変更オブジェクト
     * @return に答える
     * @throws Exception エラーの説明内容
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("調停人変更履歴変更API")
    @PostMapping("/updMediatorHistories")
    public Response updMediatorHistories(@RequestBody UpdMediatorHistories updMediatorHistories) {
        try {
            int updMediatorHistoriesNum = namAcceptService.UpdMediatorHistories(updMediatorHistories);
    
            if(updMediatorHistoriesNum == 1) {
                return Response.success(Constants.RETCD_OK);
            }
            return Response.error(Constants.RETCD_NG);
        } catch (Exception e) {
            return Response.error(e.getMessage());
        }
    }

}
