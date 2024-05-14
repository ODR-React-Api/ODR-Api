package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.Entity.CaseFileRelations;
import com.web.app.domain.Entity.CaseNegotiations;
import com.web.app.domain.Entity.File;

@Mapper
public interface InsNegotiationDataMapper {

    int AddCaseNegotiations(CaseNegotiations caseNegotiations);
    int AddFile(File file);
    int AddCaseFileRelations(CaseFileRelations caseFileRelations);
}
