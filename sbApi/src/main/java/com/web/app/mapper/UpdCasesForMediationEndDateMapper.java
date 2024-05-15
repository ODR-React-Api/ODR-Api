package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * S29_調停期日延長画面
 * Mapper层
 * UpdCasesForMediationEndDateMapper
 * 
 * @author DUC 王 エンエン
 * @since 2024/05/16
 * @version 1.0
 */
@Mapper
public interface UpdCasesForMediationEndDateMapper {
    // API_案件情報更新
    Integer setUpdCasesForMediationEndDate(String mediationEndDate,String cid);
}
