package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.domain.Response;
import com.web.app.domain.Entity.Cases;
import com.web.app.domain.MedUserConfirm.MedUserConfirm;
import com.web.app.service.MedUserConfirmService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 調停人確認画面
 * 
 * @author DUC 李志文
 * @since 2024/05/16
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "調停人確認画面")
@RestController
@RequestMapping("/MedUserConfirm")
public class MedUserConfirmController {

    @Autowired
    private MedUserConfirmService medUserConfirmService;

    /**
     * ファイル名取得
     *
     * @param NegotiatPreview セッション値
     * @return Response
     * @throws Exception 和解案提出失敗
     */
    @SuppressWarnings({"rawtypes"})
    @ApiOperation("ファイル名取得")
    @PostMapping("GetFileName")
    public Response GetFileName(@RequestBody MedUserConfirm medUserConfirm) {
        try {
            String fileName = medUserConfirmService.GetFileName(medUserConfirm.getFileId());
            return AjaxResult.success( "成功!",fileName);
        } catch (Exception e) {
            AjaxResult.fatal( "失敗!",e);
            return null;
        }
    }

    /**
     * 調停変更回数取得
     *
     * @param NegotiatPreview セッション値
     * @return Response
     * @throws Exception 和解案提出失敗
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("調停変更回数取得")
    @PostMapping("GetMediatorChangeableCount")
    public Response GetMediatorChangeableCount(@RequestBody MedUserConfirm medUserConfirm) {
        try {
            Cases cases = medUserConfirmService.GetMediatorChangeableCount(medUserConfirm.getCaseId());
            return AjaxResult.success( "成功!",cases);
        } catch (Exception e) {
            AjaxResult.fatal( "失敗!",e);
            return null;
        }
    }
}
