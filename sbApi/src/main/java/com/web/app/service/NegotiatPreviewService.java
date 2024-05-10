package com.web.app.service;
import com.web.app.domain.Entity.CaseNegotiations;
import com.web.app.domain.Entity.File;

public interface NegotiatPreviewService {
    int NegotiatPreview(CaseNegotiations caseNegotiations, File file);

    int InsNegotiationData(CaseNegotiations caseNegotiations, File file);

    int UpdNegotiationsData(CaseNegotiations caseNegotiations, File file);
}
