package com.web.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.AnswerLogin.PetitionsData;
import com.web.app.domain.AnswerLogin.UpdRepliesDataParameter;
import com.web.app.domain.constants.Constants;
import com.web.app.service.AnswerLoginService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * S11_回答登録画面
 * Controller層
 * AnswerLoginController
 * 
 * @author DUC 張明慧 王大安
 * @since 2024/4/25
 * @version 1.0
 */
@CrossOrigin(origins = "*")
// ラベルを「回答登録画面」と指定する
@Api(tags = "回答登録画面")
@RestController
@RequestMapping("/AnswerLogin")
public class AnswerLoginController {

    @Autowired
    private AnswerLoginService answerLoginService;

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
    public Response getPetitionsData(@RequestParam("caseId") String caseId,
            @RequestParam("plateFormId") String plateFormId) {
        try {
            List<PetitionsData> list;
            list = answerLoginService.getPetitionData(caseId, plateFormId);
            return AjaxResult.success(Constants.AJAXRESULT_SUCCESS, list);
        } catch (Exception e) {
            AjaxResult.fatal("error", e);
            return null;
        }
    }

    /**
     * API_プラットフォ情報取得
     *
     * @param plateFormId 画面のプラットフォームID
     * @return プラットフォ情報
     */
    @ApiOperation("案件別個人情報リレーションデータ取得(申立人)")
    @PostMapping("/getPetitionDataUser")
    @SuppressWarnings("rawtypes")
    public Response GetPetitionDataUser(@RequestParam String plateFormId) {
        try {
            return AjaxResult.success(Constants.AJAXRESULT_SUCCESS,
                    answerLoginService.getPetitionDataUser(plateFormId));
        } catch (Exception e) {
            AjaxResult.fatal("error", e);
            return null;
        }
    }

    /**
     * API_反訴・回答データ新規登録/更新
     * 「下書き保存」ボタンを押下で、画面入力したデータをDBへ登録/更新を行う
     * 
     * @param updRepliesDataParameter API_反訴・回答データ新規登録/更新の引数
     * @return Response 反訴・回答データ新規登録/更新の状況
     */
    @ApiOperation("反訴・回答データ新規登録/更新")
    @PostMapping("/UpdRepliesData")
    @SuppressWarnings("rawtypes")
    public Response updRepliesData(@RequestBody UpdRepliesDataParameter updRepliesDataParameter) {
        // セッションのプラットフォームID
        String platformId = updRepliesDataParameter.getPlatformId();
        // セッション情報のCaseId
        String caseId = updRepliesDataParameter.getCaseId();
        if (caseId != null && platformId != null) {
            try {
                // 反訴・回答データ新規登録/更新
                int res = answerLoginService.UpdRepliesData(updRepliesDataParameter);
                if (res == 0) {
                    return AjaxResult.success("更新失败!");
                }
                return AjaxResult.success("更新成功!");
            } catch (Exception e) {
                AjaxResult.fatal("更新失败!", e);
                return null;
            }
        } else {
            return AjaxResult.error("セッションを取得しない。");
        }
    }
}
