package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.NegotiatMake.SettlementDraftDataCaseFileRelations;
import com.web.app.domain.NegotiatMake.SettlementDraftDataCaseNegotiations;
import com.web.app.domain.NegotiatMake.SettlementDraftDataFiles;

/**
 * 和解案下書きデータ新規登録Mapper
 * 
 * @author DUC 朱暁芳
 * @since 2024/04/23
 * @version 1.0
 */
@Mapper
public interface InsNegotiationTempMapper {
    // 下書き保存処理
    SettlementDraftDataCaseNegotiations getNegotiationsStatusInfoSearch(String sessionCaseId, String platformId);

    // テーブル「和解案」新規登録
    Integer insertCaseNegotiationsInfo(SettlementDraftDataCaseNegotiations caseNegotiations);

    // テーブル「添付ファイル」新規登録
    Integer insertFilesInfo(SettlementDraftDataFiles files);

    // テーブル「案件-添付ファイルリレーション」新規登録
    Integer insertCaseFileRelationsInfo(SettlementDraftDataCaseFileRelations caseFileRelations);

}
