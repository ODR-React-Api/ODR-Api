package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.web.app.domain.Response;
import com.web.app.domain.CouAnswerLogin.CasesByCid;
import com.web.app.service.CasesByCidService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 * API_会員登録フォーム画面
 * 
 * @author DUC 信召艶
 * @since 2024/04/18
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "会員登録画面")
@RestController
@RequestMapping("/userLogin")
public class UserLoginaController {

    @Autowired
    DataSource dataSource;

    @Autowired
    private CasesByCidService casesByCidService;

    /**
     * API_ID:ケース詳細取得
     *
     * @param param1 API「C12_会員仮登録画面」より渡された引数
     * @return 戻り値は「_C02_会員登録フォーム画面」に返される
     * @throws Exception エラーの説明内容
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("案件データ取得")
    @GetMapping("/CasesByCid")
    public Response casesByCid(String CaseId, String PlatformId) {
        try {
            List<CasesByCid> userContextList = new ArrayList<CasesByCid>();
            userContextList = casesByCidService.casesByCid(CaseId, PlatformId);
            return AjaxResult.success("请求成功", userContextList);
        } catch (Exception e) {
            AjaxResult.fatal("查询失败!", e);
            return null;
        }
    }

}
