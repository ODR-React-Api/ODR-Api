package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.DateExtension.CaseInfo;
import com.web.app.service.DateExtensionService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 期日延長画面Controller
 * 
 * @author DUC 徐義然 耿浩哲
 * @since 2024/05/02
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "期日延長画面")
@RestController
@RequestMapping("/DateExtension")
public class DateExtensionController {

    //サービスオブジェクト
    @Autowired
    private DateExtensionService dateExtensionService;

    /**
     * 
     * API_ID:交渉期限延長可能日数取得
     * 
     * プラットフォームマスタテーブルから交渉期限延長可能日数を取得する。
     * 
     * @param platformId:案件情報取得API(getCaseInfo)の戻り値.PlatformId
     * @return 交渉期限延長可能日数
     */
    @ApiOperation("交渉期限延長可能日数取得")
    @GetMapping("/getNegotiationExtendDays")
    @SuppressWarnings("unchecked")
    public Response<String> getNegotiationExtendDays(String platformId){
        try {
            return AjaxResult.success("交渉期限延長可能日数取得しました", dateExtensionService.getNegotiationExtendDays(platformId));
        } catch (Exception e) {
            return AjaxResult.fatal("交渉期限延長可能日数取得失敗!", e);
        }
    }

    /**
     * 案件情報取得API
     *
     * @param CaseInfo 期日延長オブジェクト
     * @return 期日延長オブジェクト
     * @throws Exception エラーの説明内容
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("案件情報取得API")
    @PostMapping("/getToCaseInfo")
    public Response getToCaseInfo(@RequestBody CaseInfo caseInfo) {
        try {
            caseInfo.setNegotiationEndDate(dateExtensionService.getToCaseInfo(caseInfo.getCaseId(), caseInfo.getPlatformId()));
            return Response.success(caseInfo);
        } catch (Exception e) {
            return Response.error(e.getMessage());
        }
    }
}
