package com.web.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.domain.Response;
import com.web.app.domain.NegotiatPreview.MasterTemplates;
import com.web.app.domain.NegotiatPreview.NegotiatPreview;
import com.web.app.domain.constants.Constants;
import com.web.app.service.NegotiatPreviewService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 和解案プレビュー画面
 * 
 * @author DUC 馬芹 李志文
 * @since 2024/05/10
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "和解案プレビュー画面")
@RestController
@RequestMapping("/negotiatPreview")
public class NegotiatPreviewController {
    @Autowired
    private NegotiatPreviewService negotiatPreviewService;

    /**
     * 和解案提出登録/更新
     *
     * @param NegotiatPreview セッション値
     * @return Response
     * @throws Exception 和解案提出失敗
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("和解案プレビュー画面")
    @PostMapping("NegotiatPreview")
    public Response NegotiatPreview(@RequestBody NegotiatPreview negotiatPreview) {
        try {
            int status = negotiatPreviewService.NegotiatPreview(negotiatPreview);
            if (status == Constants.RESULT_STATE_SUCCESS) {
                return AjaxResult.success("和解案提出成功!");
            }
            return AjaxResult.error("和解案提出失敗!");
        } catch (Exception e) {
            return AjaxResult.fatal("和解案提出失敗!", e);
        }
    }

    /**
     * 和解案テンプレート取得
     *
     * @return Response 和解案テンプレート
     * @throws Exception 和解案テンプレート取得失敗
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("和解案テンプレート取得")
    @PostMapping("/getNegotiationsTemplate")
    public Response getNegotiationsTemplate() {
        try {
            List<MasterTemplates> asterTemplatesList = negotiatPreviewService
                    .getNegotiationsTemplate();
            return AjaxResult.success(Constants.MSG_SUCCESS, asterTemplatesList);
        } catch (Exception e) {
            AjaxResult.fatal(Constants.MSG_ERROR, e);
            return null;
        }
    }
}
