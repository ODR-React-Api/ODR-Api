package com.web.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.web.app.domain.NegotiatMake.SettlementDraftDataCaseFileRelations;
import com.web.app.domain.NegotiatMake.SettlementDraftDataCaseNegotiations;
import com.web.app.domain.NegotiatMake.SettlementDraftDataFiles;

/**
 * 和解案下書きデータ更新Mapper
 * 
 * @author DUC 朱暁芳
 * @since 2024/04/23
 * @version 1.0
 */
@Mapper
public interface UpdNegotiationsTempMapper {
        // 下書き保存処理
        SettlementDraftDataCaseNegotiations getNegotiationsStatusInfoSearch(String sessionCaseId,
                        String platformId);

        // テーブル「和解案」更新
        Integer updateCaseNegotiationsInfo(SettlementDraftDataCaseNegotiations caseNegotiations, String sessionCaseId);

        // テーブル「添付ファイル」論理削除
        Integer updateFilesInfo(SettlementDraftDataFiles files,
                        @Param("sessionObjFileIdList") List<String> sessionObjFileId);

        // テーブル「案件-添付ファイルリレーション」論理削除
        Integer updateCaseFileRelationsInfo(SettlementDraftDataCaseFileRelations caseFileRelations,
                        @Param("sessionObjCaseFileRelationsIdList") List<String> sessionObjCaseFileRelationsId);

        // テーブル「添付ファイル」新規登録
        Integer insertFilesInfo(SettlementDraftDataFiles files);

        // テーブル「案件-添付ファイルリレーション」新規登録
        Integer insertCaseFileRelationsInfo(SettlementDraftDataCaseFileRelations caseFileRelations);

}
