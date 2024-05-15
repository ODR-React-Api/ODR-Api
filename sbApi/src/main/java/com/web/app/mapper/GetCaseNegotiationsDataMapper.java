package com.web.app.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.Entity.CaseNegotiations;
import com.web.app.domain.Entity.Files;

/**
 * S04_申立て概要画面
 * Mapper層
 * GetCaseNegotiationsDataMapper
 * API_和解内容取得
 * 
 * @author DUC 張明慧
 * @since 2024/05/10
 * @version 1.0
 */
@Mapper
public interface GetCaseNegotiationsDataMapper {
    // API_和解内容取得
    // 和解内容の取得
    CaseNegotiations getCaseNegotiations(String caseId);

    // 添付資料の取得
    List<Files> getFiles(String caseId);
}
