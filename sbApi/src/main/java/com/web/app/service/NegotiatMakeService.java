package com.web.app.service;

import com.web.app.domain.NegotiatMake.SettlementDraftDataResult;
import com.web.app.domain.NegotiatMake.FromSessionLogin;
import com.web.app.domain.NegotiatMake.NegotiationsFile;

/**
 * 和解案作成画面Service
 * 
 * @author DUC 朱暁芳 馬芹
 * @since 2024/04/23
 * @version 1.0
 */
public interface NegotiatMakeService {
    // 和解案下書きデータ取得API
    SettlementDraftDataResult settlementDraftDataInfoSearch(FromSessionLogin fromSessionLogin);

    // 下書き保存処理
    SettlementDraftDataResult settlementDraftInfoSearch(FromSessionLogin sessionLogin);

    // 和解案編集依頼データ新規登録
    int addNegotiationsEdit(NegotiationsFile negotiationsFile) throws Exception;

    // 和解案編集依頼データ更新
    int updateNegotiationsEdit(NegotiationsFile negotiationsFile) throws Exception;
}