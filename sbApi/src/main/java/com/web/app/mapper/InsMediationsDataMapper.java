package com.web.app.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.mediationsMake.InsMediationsData;

@Mapper
public interface InsMediationsDataMapper {
    int insMediationsData2(InsMediationsData mediationcase);

    InsMediationsData mediationCount(InsMediationsData mediationcase);

    ArrayList<InsMediationsData> dataSearch(InsMediationsData mediationcase);
}