package com.web.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.mediationsMake.Mediation;

/**
 * マッパー
 * 
 * @author DUC 徐義然
 * @since 2024/05/07
 * @version 1.0
 */

@Mapper
public interface GetMediationsDataMapper {

    List<Mediation> selectMediationsData(String caseId,String platformId);
}
