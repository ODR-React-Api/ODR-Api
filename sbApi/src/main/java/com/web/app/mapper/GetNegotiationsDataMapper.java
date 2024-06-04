package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.NegotiatMake.SettlementDraftDataSelectedInfo;

/**
 * 和解案作成画面Mapper
 * 
 * @author DUC 朱暁芳
 * @since 2024/04/23
 * @version 1.0
 */
@Mapper
public interface GetNegotiationsDataMapper {
    // 和解案下書きデータ取得
    SettlementDraftDataSelectedInfo getNegotiationsDataInfoSearch(String sessionCaseId);
}
