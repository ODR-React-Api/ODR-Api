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
import com.web.app.domain.medUserConfirm.GetUserIDbyMail;
import com.web.app.domain.medUserConfirm.GetMediatorGen;
import com.web.app.service.MedUserConfirmService;

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
    @ApiOperation("調停案ステータス取得")
    @PostMapping("/GetMediationStatus")
    public String GetMediationStatus(@RequestBody String CaseId) {
        try {
            // 調停案ステータスを取得
            String getMediationStatus = medUserConfirmService.getMediationStatus(CaseId);
            // 調停案ステータス
            return getMediationStatus;
        } catch (Exception e) {
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
    @ApiOperation("調停人メール取得")
    @PostMapping("/GetUserIDbyMail")
    public GetUserIDbyMail GetUserIDbyMail(@RequestBody String CaseId) {
        try {
            // 調停者メールとユザーIDを取得
            GetUserIDbyMail getUserIDbyMail = medUserConfirmService.getUserIDbyMail(CaseId);
            // 調停者メールとユザーID
            return getUserIDbyMail;
        } catch (Exception e) {
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
    @ApiOperation("調停人情報取得")
    @PostMapping("/GetMediatorGen")
    public ArrayList<GetMediatorGen> GetMediatorGen(@RequestBody String CaseId) {
        try {
            // 調停人情報取得
            ArrayList<GetMediatorGen> getMediatorGen = medUserConfirmService.getMediatorGen(CaseId);
            return getMediatorGen;
        } catch (Exception e) {
            return null;
        }
    }
}
