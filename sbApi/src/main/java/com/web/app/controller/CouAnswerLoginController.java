package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.domain.Response;
import com.web.app.domain.CouAnswerLogin.UpdClaimRepliesDataParameter;
import com.web.app.service.CouAnswerLoginService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * S14_反訴回答登録画面
 * Controller層
 * CouAnswerLoginController
 * 
 * @author DUC 張明慧
 * @since 2024/05/02
 * @version 1.0
 */
@CrossOrigin(origins = "*")
// ラベルを「反訴回答登録画面」と指定する
@Api(tags = "反訴回答登録画面")
@RestController
@RequestMapping("/CouAnswerLogin")
public class CouAnswerLoginController {
    @Autowired
    private CouAnswerLoginService couAnswerLoginService;

    /**
     * API_反訴への回答データ新規登録/更新
     * 「下書き保存」ボタンを押下で、画面入力したデータをDBへ登録/更新を行う
     * 
     * @param updClaimRepliesDataParameter API_反訴への回答データ新規登録/更新の引数
     * @return Response 反訴への回答データ新規登録/更新の状況
     */
    @ApiOperation("反訴への回答データ新規登録/更新")
    @PostMapping("/UpdClaimRepliesData")
    @SuppressWarnings("rawtypes")
    public Response updClaimRepliesData(@RequestBody UpdClaimRepliesDataParameter updClaimRepliesDataParameter) {
        // セッションのプラットフォームID
        String platformId = updClaimRepliesDataParameter.getPlatformId();
        // セッション情報のCaseId
        String caseId = updClaimRepliesDataParameter.getCaseId();
        if (caseId != null && platformId != null) {
            try {
                // 反訴への回答データ新規登録/更新
                int res = couAnswerLoginService.UpdClaimRepliesData(updClaimRepliesDataParameter);
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
