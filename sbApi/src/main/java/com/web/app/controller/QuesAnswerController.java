package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.QuesAnswer.Questionnaire_Mails;
import com.web.app.service.QuesAnswerService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;

/**
 * アンケート回答画面
 * 
 * @author DUC 王亞テイ
 * @since 2024/04/23
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "アンケート情報取得")
@RestController
public class QuesAnswerController {

    @Autowired
    private QuesAnswerService getQuestionnairesService;

    @SuppressWarnings("rawtypes")
    @GetMapping("/getQuestionnaires")
    public Response getQuestionnaires(String Id, String PlatformId) {
        try {
            // 確認画面用データ
            Questionnaire_Mails allQuestionnaire = getQuestionnairesService.selectQuestionnaireData(Id, PlatformId);

            return AjaxResult.success("Success",allQuestionnaire);

        } catch (Exception e) {
            return AjaxResult.fatal("Error",e);
        }
    }

}
