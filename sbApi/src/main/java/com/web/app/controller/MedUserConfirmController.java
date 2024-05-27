package com.web.app.controller;

import com.web.app.domain.Response;
import com.web.app.domain.MedUserConfirm.MedUserConfirmSession;
import com.web.app.domain.MedUserConfirm.MediatorInfo;
import com.web.app.domain.MedUserConfirm.OdrUsers;
import com.web.app.domain.constants.Constants;
import com.web.app.service.MedUserConfirmService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 調停人確認画面
 * 
 * @author DUC 馬芹
 * @since 2024/05/17
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "調停人確認画面")
@RestController
@RequestMapping("/medUserConfirm")
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
            return AjaxResult.fatal(Constants.MSG_ERROR, e);
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
            return AjaxResult.fatal(Constants.MSG_ERROR, e);
        }

    }
}
