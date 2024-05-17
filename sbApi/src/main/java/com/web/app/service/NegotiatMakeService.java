package com.web.app.service;

import com.web.app.domain.NegotiatMake.SettlementDraftDataResult;
import com.web.app.domain.NegotiatMake.FromSessionLogin;

/**
 * 和解案作成画面Service
 * 
 * @author DUC 朱暁芳
 * @since 2024/04/23
 * @version 1.0
 */
public interface NegotiatMakeService {
        // 和解案下書きデータ取得API
        SettlementDraftDataResult settlementDraftDataInfoSearch(
                        FromSessionLogin settlementDraftFromSessionLogin);

        // 下書き保存処理
        SettlementDraftDataResult settlementDraftGetCaseNegotiationsStatusInfoSearch(
                        FromSessionLogin sessionLogin);
}