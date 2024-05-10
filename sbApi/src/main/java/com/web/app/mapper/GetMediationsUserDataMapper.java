package com.web.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.MediationsConCon.MediationsUserData;

/**
 * 調停案内容確認ユーザデータ取得API Mapper
 * 
 * @author DUC 王 エンエン
 * @since 2024/05/12
 * @version 1.0
 */
@Mapper
public interface GetMediationsUserDataMapper {

    List<MediationsUserData> findAllUser(String caseId,String platformId);

}
