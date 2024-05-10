package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.Entity.CaseFileRelations;
import com.web.app.domain.Entity.CaseNegotiations;
import com.web.app.domain.Entity.File;

@Mapper
public interface InsNegotiationDataMapper {
    //CaseNegotiations SearchCaseNegotiations(String caseId);

    int AddCaseNegotiations(CaseNegotiations caseNegotiations);
    int AddFile(File file);
    int AddCaseFileRelations(CaseFileRelations caseFileRelations);

    // int UpCaseNegotiations(CaseNegotiations caseNegotiations);
    // int UpFile(File file);
    // int UpCaseFileRelations(CaseFileRelations caseFileRelations);
}
