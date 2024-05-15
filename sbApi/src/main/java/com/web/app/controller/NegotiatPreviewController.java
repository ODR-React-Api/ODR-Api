package com.web.app.controller;

import com.web.app.domain.NegotiatPreview.MasterTemplates;
import com.web.app.domain.constants.Constants;
import com.web.app.service.NegotiatPreviewService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 和解案テンプレート取得コントローラ
 * 
 * @author DUC 馬芹
 * @since 2024/05/14
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "和解案テンプレート取得")
@RestController
@RequestMapping("/NegotiatPreviewController")
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
    @ApiOperation("和解案テンプレート取得")
    @PostMapping("/getNegotiationsTemplate")
    public List<MasterTemplates> getNegotiationsTemplate() {
        try {

            MasterTemplates masterTemplates = new MasterTemplates();
            masterTemplates.setDeleteFlag(Constants.DELETE_FLAG_0);

            List<Integer> templateType = new ArrayList<>();
            templateType.add(Constants.TEMPLATE_TYPE_0);
            templateType.add(Constants.TEMPLATE_TYPE_3);
            masterTemplates.setTemplateTypes(templateType);

            masterTemplates.setLanguageId(Constants.JP);
            List<MasterTemplates> asterTemplatesList = negotiationsPreviewService
                    .getNegotiationsTemplate(masterTemplates);

            return asterTemplatesList;

        } catch (Exception e) {
            AjaxResult.fatal(Constants.MSG_ERROR, e);
            return null;
        }
    }
}
