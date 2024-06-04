package com.web.app.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.MediatorDisclosureRequest;
import com.web.app.domain.Response;
import com.web.app.domain.constants.Constants;
import com.web.app.service.InfoPublicSeigenService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 情報公開制限画面
 * 
 * @author DUC 李健
 * @since 2024/04/26
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "情報公開制限画面")
@RestController
@RequestMapping("/InfoPublicSeigen")
public class InfoPublicSeigenController {

    @Autowired
    private InfoPublicSeigenService infoPublicSeigenService;

    /**
     * 調停人情報開示制限
     * 
     * @param mediatorDisclosureRequest 請求の変数
     * @return 変更状態
     * @throws Exception
     */
    @ApiOperation("調停人情報開示制限")
    @GetMapping("/updMediatorDisclosureFlag")
    @SuppressWarnings({ "rawtypes" })
    public Response updMediatorDisclosureFlag(MediatorDisclosureRequest mediatorDisclosureRequest) throws Exception {
        try {
            Boolean resultBoolean = infoPublicSeigenService.updMediatorDisclosureFlag(mediatorDisclosureRequest);
            return AjaxResult.success(Constants.MSG_SUCCESS, resultBoolean);
        } catch (Exception e) {
            AjaxResult.fatal(Constants.MSG_ERROR, e);
            throw e;
        }
    }

    /**
     * 調停人情報開示変更可能期限日
     * 
     * @param caseId 案件ID
     * @return Response
     * @throws Exception
     */
    @ApiOperation("調停人情報開示変更可能期限日")
    @GetMapping("/getMediatorDisclosureDate")
    @SuppressWarnings({ "rawtypes" })
    public Response getMediatorDisclosureDate(String caseId) throws Exception {
        try {
            Date mediatorDisclosureDate = infoPublicSeigenService.getMediatorDisclosureDate(caseId);
            return AjaxResult.success(Constants.MSG_SUCCESS, mediatorDisclosureDate);
        } catch (Exception e) {
            AjaxResult.fatal(Constants.MSG_ERROR, e);
            throw e;
        }
    }
}
