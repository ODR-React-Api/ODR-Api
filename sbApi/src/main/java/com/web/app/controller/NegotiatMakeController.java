package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.NegotiatMake.FromSessionLogin;
import com.web.app.domain.NegotiatMake.SettlementDraftDataResult;
import com.web.app.service.NegotiatMakeService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 和解案作成画面Controller
 * 
 * @author DUC 朱暁芳
 * @since 2024/04/23
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "和解案作成")
@RestController
@RequestMapping("/NegotiatMake")
public class NegotiatMakeController {

    @Autowired
    private NegotiatMakeService negotiatMakeService;

    /**
     * 和解案下書きデータ取得API
     * 
     * @param param1 セッション情報 と ログイン情報渡された
     * @return 戻り値は「 和解案作成 和解案下書きデータ取得」に返される
     * @throws Exception エラーの説明内容
     */
    @ApiOperation("和解案データ取得")
    @PostMapping("/getSettlementDraft")
    public SettlementDraftDataResult getSettlementDraftData(
            @RequestBody FromSessionLogin settlementDraftFromSessionLogin) {
        try {
            SettlementDraftDataResult dataResult = negotiatMakeService
                    .settlementDraftDataInfoSearch(settlementDraftFromSessionLogin);
            return dataResult;
        } catch (Exception e) {
            AjaxResult.fatal("失敗しました。", e);
            return null;
        }
    }

    /**
     * 下書き保存処理
     * 
     * @param param1 セッション情報 と ログイン情報渡された
     * @return 戻り値は「 下書き保存処理」が返された値
     * @throws Exception エラーの説明内容
     */
    @ApiOperation("下書き保存処理")
    @PostMapping("/getCaseNegotiationsStatus")
    public SettlementDraftDataResult getCaseNegotiationsStatus(
            @RequestBody FromSessionLogin settlementDraftFromSessionLogin) {
        try {
            SettlementDraftDataResult participatedResult = negotiatMakeService
                    .settlementDraftGetCaseNegotiationsStatusInfoSearch(settlementDraftFromSessionLogin);
            return participatedResult;
        } catch (Exception e) {
            AjaxResult.fatal("失敗しました。", e);
            return null;
        }
    }
}