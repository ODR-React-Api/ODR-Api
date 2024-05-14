package com.web.app.controller;

import com.web.app.domain.PoliciesInfo;
import com.web.app.service.PoliciesConfirmService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * API_利用規約情報取得
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
    DataSource dataSource;

    @Autowired
    private PoliciesConfirmService policiesConfirmService;

    @ApiOperation("利用規約情報取得")
    @PostMapping("/GetPoliciesInfo")
    public List<PoliciesInfo> getPoliciesInfo() {
        try {
            List<PoliciesInfo> policiesInfoList = policiesConfirmService.getPoliciesInfoList();
            return policiesInfoList;
        } catch (Exception e) {
            AjaxResult.fatal("利用規約情報取得失败!", e);
            return null;
        }
    }
}