package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.MediationsConCon.MediationsUserData;

/**
 * S24_調停案内容確認画面
 * Mapper层
 * GetMediationsUserDataMapper
 * 
 * @author DUC 王 エンエン
 * @since 2024/05/09
 * @version 1.0
 */
@Mapper
public interface GetMediationsUserDataMapper {
    // API_ユーザデータ取得
    MediationsUserData findAllUser(String caseId, String platformId);
}
