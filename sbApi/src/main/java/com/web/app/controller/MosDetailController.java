package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.domain.MosDetail.GetCaseInfoParameter;
import com.web.app.domain.Response;
import com.web.app.domain.MosDetail.CaseInfo;
import com.web.app.service.MosDetailService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
                CaseInfo caseInfo = mosDetailService.GetCaseInfo(getCaseInfoParameter.getCaseId(),getCaseInfoParameter.getPlatformId(), getCaseInfoParameter.getUserId());
                return AjaxResult.success("案件状態取得成功!", caseInfo);
            } catch (Exception e) {
                AjaxResult.fatal("案件状態取得失敗!", e);
                return null;
            }
        } else {
            return AjaxResult.error("実行Flgが不正!");
        }
    }

}
