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
 * Controller层
 * CouAnswerLoginController
 * API_反訴への回答データ更新
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
     * API_反訴への回答データ更新
     * 「下書き保存」ボタンを押下で、下書きデータ存在する場合、下記のAPIをコールし、画面入力したデータをDBへ更新を行う
     * 
     * @param updClaimRepliesDataParameter API_反訴への回答データ更新の引数
     * @return Response 反訴への回答データ更新の状況
     */
    @ApiOperation("反訴への回答データ更新")
    @PostMapping("/UpdClaimRepliesData")
    @SuppressWarnings("rawtypes")
    public Response updClaimRepliesData(@RequestBody UpdClaimRepliesDataParameter updClaimRepliesDataParameter) {
        // 反訴への回答
        String replyContext = updClaimRepliesDataParameter.getReplyContext();
        // セッションのプラットフォームID
        String platformId = updClaimRepliesDataParameter.getPlatformId();
        // セッション情報のCaseId
        String caseId = updClaimRepliesDataParameter.getCaseId();
        if (replyContext != null) {
            if (caseId != null && platformId != null) {
                try {
                    int result = couAnswerLoginService.UpdClaimRepliesData(updClaimRepliesDataParameter);
                    if (result == 0) {
                        return AjaxResult.error("更新失败!");
                    } else {
                        return AjaxResult.success("更新成功!");
                    }
                } catch (Exception e) {
                    AjaxResult.fatal("更新失败!", e);
                    return null;
                }
            } else {
                return AjaxResult.error("セッションを取得しない。");
            }
        } else {
            return AjaxResult.error("回答の内容が入力必須です。");
        }
    }
}