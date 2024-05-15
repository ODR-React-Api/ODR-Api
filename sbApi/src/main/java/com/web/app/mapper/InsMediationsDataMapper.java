package com.web.app.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.Entity.CaseMediations;
import com.web.app.domain.mediationsMake.InsMediationsData;

@Mapper
public interface InsMediationsDataMapper {
    int insMediationsData2(CaseMediations CaseMediations);

    CaseMediations mediationCount(InsMediationsData mediationcase);

    int dataSearch(InsMediationsData mediationcase);
}