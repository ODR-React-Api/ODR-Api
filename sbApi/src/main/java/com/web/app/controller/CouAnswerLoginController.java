package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import com.web.app.service.CouAnswerLoginService;
import com.web.app.service.UtilService;
import com.web.app.tool.AjaxResult;
import com.web.app.domain.Response;
import com.web.app.domain.Entity.CasesByCid;
import com.web.app.domain.couAnswerLogin.RepliesContext;
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

    @Autowired
    private UtilService utilService;

    /**
     * API_反訴・回答データ取得
     *
     * @param CaseId セッション情報のCaseId 
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
            return AjaxResult.success("请求成功", getRepliesContextList);
        } catch (Exception e) {
            AjaxResult.fatal("查询失败!", e);
            return null;
        }
    }

    /**
     * API_案件データ取得
     *
     * @param CaseId セッション情報のCaseId 
     * @param PlatformId セッション情報のプラットフォームID
     * @return getCasesByCidList
     * @throws Exception エラーの説明内容
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("案件データ取得")
    @GetMapping("/getCasesByCid")
    public Response getCasesByCid(String caseId, String platformId) {
        try {
            List<CasesByCid> getCasesByCidList = new ArrayList<CasesByCid>();
            getCasesByCidList = utilService.casesByCid(caseId, platformId);
            return AjaxResult.success("请求成功", getCasesByCidList);
        } catch (Exception e) {
            AjaxResult.fatal("查询失败!", e);
            return null;
        }
    }
}


