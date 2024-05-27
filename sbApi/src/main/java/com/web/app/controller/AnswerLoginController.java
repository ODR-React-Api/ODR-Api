package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.web.app.service.AnswerLoginService;
import com.web.app.domain.Response;
import com.web.app.domain.AnswerLogin.RepliesData;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;

/**
 * 回答登録画面 Controller
 * 
 * @author DUC 信召艶
 * @since 2024/04/25
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "回答登録画面")
@RestController
@RequestMapping("/AnswerLogin")
public class AnswerLoginController {

    @Autowired
    private AnswerLoginService answerLoginService;

    /**
     * API_ID:反訴・回答データ取得
     *
     * @param CaseId セッション情報のCaseId 
     * @param PlatformId セッション情報のプラットフォームID
     * @return userRepliesList
     * @throws Exception エラーの説明内容
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("反訴・回答データ取得")
    @GetMapping("/getRepliesData")
    public Response getRepliesData(String caseId, String platformId) {
        try {
            List<RepliesData> getRepliesDataList = new ArrayList<RepliesData>();
            getRepliesDataList = answerLoginService.getRepliesData(caseId, platformId);
            return AjaxResult.success("会員登録の取得に成功しました。", getRepliesDataList);
        } catch (Exception e) {
            AjaxResult.fatal("会員登録の取得に失敗しました!", e);
            return null;
        }
    }
}
