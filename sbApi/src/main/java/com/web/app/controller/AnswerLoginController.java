package com.web.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.AnswerLogin.PetitionsData;
import com.web.app.domain.constants.Constants;
import com.web.app.service.AnswerLoginService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 回答登録画面
 * 
 * @author DUC 王大安
 * @since 2024/4/25
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "回答登録画面")
@RestController
@RequestMapping("/getData")
public class AnswerLoginController {

    @Autowired
    private AnswerLoginService getPetitionsDataService;

    /**
     * 申立データ取得API
     *
     * @param caseId      画面の案件ID
     * @param plateFormId 画面のプラットフォームID
     * @return 申立データ取得結果
     */
    @ApiOperation("申立データ取得API")
    @PostMapping("/getPetitionsData")
    @SuppressWarnings("rawtypes")
    public Response getPetitionsData(String caseId, String plateFormId) {
        try {
            List<PetitionsData> list;
            list = getPetitionsDataService.getPetitionData(caseId, plateFormId);
            return AjaxResult.success(Constants.AJAXRESULT_SUCCESS,list);
        } catch (Exception e) {
            AjaxResult.fatal("error",e);
            return null;
        }
    }

    /**
     * 申立データ取得API
     *
     * @param plateFormId 画面のプラットフォームID
     * @return プラットフォ情報
     */
    @ApiOperation("案件別個人情報リレーションデータ取得(申立人)")
    @PostMapping("/getPetitionDataUser")
    @SuppressWarnings("rawtypes")
    public Response GetPetitionDataUser(String plateFormId) {
        try {
            return AjaxResult.success(Constants.AJAXRESULT_SUCCESS,getPetitionsDataService.getPetitionDataUser(plateFormId));
        } catch (Exception e) {
            AjaxResult.fatal("error",e);
            return null;
        }
    }
}
