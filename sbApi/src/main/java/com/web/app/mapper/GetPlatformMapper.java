package com.web.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.ExpandItems;
import com.web.app.domain.PetitionType;
import com.web.app.domain.Status;

@Mapper
public interface GetPlatformMapper {

     String selectOdrUsers(String sessionId);
     List<PetitionType> masterTypesSearch1(String getPlatformId);
     List<PetitionType> masterTypesSearch2(String getPlatformId);
     Status masterPlatformsSearch(String getPlatformId);
     List<ExpandItems> expandProjectSearch(String getPlatformId);
}
