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
import com.web.app.domain.MediateUser;
import com.web.app.service.MedUserConfirmService;

/**
 * 調停案ステータス取得
 * 調停人メール取得
 * 調停人情報取得
 * 
 * @author DUC jiawenzhi
 * @since 2024/05/10
 * @version 1.0
 */

@CrossOrigin(origins = "*")
@Api(tags = "調停人確認模块")
@RestController
@RequestMapping("/MedUserConfirm")
public class MedUserConfirmController {
    @Autowired
    private MedUserConfirmService mediationService;

    /**
     * 
     * 調停案ステータス取得
     * 
     * @param mediateUser 受付カウンターからの案件ID
     * @return 調停案ステータスを取得する
     * @throws Exception 調整案ステータス取得失敗
     */
    @ApiOperation("調停案ステータス取得")
    @PostMapping("/GetMediationStatus")
    public String GetMediationStatus(@RequestBody String CaseId) {
        try {
            // 調停案ステータスを取得
            String MediationStatus = mediationService.Mediationstatus(CaseId);
            // 調停案ステータス
            return MediationStatus;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 
     * 調停人メール取得
     * 
     * @param mediateUser
     * @return 調停者メールボックスとユーザーID
     * @throws Exception 調停者メール取得失敗
     */
    @ApiOperation("調停人メール取得")
    @PostMapping("/GetUserIDbyMail")
    public MediateUser GetUserIDbyMail(@RequestBody String CaseId) {
        try {
            //調停人メール取得
            MediateUser GetUserIDbyMail = mediationService.GetUserIDbyMail(CaseId);
            //調停者メールボックスとユーザーID
            return GetUserIDbyMail;   
        } catch(Exception e) {
            return null;
        }
    }

    @ApiOperation("調停人情報取得")
    @PostMapping("/MediatorIntelligence")
    public ArrayList<MediateUser> MediatorIntelligence(@RequestBody MediateUser mediateUser) {
        try {
            ArrayList<MediateUser> MediatorIntelligence = mediationService.MediatorIntelligence(mediateUser);
            return MediatorIntelligence;
        } catch (Exception e) {
            return null;
        }
    }
}
