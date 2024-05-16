package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.web.app.service.GetRepliesContextService;
import com.web.app.tool.AjaxResult;
import com.web.app.domain.Response;
import com.web.app.domain.couAnswerLogin.GetRepliesContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.sql.DataSource;
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
    DataSource dataSource;

    @Autowired
    private GetRepliesContextService getRepliesContextService;

    /**
     * API_ID:反訴・回答データ取得
     *
     * @param CaseId,PlatformId セッション情報より渡された引数
     * @return Response
     * @throws Exception エラーの説明内容
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("反訴・回答データ取得")
    @GetMapping("/getRepliesContext")
    public Response getRepliesContext(String CaseId, String PlatformId) {
        try {
            List<GetRepliesContext> userContextList = new ArrayList<GetRepliesContext>();
            userContextList = getRepliesContextService.getRepliesContext(CaseId, PlatformId);
            return AjaxResult.success("请求成功", userContextList);
        } catch (Exception e) {
            AjaxResult.fatal("查询失败!", e);
            return null;
        }
    }
}


