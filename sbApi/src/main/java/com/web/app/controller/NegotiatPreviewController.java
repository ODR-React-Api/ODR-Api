package com.web.app.controller;

import com.web.app.domain.Response;
import com.web.app.domain.NegotiatPreview.MasterTemplates;
import com.web.app.domain.constants.Constants;
import com.web.app.service.NegotiatPreviewService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 和解案プレビュー画面
 * 
 * @author DUC 馬芹
 * @since 2024/05/14
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "和解案プレビュー画面")
@RestController
@RequestMapping("/negotiatPreview")
public class NegotiatPreviewController {
    @Autowired
    private NegotiatPreviewService negotiationsPreviewService;

    /**
     * 和解案テンプレート取得
     *
     * @param param1 なし
     * @return List<MasterTemplates>
     * @throws Exception 異常終了
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("和解案テンプレート取得")
    @PostMapping("/getNegotiationsTemplate")
    public Response getNegotiationsTemplate() {
        try {
            List<MasterTemplates> asterTemplatesList = negotiationsPreviewService
                    .getNegotiationsTemplate();
            return AjaxResult.success(Constants.MSG_SUCCESS, asterTemplatesList);
        } catch (Exception e) {
            return AjaxResult.fatal(Constants.MSG_ERROR, e);
        }
    }
}
