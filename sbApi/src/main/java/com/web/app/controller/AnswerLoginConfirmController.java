package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.domain.AnswerLoginConfirm.UpdCases;
import com.web.app.domain.AnswerLoginConfirm.UpdCasesRelations;
import com.web.app.service.AnswerLoginConfirmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * S12 回答内容確認画面
 * Controller層
 * AnswerLoginConfirmController
 * 
 * @author DUC 楊バイバイ
 * @since 2024/05/01
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "CASESについて")
@RestController
@RequestMapping("/AnswerLoginConfirm")
public class AnswerLoginConfirmController {

    @Autowired
    private AnswerLoginConfirmService answerLoginConfirmService;

    /**
     * 代理人更新処理
     * API_案件別個人情報リレーションデータ更新
     * S12画面に 登録するボタン押下後、代理人更新処理
     * 
     * @return 更新した個数
     */
    @ApiOperation("代理人更新処理")
    @PostMapping("/UpdCasesRelations")
    public String updCasesRelations(@RequestBody UpdCasesRelations caserelations) {

        int count = answerLoginConfirmService.updateCaserelations(caserelations);
        if (count > 0) {
          return count + "件を更新しました(代理人更新)。";
        }
        return "更新がありません(代理人更新)。";
    }

    /**
     * 案件状態更新処理
     * API_ 案件更新
     * S12画面に 登録するボタン押下後、案件状態更新処理
     * 
     * @return 更新した個数
     */
    @ApiOperation("案件状態更新処理")
    @PostMapping("/UpdCases")
    public String updCases(@RequestBody UpdCases casecase) {

        //条件によって、更新した個数
        int count = answerLoginConfirmService.updateCasecase(casecase);
        if (count > 0) {
          return count + "件を更新しました(案件状態更新)。";
        }
        return "更新がありません(案件状態更新)。";
    }
}
