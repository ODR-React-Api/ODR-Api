package com.web.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.web.app.domain.Response;
import com.web.app.domain.Entity.CaseNegotiations;
import com.web.app.domain.NegotiatAgree.NegotiatAgree;
import com.web.app.domain.NegotiatAgree.UpdNegotiatAgree;
import com.web.app.service.NegotiatAgreeService;
import com.web.app.tool.AjaxResult;

/**
 * 和解案合意更新API
 * 
 * @author DUC 李志文 賈文志
 * @since 2024/05/13
 * @version 1.0
 */                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         

@CrossOrigin(origins = "*")
@Api(tags = "和解案合意更新模块")
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
    @SuppressWarnings("rawtypes")
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

    /**
     * 
     * 和解案合意更新
     * 
     * @param updNegotiatAgree フォアグラウンドでんたつ
     * @return 和解案合意更新状態
     * @throws Exception 和解案合意更新失敗
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("和解案合意更新")
    @PostMapping("/UpdNegotiatAgree")
    public Response UpdNegotiatAgree(@RequestBody UpdNegotiatAgree updNegotiatAgree) {
        try {
            // 和解案合意更新
            Boolean updateCount = negotiatAgreeService.updNegotiatAgree(updNegotiatAgree);
            if (updateCount == true) {
                return AjaxResult.success("和解案合意更新成功");
            }else{
                return AjaxResult.success("和解案合意更新失败");
            }
        } catch (Exception e) {
            AjaxResult.fatal("和解案合意更新異常", e);
            return null;
        }
    }
}