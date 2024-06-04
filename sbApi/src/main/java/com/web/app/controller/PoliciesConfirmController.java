package com.web.app.controller;

import com.web.app.domain.Response;
import com.web.app.domain.PoliciesConfirm.PoliciesInfo;
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
@Api(tags = "利用規約確認画面")
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
    @SuppressWarnings("rawtypes")
    @ApiOperation("利用規約情報取得")
    @PostMapping("/GetPoliciesInfo")
    public Response getPoliciesInfo() {
        try {
            PoliciesInfo policiesInfo = policiesConfirmService.getPoliciesInfo();
            return AjaxResult.success("利用規約情報取得成功!", policiesInfo);
        } catch (Exception e) {
            AjaxResult.fatal("利用規約情報取得失败!", e);
            return null;
        }
    }
}