package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * 調停案ステータス取得
 * 
 * @author DUC 賈文志
 * @since 2024/05/13
 * @version 1.0
 */
@Mapper
public interface GetMediationStatusMapper {

    // 調停案ステータス取得
    String Mediationstatus(String CaseId);
}
