package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * API_ID:手続き中止API
 * 
 * 案件ステージ、案件ステータス、手続き中止日を更新する。
 * 
 * 
 * @author DUC 徐義然
 * @since 2024/05/10
 * @version 1.0
 */

@Mapper
public interface UpdCaseCancelDateMapper {
    int updCaseCancelDate(String cancelDate, String resolutionDate, String mediationId);
}
