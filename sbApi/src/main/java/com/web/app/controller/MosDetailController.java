package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.MosDetail.WithdrawalReturn;
import com.web.app.domain.constants.Constants;
import com.web.app.service.MosDetailService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 申立て概要画面
 * 
 * @author DUC 張万超
 * @since 2024/4/29
 * @version 1.0
 */
@RestController
@CrossOrigin(origins = "*")
@Api(tags = "申立て詳細画面")
@RequestMapping("/MosDetail")
public class MosDetailController {

    @Autowired
    private MosDetailService mosDetailService;

    /**
     * ケースの状態を取り下げに変更する。
     *
     * @param caseId 渡し項目.CaseId
     * @return 変更結果
     */
    @GetMapping("/withdrawal")
    @ApiOperation("取り下げ済状態変更")
    @SuppressWarnings("rawtypes")
    public Response getMethodName(@RequestParam String caseId) {
        try {
            WithdrawalReturn res = mosDetailService.applyWithdraw(caseId);
            return AjaxResult.success(Constants.AJAXRESULT_SUCCESS,res);
        } catch (Exception e) {
            return AjaxResult.error("error:" + e);
        }
    }

}
