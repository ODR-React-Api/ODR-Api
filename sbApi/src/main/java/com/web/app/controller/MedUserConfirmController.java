package com.web.app.controller;

import java.util.ArrayList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Entity.Cases;
import com.web.app.domain.MedUserConfirm.GetMediatorGen;
import com.web.app.domain.MedUserConfirm.GetUserIDbyMail;
import com.web.app.domain.MedUserConfirm.MedUserConfirm;
import com.web.app.domain.Response;
import com.web.app.domain.MedUserConfirm.MedUserConfirmSession;
import com.web.app.domain.MedUserConfirm.MediatorInfo;
import com.web.app.domain.MedUserConfirm.OdrUsers;
import com.web.app.domain.constants.Constants;
import com.web.app.service.MedUserConfirmService;
import com.web.app.tool.AjaxResult;

/**
 * 調停人確認画面
 * 
 * @author DUC 李志文 馬芹 賈文志
 * @since 2024/05/06
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
     * 調停人ユーザ情報取得
     *
     * @param MedUserConfirmSession セッション情報
     * @return OdrUsers 調停人ユーザ情報
     * @throws Exception 異常終了
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("調停人ユーザ情報取得")
    @PostMapping("/getOdrUserInfo")
    public Response getOdrUserInfo(@RequestBody MedUserConfirmSession medUserConfirmSession) {
        try {
            OdrUsers odrUsers = medUserConfirmService.getOdrUserInfo(medUserConfirmSession);
            return AjaxResult.success(Constants.MSG_SUCCESS, odrUsers);
        } catch (Exception e) {
            AjaxResult.fatal(Constants.MSG_ERROR, e);
            return null;
        }
    }

    /**
     * 調停人の経験取得
     *
     * @param MedUserConfirmSession セッション情報
     * @return MediatorInfo 調停人の経験
     * @throws Exception 異常終了
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("調停人の経験取得")
    @PostMapping("/getMediatorInfo")
    public Response getMediatorInfo(@RequestBody MedUserConfirmSession medUserConfirmSession) {
        try {
            MediatorInfo mediatorInfo = medUserConfirmService.getMediatorInfo(medUserConfirmSession);
            return AjaxResult.success(Constants.MSG_SUCCESS, mediatorInfo);
        } catch (Exception e) {
            AjaxResult.fatal(Constants.MSG_ERROR, e);
            return null;
        }

    }

    /**
     * ファイル名取得
     *
     * @param NegotiatPreview セッション値
     * @return Response
     * @throws Exception 和解案提出失敗
     */
    @SuppressWarnings({"rawtypes"})
    @ApiOperation("ファイル名取得")
    @PostMapping("GetFileName")
    public Response GetFileName(@RequestBody MedUserConfirm medUserConfirm) {
        try {
            String fileName = medUserConfirmService.GetFileName(medUserConfirm.getFileId());
            return AjaxResult.success( "成功!",fileName);
        } catch (Exception e) {
            AjaxResult.fatal( "失敗!",e);
            return null;
        }
    }

    /**
     * 調停変更回数取得
     *
     * @param NegotiatPreview セッション値
     * @return Response
     * @throws Exception 和解案提出失敗
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("調停変更回数取得")
    @PostMapping("GetMediatorChangeableCount")
    public Response GetMediatorChangeableCount(@RequestBody MedUserConfirm medUserConfirm) {
        try {
            Cases cases = medUserConfirmService.SelCases(medUserConfirm.getCaseId());
            return AjaxResult.success( "成功!",cases);
        } catch (Exception e) {
            AjaxResult.fatal( "失敗!",e);
            return null;
        }
    }
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
