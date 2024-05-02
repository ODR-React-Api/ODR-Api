package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.GetReplies;

import java.util.List;

@Mapper
public interface GetRepliesMapper {

  GetReplies getPreUserDataSearch(String CaseId,String PlatformId);
  
  List<GetReplies> getReplies(String CaseId,String PlatformId);
}

