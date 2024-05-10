package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * 期日延長画面案件情報更新API Mapper
 * 
 * @author DUC 王 エンエン
 * @since 2024/05/20
 * @version 1.0
 */
@Mapper
public interface UpdCasesForMediationEndDateMapper {
    Integer setUpdCasesForMediationEndDate(String mediationEndDate,String string);
}
