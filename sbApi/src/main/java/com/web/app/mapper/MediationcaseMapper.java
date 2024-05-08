package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.controller.List;
import com.web.app.domain.Mediationcase;

@Mapper
public interface MediationcaseMapper {
    int MediationcaseInsert(Mediationcase mediationcase);
    List<Mediationcase> MediationcaseSearch(Mediationcase mediationcase);
}