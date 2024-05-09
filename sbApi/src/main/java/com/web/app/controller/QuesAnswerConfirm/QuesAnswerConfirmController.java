package com.web.app.controller.QuesAnswerConfirm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.domain.Response;
import com.web.app.domain.QuesAnswerConfirm.InsQuestionnaireResults;
import com.web.app.service.UtilService;
import com.web.app.service.QuesAnswerConfirm.QuesAnswerConfirmService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * アンケート回答確認画面
 * Controller层
 * QuesAnswerConfirmController
 * API_アンケート入力結果新規登録
 * 
 * @author DUC 張明慧
 * @since 2024/04/14
 * @version 1.0
 */
@CrossOrigin(origins = "*")
// ラベルを「アンケート回答確認画面」と指定する
@Api(tags = "アンケート回答確認画面")
@RestController
@RequestMapping("/QuesAnswerConfirm")
public class QuesAnswerConfirmController {
    @Autowired
    private QuesAnswerConfirmService quesAnswerConfirmService;

    @Autowired
    private UtilService utilService;

    /**
     * 「送信する」ボタン押下で、アンケート回答登録処理を行う。
     * 2.1 メール送信 & アクション履歴記録
     * 2.2 アンケート回答内容を登録
     * 2.3 アクション履歴登録
     * 
     * @param insQuestionnaireResults アンケート回答登録処理の引数
     * @return Response アンケート回答登録処理の状況
     */
    @ApiOperation("アンケート入力結果新規登録")
    @PostMapping("/InsQuestionnairesResults")
    @SuppressWarnings("rawtypes")
    public Response insQuestionnairesResults(@RequestBody InsQuestionnaireResults insQuestionnaireResults) {
        // ID 自動生成GIUD
        insQuestionnaireResults.setId(utilService.GetGuid());
        // System.out.println("insQuestionnaireResults:" + insQuestionnaireResults);
        // TODO
        // 2.1 メール送信 & アクション履歴記録 送信先：【画面C08】.userEmail
        // 2.2 アンケート回答内容を登録---API_アンケート入力結果新規登録
        try {
            quesAnswerConfirmService.insQuestionnairesResults(insQuestionnaireResults);
            return AjaxResult.success("新規登録成功!");
        } catch (Exception e) {
            AjaxResult.fatal("新規登録失敗!", e);
            return null;
        }
        // TODO
        // 2.3 アクション履歴登録
    }
}
