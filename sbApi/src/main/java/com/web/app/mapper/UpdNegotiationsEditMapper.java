package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.Entity.CaseFileRelations;
import com.web.app.domain.Entity.Files;
import com.web.app.domain.Entity.CaseNegotiations;

@Mapper
public interface UpdNegotiationsEditMapper {

    int updateCaseNegotiations(CaseNegotiations caseNegotiations);

    int deleteFiles(Files files);

    int deleteCaseFileRelations(CaseFileRelations caseFileRelations);

}
