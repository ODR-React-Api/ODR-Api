package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.Entity.MasterPlatforms;
import com.web.app.domain.Entity.OdrUsers;
import com.web.app.domain.MosDetail.CasesData;

/**
 * S04_申立て概要画面
 * Mapper層
 * GetCaseInfoMapper
 * API_案件状態取得
 * 
 * @author DUC 張明慧
 * @since 2024/05/10
 * @version 1.0
 */
@Mapper
public interface GetCaseInfoMapper {
    // API_案件状態取得
    // 該当案件のステータスを取得
    CasesData getCasesData(String caseId);

    // 利用モジュール状況取得
    MasterPlatforms getPhases(String platformId);

    // チュートリアル表示制御取得
    OdrUsers getShowTuritor(String userId, String platformId);
}
