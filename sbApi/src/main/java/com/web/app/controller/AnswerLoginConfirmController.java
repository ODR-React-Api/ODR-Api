package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.domain.Response;
import com.web.app.domain.AnswerLoginConfirm.UpdCases;
import com.web.app.domain.AnswerLoginConfirm.UpdCasesRelations;
import com.web.app.service.AnswerLoginConfirmService;
import com.web.app.tool.AjaxResult;
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
    @SuppressWarnings("rawtypes")
    @ApiOperation("代理人更新処理")
    @PostMapping("/UpdCasesRelations")
    public Response updCasesRelations(@RequestBody UpdCasesRelations caserelations) { 
        try {
            // 代理人更新成功 個数
            int count = answerLoginConfirmService.updateCaserelations(caserelations);
            if (count > 0) {
                return AjaxResult.success("代理人更新成功!", count);
            }
            else{
                return AjaxResult.success("更新がありません(代理人更新)。",null);
            }
        } catch (Exception e) {
            AjaxResult.fatal("代理人更新失敗!", e);
            return null;
        }
    }

    /**
     * 案件状態更新処理
     * API_ 案件更新
     * S12画面に 登録するボタン押下後、案件状態更新処理
     * 
     * @return 更新した個数
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("案件状態更新処理")
    @PostMapping("/UpdCases")
    public Response updCases(@RequestBody UpdCases casecase) {
        try {
            //条件によって、案件状態更新した個数
            int count = answerLoginConfirmService.updateCasecase(casecase);
            if (count > 0) {
                return AjaxResult.success("案件状態更新!", count);
            }
            else{
                return AjaxResult.success("更新がありません(案件状態更新)。",null);
            }
        } catch (Exception e) {
            AjaxResult.fatal("案件状態失敗!", e);
            return null;
        }
    }
}
