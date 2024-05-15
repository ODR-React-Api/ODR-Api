package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.domain.Response;
import com.web.app.domain.MosDetail.CaseIdParameter;
import com.web.app.domain.MosDetail.CaseInfo;
import com.web.app.domain.MosDetail.GetCaseInfoParameter;
import com.web.app.domain.MosDetail.UpdShowTuritorParameter;
import com.web.app.domain.MosDetail.CaseMediationsData;
import com.web.app.domain.MosDetail.CaseNegotiationsData;
import com.web.app.service.MosDetailService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * S04_申立て概要画面
 * Controller層
 * MosDetailController
 * 
 * @author DUC 張明慧
 * @since 2024/04/22
 * @version 1.0
 */
@CrossOrigin(origins = "*")
// ラベルを「申立て概要画面」と指定する
@Api(tags = "申立て概要画面")
@RestController
@RequestMapping("/MosDetail")
public class MosDetailController {
    @Autowired
    private MosDetailService mosDetailService;

    /**
     * API_案件状態取得
     * 申立て一覧画面より渡されたCaseIdを引数として、DBから該当する案件のステータスを取得する。
     * 実行Flgが1の場合
     * 
     * @param getCaseInfoParameter API_案件状態取得の引数
     * @return Response API「案件状態取得」を呼び出すData
     */
    @ApiOperation("案件状態取得")
    @PostMapping("/GetCaseInfo")
    @SuppressWarnings("rawtypes")
    public Response getCaseInfo(@RequestBody GetCaseInfoParameter getCaseInfoParameter) {
        // 実行Flgが1の場合は以下1.～6.の処理を行う
        if (getCaseInfoParameter.getExecuteFlg() == 1) {
            try {
                // 案件状態取得
                CaseInfo caseInfo = mosDetailService.GetCaseInfo(getCaseInfoParameter.getCaseId(),
                        getCaseInfoParameter.getPlatformId(), getCaseInfoParameter.getUserId());
                return AjaxResult.success("案件状態取得成功!", caseInfo);
            } catch (Exception e) {
                AjaxResult.fatal("案件状態取得失敗!", e);
                return null;
            }
        } else {
            return AjaxResult.error("実行Flgが不正!");
        }
    }

    /**
     * API_チュートリアル表示制御変更
     * 実行Flgが2の場合
     * 
     * @param updShowTuritorParameter API_チュートリアル表示制御変更の引数
     * @return Response チュートリアル表示制御変更の状況
     */
    @ApiOperation("チュートリアル表示制御変更")
    @PostMapping("/UpdShowTuritor")
    @SuppressWarnings("rawtypes")
    public Response updShowTuritor(@RequestBody UpdShowTuritorParameter updShowTuritorParameter) {
        // 実行Flgが2の場合は以下処理を行う
        if (updShowTuritorParameter.getExecuteFlg() == 2) {
            try {
                // チュートリアル表示制御変更
                int res = mosDetailService.UpdShowTuritor(updShowTuritorParameter);
                if (res > 0) {
                    return AjaxResult.success("更新成功!");
                }
                return AjaxResult.success("更新失敗!");
            } catch (Exception e) {
                AjaxResult.fatal("更新失敗!", e);
                return null;
            }
        } else {
            return AjaxResult.error("実行Flgが不正!");
        }
    }

    /**
     * API_和解内容取得
     * 渡し項目.CaseIdを引数に、DBよりケースに該当する和解の内容を取得して、画面へ返す。
     * 
     * @param caseIdParameter 渡し項目.CaseId
     * @return Response API「和解内容取得」を呼び出すData
     */
    @ApiOperation("和解内容取得")
    @PostMapping("/GetCaseNegotiationsData")
    @SuppressWarnings("rawtypes")
    public Response getCaseNegotiationsData(@RequestBody CaseIdParameter caseIdParameter) {
        try {
            // 和解内容取得
            CaseNegotiationsData caseNegotiationsData = mosDetailService
                    .GetCaseNegotiationsData(caseIdParameter.getCaseId());
            return AjaxResult.success("和解内容取得成功!", caseNegotiationsData);
        } catch (Exception e) {
            AjaxResult.fatal("和解内容取得失敗!", e);
            return null;
        }
    }

    /**
     * API_調停内容取得
     * 渡し項目.CaseIdを引数に、DBよりケースに該当する調停の内容を取得して、画面へ返す。
     * 
     * @param caseIdParameter 渡し項目.CaseId
     * @return Response API「調停内容取得」を呼び出すData
     */
    @ApiOperation("調停内容取得")
    @PostMapping("/GetCaseMediationsData")
    @SuppressWarnings("rawtypes")
    public Response getCaseMediationsData(@RequestBody CaseIdParameter caseIdParameter) {
        try {
            // 調停内容取得
            CaseMediationsData caseMediationsData = mosDetailService.GetCaseMediationsData(caseIdParameter.getCaseId());
            return AjaxResult.success("調停内容取得成功!", caseMediationsData);
        } catch (Exception e) {
            AjaxResult.fatal("調停内容取得失敗!", e);
            return null;
        }
    }
}
