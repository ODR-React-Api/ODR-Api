package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.NegotiatPreview.NegotiatPreview;
import com.web.app.domain.constants.Constants;
import com.web.app.service.NegotiatPreviewService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 和解案データ登録
 * 
 * @author DUC 李志文
 * @since 2024/05/10
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "和解案プレビュー画面")
@RestController
@RequestMapping("/NegotiatPreview")
public class NegotiatPreviewController {
    @Autowired
    private NegotiatPreviewService negotiatPreviewService;

    @Autowired
    private SendMailController sendMailController;

    /**
     * 和解案提出登録/更新
     *
     * @param NegotiatPreview セッション値
     * @return Response
     * @throws Exception 和解案提出失敗
     */
    @ApiOperation("和解案プレビュー画面")
    @PostMapping("NegotiatPreview")
    public Response NegotiatPreview(NegotiatPreview negotiatPreview){
        try{
            int status = negotiatPreviewService.NegotiatPreview(negotiatPreview);
            if (status == Constants.RESULT_STATE_SUCCESS) {
                Boolean boolean1 = sendMailController.sendMail();
                if (boolean1 = true) {
                    return AjaxResult.success("和解案提出成功!");
                }
            }
            return AjaxResult.error("和解案提出成功!");
        }catch (Exception e){
            return AjaxResult.fatal("和解案提出失敗!", e);
        }
    }
}
