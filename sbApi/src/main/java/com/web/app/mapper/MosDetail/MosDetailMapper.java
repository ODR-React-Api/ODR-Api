package com.web.app.mapper.MosDetail;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.MosDetail.CaseMediations;
import com.web.app.domain.MosDetail.CaseNegotiations;
import com.web.app.domain.MosDetail.CasesData;
import com.web.app.domain.MosDetail.Files;
import com.web.app.domain.MosDetail.GetCaseInfoParameter;
import com.web.app.domain.MosDetail.MasterPlatforms;
import com.web.app.domain.MosDetail.OdrUsers;

/**
 * 申立て概要画面
 * Mapper层
 * MosDetailMapper
 * 
 * @author DUC 張明慧
 * @since 2024/04/22
 * @version 1.0
 */
@Mapper
public interface MosDetailMapper {
    // API_案件状態取得
    // 該当案件のステータスを取得
    CasesData getCaseInfo(String caseId);

    // 利用モジュール状況取得
    MasterPlatforms getPhases(String platformId);

    // チュートリアル表示制御取得
    OdrUsers getShowTuritor(String userId, String platformId);

    // チュートリアル表示制御変更
    int updShowTuritor(GetCaseInfoParameter getCaseInfoParameter);

    // API_和解内容取得
    // 和解内容の取得
    CaseNegotiations getCaseNegotiations(String caseId);

    // API_調停内容取得
    // 調停内容の取得
    CaseMediations getCaseMediations(String caseId);

    // 共通 添付資料の取得
    List<Files> getFiles(String caseId, int relationType);
}
