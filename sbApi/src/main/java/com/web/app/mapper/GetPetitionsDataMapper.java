package com.web.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.PetitionsData;

@Mapper
public interface GetPetitionsDataMapper {

    List<PetitionsData> getPetitionsData(String caseId, String plateFormId);
    
}
