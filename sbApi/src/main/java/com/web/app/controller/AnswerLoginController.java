package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.domain.Response;
import com.web.app.domain.AnswerLogin.UpdRepliesDataParameter;
import com.web.app.service.AnswerLoginService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * S11_回答登録画面
 * Controller層
 * AnswerLoginController
 * 
 * @author DUC 張明慧
 * @since 2024/05/14
 * @version 1.0
 */
@CrossOrigin(origins = "*")
// ラベルを「回答登録画面」と指定する
@Api(tags = "回答登録画面")
@RestController
@RequestMapping("/AnswerLogin")
public class AnswerLoginController {
    @Autowired
    AnswerLoginService answerLoginService;

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
                    return AjaxResult.success("更新失敗!");
                }
                return AjaxResult.success("更新成功!");
            } catch (Exception e) {
                AjaxResult.fatal("更新失敗!", e);
                return null;
            }
        } else {
            return AjaxResult.error("セッションを取得しない。");
        }
    }
}
