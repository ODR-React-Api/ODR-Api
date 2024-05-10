package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.Entity.CaseNegotiations;
import com.web.app.domain.Entity.File;
import com.web.app.service.NegotiatPreviewService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//import org.springframework.web.bind.annotation.*;

/**
 * 和解案データ登録
 * 
 * @author DUC 李志文
 * @since 2024/05/10
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "和解案データ登録")
@RestController
@RequestMapping("/SettlementPlan")
public class NegotiatPreviewController {
    @Autowired
    private NegotiatPreviewService negotiatPreviewService;

    /**
     * メソッドの説明内容
     *
     * @param CaseNegotiations 和解案、　File　ファイル
     * @return Response
     * @throws Exception 和解案提出失敗
     */
    @ApiOperation("和解案登録")
    @PostMapping("NegotiatPreview")
    //@SuppressWarnings("")
    public Response NegotiatPreview(CaseNegotiations caseNegotiations, File file){
        try{
            negotiatPreviewService.NegotiatPreview(caseNegotiations, file);
            return AjaxResult.success("和解案提出成功!");
        }catch (Exception e){
            AjaxResult.fatal("和解案提出失敗!", e);
            return null;
        }
    }
}
