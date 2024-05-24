package com.web.app.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.QuesAnswer.QuestionnaireMails;
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
@RequestMapping("/QuesAnswer")
@RestController
public class QuesAnswerController {

    @Autowired
    private QuesAnswerService quesAnswerService;

    @SuppressWarnings("rawtypes")
    @GetMapping("/getQuestionnaires")
    public Response getQuestionnaires(@RequestParam("id") String id, @RequestParam("platformId") String platformId) {
        try {
            // 確認画面用データ
            QuestionnaireMails allQuestionnaire = quesAnswerService.getQuestionnaires(id, platformId);

            return AjaxResult.success("Success",allQuestionnaire);

        } catch (Exception e) {
            return AjaxResult.fatal("Error",e);
        }
    }

}
