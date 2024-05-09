package com.web.app.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Questionnaire_Mails;
import com.web.app.domain.Response;
import com.web.app.service.GetQuestionnairesService;

import io.swagger.annotations.Api;

@CrossOrigin(origins = "*")
@Api(tags = "アンケート情報取得")
@RestController
public class GetQuestionnairesController {

    @Autowired
    DataSource dataSource;

    @Autowired
    private GetQuestionnairesService questionnaireService;

    @SuppressWarnings("rawtypes")
    @GetMapping("/getQuestionnaires")
    public Response getQuestionnaires(String Id, String PlatformId) {

        // 確認画面用データ
        Questionnaire_Mails allQuestionnaire = questionnaireService.selectQuestionnaireData(Id, PlatformId);

        if (allQuestionnaire != null) {
            return Response.success(allQuestionnaire);
        }
        return Response.error("失敗");
    }

}
