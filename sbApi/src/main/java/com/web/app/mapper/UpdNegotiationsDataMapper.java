package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.Entity.CaseFileRelations;
import com.web.app.domain.Entity.CaseNegotiations;
import com.web.app.domain.Entity.File;

@Mapper
public interface UpdNegotiationsDataMapper {
    CaseNegotiations SearchCaseNegotiations(String caseId);

    int UpCaseNegotiations(CaseNegotiations caseNegotiations);
    int UpFile(File file);
    int UpCaseFileRelations(CaseFileRelations caseFileRelations);
}
