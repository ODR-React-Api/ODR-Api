package com.web.app.service.NegotiatPreview;
import com.web.app.domain.NegotiatPreview.CaseFileRelations;
import com.web.app.domain.NegotiatPreview.CaseNegotiations;
import com.web.app.domain.NegotiatPreview.File;

public interface NegotiatPreviewService {
    int SearchCaseNegotiations(CaseNegotiations caseNegotiations, File file);

    // int AddCaseNegotiations(CaseNegotiations caseNegotiations);
    // int AddFile(File file);
    // int AddCaseFileRelations(CaseFileRelations caseFileRelations);

    // int UpCaseNegotiations(CaseNegotiations caseNegotiations);
    // int UpFile(File file);
    // int UpCaseFileRelations(CaseFileRelations caseFileRelations);
    int InsNegotiationData(CaseNegotiations caseNegotiations, File file);

    int UpdNegotiationsData(CaseNegotiations caseNegotiations, File file);
}
