package com.web.app.controller.MosDetail;

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
import com.web.app.domain.MosDetail.MediationsData;
import com.web.app.domain.MosDetail.NegotiationsData;
import com.web.app.service.MosDetail.MosDetailService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 申立て概要画面
 * Controller层
 * MosDetailController
 * API_案件状態取得
 * API_和解内容取得
 * API_調停内容取得
 * 
 * @author DUC 張明慧
 * @since 2024/04/22
 * @version 1.0
 */
@CrossOrigin(origins = "*")
// ラベルを「申請して概要画面」と指定する
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
     * @return caseInfo API「案件状態取得」を呼び出すData
     */
    @ApiOperation("案件状態取得")
    @PostMapping("/GetCaseInfo")
    public CaseInfo getCaseInfo(@RequestBody GetCaseInfoParameter getCaseInfoParameter) {
        CaseInfo caseInfo = new CaseInfo();
        // 実行Flgが1の場合は以下1.～6.の処理を行う
        if (getCaseInfoParameter.getExecuteFlg() == 1) {
            try {
                // 案件状態取得
                caseInfo = mosDetailService.getCaseInfo(getCaseInfoParameter.getCaseId(),
                        getCaseInfoParameter.getPlatformId(), getCaseInfoParameter.getUserId());
                System.out.println("caseInfo:" + caseInfo);
                return caseInfo;
            } catch (Exception e) {
                AjaxResult.fatal("案件状態取得失敗!", e);
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * チュートリアル表示制御変更
     * 実行Flgが2の場合
     * 
     * @param getCaseInfoParameter API_案件状態取得の引数
     * @return Response チュートリアル表示制御変更の状況
     */
    @ApiOperation("チュートリアル表示制御変更")
    @PostMapping("/UpdShowTuritor")
    @SuppressWarnings("rawtypes")
    public Response updShowTuritor(@RequestBody GetCaseInfoParameter getCaseInfoParameter) {
        // System.out.println("getCaseInfoParameter:" + getCaseInfoParameter);
        // 実行Flgが2の場合は以下処理を行う
        if (getCaseInfoParameter.getExecuteFlg() == 2) {
            try {
                // チュートリアル表示制御変更
                int res = mosDetailService.updShowTuritor(getCaseInfoParameter);
                System.out.println("res:" + res);
                if (res > 0) {
                    return AjaxResult.success("更新成功!");
                } else {
                    return AjaxResult.error("更新失敗!");
                }
            } catch (Exception e) {
                AjaxResult.fatal("更新失敗!", e);
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * API_和解内容取得
     * 渡し項目.CaseIdを引数に、DBよりケースに該当する和解の内容を取得して、画面へ返す。
     * 
     * @param caseIdParameter 渡し項目.CaseId
     * @return negotiationsData API「和解内容取得」を呼び出すData
     */
    @ApiOperation("和解内容取得")
    @PostMapping("/GetNegotiationsData")
    public NegotiationsData getNegotiationsData(@RequestBody CaseIdParameter caseIdParameter) {
        NegotiationsData negotiationsData = new NegotiationsData();
        try {
            // 和解内容取得
            negotiationsData = mosDetailService.getNegotiationsData(caseIdParameter.getCaseId());
            System.out.println("negotiationsData:" + negotiationsData);
            if (negotiationsData != null) {
                return negotiationsData;
            } else {
                AjaxResult.error("和解内容取得0件!");
                return null;
            }
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
     * @return mediationsData API「調停内容取得」を呼び出すData
     */
    @ApiOperation("調停内容取得")
    @PostMapping("/GetMediationsData")
    public MediationsData getMediationsData(@RequestBody CaseIdParameter caseIdParameter) {
        MediationsData mediationsData = new MediationsData();
        try {
            // 調停内容取得
            mediationsData = mosDetailService.getMediationsData(caseIdParameter.getCaseId());
            System.out.println("mediationsData:" + mediationsData);
            if (mediationsData != null) {
                return mediationsData;
            } else {
                AjaxResult.error("調停内容取得0件!");
                return null;
            }
        } catch (Exception e) {
            AjaxResult.fatal("調停内容取得失敗!", e);
            return null;
        }
    }
}
