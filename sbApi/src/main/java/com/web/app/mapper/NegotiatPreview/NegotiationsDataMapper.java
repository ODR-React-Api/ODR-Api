package com.web.app.mapper.NegotiatPreview;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.NegotiatPreview.CaseFileRelations;
import com.web.app.domain.NegotiatPreview.CaseNegotiations;
import com.web.app.domain.NegotiatPreview.File;

@Mapper
public interface NegotiationsDataMapper {
    CaseNegotiations SearchCaseNegotiations(String caseId);

    int AddCaseNegotiations(CaseNegotiations caseNegotiations);
    int AddFile(File file);
    int AddCaseFileRelations(CaseFileRelations caseFileRelations);

    int UpCaseNegotiations(CaseNegotiations caseNegotiations);
    int UpFile(File file);
    int UpCaseFileRelations(CaseFileRelations caseFileRelations);
}
