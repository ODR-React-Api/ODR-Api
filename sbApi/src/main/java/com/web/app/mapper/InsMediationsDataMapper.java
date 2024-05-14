package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.controller.List;
import com.web.app.domain.mediationsMake.InsMediationsData;

@Mapper
public interface InsMediationsDataMapper {
    int MediationcaseInsert(InsMediationsData mediationcase);

    List<InsMediationsData> MediationcaseSearch(InsMediationsData mediationcase);
}