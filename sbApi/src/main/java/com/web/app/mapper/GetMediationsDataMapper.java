package com.web.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.Mediation;

@Mapper
public interface GetMediationsDataMapper {

    List<Mediation> selectMediationsData(String caseId,String platformId);
}
