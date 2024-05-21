package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.Entity.CaseNegotiations;
import com.web.app.domain.NegotiatAgree.NegotiatAgree;
import com.web.app.service.NegotiatAgreeService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 和解案合意画面
 * 
 * @author DUC 李志文
 * @since 2024/05/14
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "和解案合意画面")
@RestController
@RequestMapping("/NegotiatAgree")
public class NegotiatAgreeController {
    @Autowired
    private NegotiatAgreeService negotiatAgreeService;

    /**
     * 和解案確認データ取得
     *
     * @param NegotiatAgree セッション値
     * @return Response
     * @throws Exception 和解案提出失敗
     */
    @ApiOperation("和解案確認データ取得")
    @PostMapping("GetNegotiatConInfo")
    public Response GetNegotiatConInfo(@RequestBody NegotiatAgree negotiatAgree) {
        try {
            CaseNegotiations caseNegotiations = negotiatAgreeService.SelCaseNegotiations(negotiatAgree);
            return AjaxResult.success("和解案内容取得成功!", caseNegotiations);
        } catch (Exception e) {
            return AjaxResult.fatal("和解案内容取得失敗!", e);
        }
    }
}
