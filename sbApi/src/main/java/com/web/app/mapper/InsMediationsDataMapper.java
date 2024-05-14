package com.web.app.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.controller.List;
import com.web.app.domain.mediationsMake.InsMediationsData;

@Mapper
public interface InsMediationsDataMapper {
    int MediationcaseInsert(InsMediationsData mediationcase);

    ArrayList<InsMediationsData> MediationcaseSearch(InsMediationsData mediationcase);
}