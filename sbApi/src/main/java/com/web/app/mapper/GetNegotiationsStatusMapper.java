package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.NegotiatMake.SettlementDraftDataCaseNegotiations;

/**
 * 下書き保存処理Mapper
 * 
 * @author DUC 朱暁芳
 * @since 2024/04/23
 * @version 1.0
 */
@Mapper
public interface GetNegotiationsStatusMapper {
    // 下書き保存処理
    SettlementDraftDataCaseNegotiations getNegotiationsStatusInfoSearch(String sessionCaseId, String platformId);
}
