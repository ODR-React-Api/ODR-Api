package com.web.app.controller;

import com.web.app.domain.PoliciesInfo;
import com.web.app.service.PoliciesConfirmService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * S1_利用規約確認画面
 * Controller層
 * PoliciesConfirmController
 * 
 * @author DUC 閆文静
 * @since 2024/04/19
 * @version 1.0
 */
@Api(tags = "情报取得")
@RestController
@RequestMapping("/PoliciesConfirm")
public class PoliciesConfirmController {

    @Autowired
    private PoliciesConfirmService policiesConfirmService;

    /**
     * 利用規約情報取得
     *
     * @return 利用規約情報
     * @throws Exception 利用規約情報取得失败!
     */
    @ApiOperation("利用規約情報取得")
    @PostMapping("/GetPoliciesInfo")
    public PoliciesInfo getPoliciesInfo() {
        try {
            PoliciesInfo policiesInfo = policiesConfirmService.getPoliciesInfo();
            return policiesInfo;
        } catch (Exception e) {
            AjaxResult.fatal("利用規約情報取得失败!", e);
            return null;
        }
    }
}