package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.DraftSavingDate;

@Mapper
public interface GetDraftSavingMapper {

  DraftSavingDate getDraftSavingData(String uid);
  
}
