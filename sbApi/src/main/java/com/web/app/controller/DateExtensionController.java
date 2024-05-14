package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.DateExtension.CaseInfo;
import com.web.app.service.DateExtensionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 期日延長画面Controller
 * 
 * @author DUC 耿浩哲
 * @since 2024/05/02
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "期日延長画面")
@RestController
@RequestMapping("/DateExtension")
public class DateExtensionController {

    @Autowired
    private DateExtensionService dateExtensionService;

    /**
     * 案件情報取得API
     *
     * @param CaseInfo 期日延長オブジェクト
     * @return 期日延長オブジェクト
     * @throws Exception エラーの説明内容
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("案件情報取得API")
    @PostMapping("/getCaseInfo")
    public Response getCaseInfo (@RequestBody CaseInfo caseInfo) {
        try {
            caseInfo.setNegotiationEndDate(dateExtensionService.getCaseInfo(caseInfo.getCaseId(), caseInfo.getPlatformId()));
            return Response.success(caseInfo);
        } catch (Exception e) {
            return Response.error(e.getMessage());
        }
    }
}
