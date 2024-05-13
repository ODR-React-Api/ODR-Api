package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.NegotiatMake.CaseFileRelations;
import com.web.app.domain.NegotiatMake.Files;
import com.web.app.domain.NegotiatMake.Negotiations;

import java.util.List;

@Mapper
public interface InsNegotiationsEditMapper {

    List<Integer> selectStatusList(String caseId, String platformId, int deleteFlag);

    int insertNegotiations(Negotiations negotiationsEdit);

    int insertFiles(Files files);

    int insertCaseFileRelations(CaseFileRelations caseFileRelations);

}
