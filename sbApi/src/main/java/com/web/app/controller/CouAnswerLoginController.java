package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.web.app.service.CouAnswerLoginService;
import com.web.app.tool.AjaxResult;
import com.web.app.domain.Response;
import com.web.app.domain.constants.CouAnswerLogin.InsClaimRepliesDto;
import com.web.app.domain.constants.CouAnswerLogin.RepliesContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;

/**
 * 反訴回答登録画面 Controller
 * 
 * @author DUC 信召艶
 * @since 2024/04/29
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "反訴回答登録画面")
@RestController
@RequestMapping("/CouAnswerLogin")
public class CouAnswerLoginController {

    @Autowired
    private CouAnswerLoginService couAnswerLoginService;

    /**
     * API_反訴・回答データ取得
     *
     * @param CaseId     セッション情報のCaseId
     * @param PlatformId セッション情報のプラットフォームID
     * @return getRepliesContextList
     * @throws Exception エラーの説明内容
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("反訴・回答データ取得")
    @GetMapping("/getRepliesContext")
    public Response getRepliesContext(String caseId, String platformId) {
        try {
            List<RepliesContext> getRepliesContextList = new ArrayList<RepliesContext>();
            getRepliesContextList = couAnswerLoginService.getRepliesContext(caseId, platformId);
            return AjaxResult.success("反訴・回答データ取得に成功しました", getRepliesContextList);
        } catch (Exception e) {
            AjaxResult.fatal("反訴・回答データ取得に失敗しました!", e);
            return null;
        }
    }

    /**
     * API_反訴への回答データ新規登録
     *
     * @param InsClaimRepliesDto API_反訴への回答データ新規登録の引数
     * @return 反訴への回答データ新規登録の状況
     * @throws Exception エラーの説明内容
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("反訴への回答データ新規登録")
    @PostMapping("/insClaimRepliesData")
    public Response insClaimRepliesData(@RequestBody InsClaimRepliesDto insClaimRepliesDto) {
        try {
            couAnswerLoginService.insClaimRepliesData(insClaimRepliesDto);
            return AjaxResult.success("反訴への回答データ新規登録に成功しました!");
        } catch (Exception e) {
            AjaxResult.fatal("反訴への回答データ新規登録に失敗しました!", e);
            return null;
        }
    }
}
