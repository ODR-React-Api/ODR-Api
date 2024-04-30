package com.web.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import com.web.app.domain.FileId;
import com.web.app.domain.PetitionInfo;
import com.web.app.domain.PetitionTemp;
import com.web.app.domain.ScaleItems;


@Mapper
public interface GetPetitionsTempMapper {
     PetitionTemp selectPetitionsTemp(String sessionId);
     PetitionInfo selectOdrUsers(String sessionId,String infoEmail);
     List<FileId> selectFileId(String casePetition); 
     List<ScaleItems> scaleItemsSearch(String platformId);    
}
