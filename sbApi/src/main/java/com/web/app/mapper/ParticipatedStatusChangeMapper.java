package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.ParticipatedStatusChangeSelectInfo;

@Mapper
public interface ParticipatedStatusChangeMapper {
    ParticipatedStatusChangeSelectInfo participatedStatusChangeInfoSearch(String caseId);

    int caseStatusChangeUpdate(String caseId);

}
