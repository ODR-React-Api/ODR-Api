package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.MedUserChange.InsertFileInfo;
import com.web.app.domain.MedUserChange.UpdMediatorHistoriesChangeable;
import com.web.app.domain.constants.Constants;
import com.web.app.domain.constants.MessageConstants;
import com.web.app.service.MedUserChangeService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 調停人変更画面Controller
 * 
 * @author DUC 李健,耿浩哲
 * @since 2024/04/29
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "調停人変更画面")
@RestController
@RequestMapping("/MedUserChange")
public class MedUserChangeController {

    @Autowired
    private MedUserChangeService medUserChangeService;

    /**
     * API_調停案削除
     * 
     * @since 2024/04/24
     * @param caseId セッション.案件ID
     * @return true false
     * @throws Exception
     */
    @ApiOperation("調停案削除")
    @GetMapping("/delAboutCasesMediations")
    @SuppressWarnings({ "rawtypes" })
    public Response delAboutCasesMediations(String caseId) throws Exception {
        try {
            int resultBoolean = medUserChangeService.delAboutCasesMediations(caseId);
            if (resultBoolean == 0) {
                return AjaxResult.error(MessageConstants.S25010E);
            }
            return AjaxResult.success(Constants.MSG_SUCCESS, resultBoolean);

        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 案件関連情報更新
     * 
     * @param caseId     セッション.案件ID
     * @param userType   1:申立人 2:相手方
     * @param withReason true:理由あり false:理由なし
     * @return
     * @throws Exception
     */
    @ApiOperation("案件関連情報更新")
    @GetMapping("/updAboutCasesInfo")
    @SuppressWarnings({ "rawtypes" })
    public Response updAboutCasesInfo(String caseId, String userType, Boolean withReason) throws Exception {
        try {
            int result = medUserChangeService.updAboutCasesInfo(caseId, userType, withReason);
            if (result == 0) {
                return AjaxResult.error(Constants.MSG_ERROR);
            }
            return AjaxResult.success(Constants.MSG_SUCCESS, result);
        } catch (Exception e) {
            AjaxResult.fatal(caseId, e);
            throw e;
        }
    }

    /**
     * ファイル関連情報更新API
     *
     * @param InsertFileInfo ファイル関連情報更新オブジェクト
     * @return に答える
     * @throws Exception エラーの説明内容
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("ファイル関連情報更新API")
    @PostMapping("/insertFileInfo")
    public Response insertFileInfo(@RequestBody InsertFileInfo insertFileInfo) {
        try {
            int insertfileInfoNum = medUserChangeService.insertFileInfo(insertFileInfo);
            if (insertfileInfoNum == 1) {
                return Response.success(Constants.RETCD_OK);
            }
            return Response.error(Constants.RETCD_NG);
        } catch (Exception e) {
            return Response.error(e.getMessage());
        }
    }

    /**
     * 調停人変更履歴更新API
     *
     * @param InsertFileInfo 調停人変更履歴更新オブジェクト
     * @return に答える
     * @throws Exception エラーの説明内容
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("調停人変更履歴更新API")
    @PostMapping("/updMediatorHistoriesChangeable")
    public Response updMediatorHistoriesChangeable(@RequestBody UpdMediatorHistoriesChangeable updMediatorHistoriesChangeable) {
        try {
            int updMediatorHistoriesChangeableNum = medUserChangeService.updMediatorHistoriesChangeable(updMediatorHistoriesChangeable);
            if(updMediatorHistoriesChangeableNum != 0) {
                return Response.success(Constants.RETCD_OK);
            }
            return Response.error(Constants.RETCD_NG);
        } catch (Exception e) {
            return Response.error(e.getMessage());
        }
    }
}
