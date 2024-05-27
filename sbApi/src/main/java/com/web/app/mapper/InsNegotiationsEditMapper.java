package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.Entity.CaseFileRelations;
import com.web.app.domain.Entity.Files;
import com.web.app.domain.Entity.CaseNegotiations;

@Mapper
public interface InsNegotiationsEditMapper {

    int insertCaseNegotiations(CaseNegotiations caseNegotiations);

    int insertFiles(Files files);

    int insertCaseFileRelations(CaseFileRelations caseFileRelations);

}
