package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.CaseFileRelations;
import com.web.app.domain.Files;
import com.web.app.domain.NegotiationsEdit;

import java.util.List;

@Mapper
public interface NegotiationsEditMapper {

    List<Integer> selectStatusList(String caseId, String platformId, int deleteFlag);

    int insertNegotiationsEdit(NegotiationsEdit negotiationsEdit);

    int insertFiles(Files files);

    int insertCaseFileRelations(CaseFileRelations caseFileRelations);

    int updateNegotiationsEdit(NegotiationsEdit negotiationsEdit);

    int deleteFiles(Files files);

    int deleteCaseFileRelations(CaseFileRelations caseFileRelations);

}
