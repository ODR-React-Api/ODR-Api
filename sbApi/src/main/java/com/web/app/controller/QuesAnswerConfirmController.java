package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.domain.Response;
import com.web.app.domain.QuesAnswerConfirm.InsQuestionnaireResults;
import com.web.app.service.QuesAnswerConfirmService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * C09_アンケート回答確認画面
 * Controller層
 * QuesAnswerConfirmController
 * 
 * @author DUC zzy
 * @since 2024/05/27
 * @version 1.0
 */
@CrossOrigin(origins = "*")

@Api(tags = "アンケート回答確認画面")
@RestController
@RequestMapping("/QuesAnswerConfirm")
public class QuesAnswerConfirmController {
    @Autowired
    private QuesAnswerConfirmService quesAnswerConfirmService;

    @ApiOperation("アンケート入力結果新規登録")
    @PostMapping("/InsQuestionnairesResults")
    @SuppressWarnings("rawtypes")
    public Response insQuestionnairesResults(@RequestBody InsQuestionnaireResults insQuestionnaireResults) {
        try {
            int res = quesAnswerConfirmService.InsQuestionnairesResults(insQuestionnaireResults);
            if (res == 0) {
                return AjaxResult.success("登録失敗!");
            }
            return AjaxResult.success("登録成功!");
        } catch (Exception e) {
            AjaxResult.fatal("登録失敗!", e);
            return null;
        }
    }
}
