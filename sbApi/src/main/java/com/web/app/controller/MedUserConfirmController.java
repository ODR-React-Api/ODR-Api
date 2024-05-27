package com.web.app.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.web.app.domain.Response;
import com.web.app.domain.MedUserConfirm.GetMediatorGen;
import com.web.app.domain.MedUserConfirm.GetUserIDbyMail;
import com.web.app.service.MedUserConfirmService;
import com.web.app.tool.AjaxResult;

/**
 * 調停案ステータス取得
 * 調停者メールとユザーIDを取得
 * 調停人情報取得
 * 
 * @author DUC 賈文志
 * @since 2024/05/20
 * @version 1.0
 */

@CrossOrigin(origins = "*")
@Api(tags = "調停人確認模块")
@RestController
@RequestMapping("/MedUserConfirm")
public class MedUserConfirmController {
    @Autowired
    private MedUserConfirmService medUserConfirmService;

    /**
     *                
     * 調停案ステータス取得
     * 
     * @param CaseId 受付カウンターからの案件ID
     * @return 調停案ステータスを取得する
     * @throws Exception 調整案ステータス取得失敗
     */

    @SuppressWarnings("rawtypes")
    @ApiOperation("調停案ステータス取得")
    @PostMapping("/GetMediationStatus")
    public Response GetMediationStatus(@RequestBody String CaseId) {
        try {
            // 調停案ステータスを取得
            String getMediationStatus = medUserConfirmService.getMediationStatus(CaseId);
            // 調停案ステータス
            return AjaxResult.success("調停案ステータス取得成功", getMediationStatus);
        } catch (Exception e) {
            AjaxResult.fatal("調停案ステタス取得異常", e);
            return null;
        }
    }

    /**
     * 
     * 調停者メールとユザーIDを取得
     * 
     * @param CaseId 受付カウンターからの案件ID
     * @return 調停者メールとユザーID
     * @throws Exception 調停者メールとユザーIDを取得失敗
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("調停人メール取得")
    @PostMapping("/GetUserIDbyMail")
    public Response GetUserIDbyMail(@RequestBody String CaseId) {
        try {
            // 調停者メールとユザーIDを取得
            GetUserIDbyMail getUserIDbyMail = medUserConfirmService.getUserIDbyMail(CaseId);
            // 調停者メールとユザーID
            return AjaxResult.success("調停者メールとユザーIDを取得成功", getUserIDbyMail);
        } catch (Exception e) {
            AjaxResult.fatal("調停者メールとユザーIDを取得異常", e);
            return null;
        }
    }

    /**
     * 
     * 調停人情報取得
     * 
     * @param CaseId 受付カウンターからの案件ID
     * @return 調停人情報取得
     * @throws Exception 調停人情報取得失敗
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("調停人情報取得")
    @PostMapping("/GetMediatorGen")
    public Response GetMediatorGen(@RequestBody String CaseId) {
        try {
            // 調停人情報取得
            ArrayList<GetMediatorGen> getMediatorGen = medUserConfirmService.getMediatorGen(CaseId);
            return AjaxResult.success("調停人情報取得成功", getMediatorGen);
        } catch (Exception e) {
            AjaxResult.fatal("調停人情報取得成功", e);
            return null;
        }
    }
}
