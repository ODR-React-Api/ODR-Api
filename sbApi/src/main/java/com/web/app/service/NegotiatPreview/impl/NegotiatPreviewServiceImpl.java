package com.web.app.service.NegotiatPreview.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.NegotiatPreview.CaseFileRelations;
import com.web.app.domain.NegotiatPreview.CaseNegotiations;
import com.web.app.domain.NegotiatPreview.File;
import com.web.app.domain.constants.Constants;
import com.web.app.mapper.NegotiatPreview.NegotiationsDataMapper;
import com.web.app.service.NegotiatPreview.NegotiatPreviewService;
import com.web.app.service.impl.UtilServiceImpl;

@Service
public class NegotiatPreviewServiceImpl implements NegotiatPreviewService {
    @Autowired
    private NegotiationsDataMapper negotiationsDataMapper;

    @Autowired
    private UtilServiceImpl utilServiceImpl;

    @Override
    public int SearchCaseNegotiations(CaseNegotiations caseNegotiations, File file) {
        CaseNegotiations cNegotiations = negotiationsDataMapper.SearchCaseNegotiations(caseNegotiations.getCaseId());
        if (cNegotiations != null) {
            if (caseNegotiations.getStatus().equals("0") ||
                    caseNegotiations.getStatus().equals("1") ||
                    caseNegotiations.getStatus().equals("2")) {
                caseNegotiations.setStatus("2");
            } else if (caseNegotiations.getStatus().equals("7") ||
                    caseNegotiations.getStatus().equals("8") ||
                    caseNegotiations.getStatus().equals("9")) {
                caseNegotiations.setStatus("9");
            } else if (caseNegotiations.getStatus().equals("10") ||
                    caseNegotiations.getStatus().equals("11") ||
                    caseNegotiations.getStatus().equals("12")) {
                caseNegotiations.setStatus("12");
            } else if (caseNegotiations.getStatus().equals("13") ||
                    caseNegotiations.getStatus().equals("14") ||
                    caseNegotiations.getStatus().equals("15")) {
                caseNegotiations.setStatus("15");
            }
            UpdNegotiationsData(caseNegotiations, file);
        } else {

            InsNegotiationData(caseNegotiations, file);
        }
        return 1;
    }

    @Override
    public int InsNegotiationData(CaseNegotiations caseNegotiations, File file) {
        caseNegotiations.setId(utilServiceImpl.GetGuid());
        file.setId(utilServiceImpl.GetGuid());
        int addCaseNegotiationsStatus = negotiationsDataMapper.AddCaseNegotiations(caseNegotiations);
        int addFileStatus = negotiationsDataMapper.AddFile(file);

        CaseFileRelations caseFileRelations = new CaseFileRelations();
        caseFileRelations.setId(utilServiceImpl.GetGuid());
        caseFileRelations.setPlatformId(caseNegotiations.getPlatformId());
        caseFileRelations.setCaseId(caseNegotiations.getCaseId());
        caseFileRelations.setRelatedId(caseNegotiations.getId());
        caseFileRelations.setFileId(file.getId());
        int addCaseFileRelationsStatus = negotiationsDataMapper.AddCaseFileRelations(caseFileRelations);
        if (addCaseNegotiationsStatus == 1 && addFileStatus == 1 && addCaseFileRelationsStatus == 1) {
            return Constants.RESULT_STATE_SUCCESS;
        } else {
            return Constants.RESULT_STATE_ERROR;
        }
    }

    @Override
    public int UpdNegotiationsData(CaseNegotiations caseNegotiations, File file) {
        int upCaseNegotiationsStatus = negotiationsDataMapper.UpCaseNegotiations(caseNegotiations);
        int upFileStatus = negotiationsDataMapper.UpFile(file);
        CaseFileRelations caseFileRelations = new CaseFileRelations();
        caseFileRelations.setFileId(file.getId());
        int upCaseFileRelationsStatus = negotiationsDataMapper.UpCaseFileRelations(caseFileRelations);

        int addFileStatus = negotiationsDataMapper.AddFile(file);
        int addCaseFileRelationsStatus = negotiationsDataMapper.AddCaseFileRelations(caseFileRelations);
        if (upCaseNegotiationsStatus == 1 && upFileStatus == 1 && upCaseFileRelationsStatus == 1) {
            return Constants.RESULT_STATE_SUCCESS;
        } else {
            return Constants.RESULT_STATE_ERROR;
        }
    }

    // @Override
    // public int AddCaseNegotiations(CaseNegotiations caseNegotiations) {
    // return negotiationsDataMapper.AddCaseNegotiations(caseNegotiations);
    // }

    // @Override
    // public int AddFile(File file) {
    // return negotiationsDataMapper.AddFile(file);
    // }

    // @Override
    // public int AddCaseFileRelations(CaseFileRelations caseFileRelations) {
    // return negotiationsDataMapper.AddCaseFileRelations(caseFileRelations);
    // }

    // @Override
    // public int UpCaseNegotiations(CaseNegotiations caseNegotiations) {
    // return negotiationsDataMapper.UpCaseNegotiations(caseNegotiations);
    // }

    // @Override
    // public int UpFile(File file) {
    // return negotiationsDataMapper.UpFile(file);
    // }

    // @Override
    // public int UpCaseFileRelations(CaseFileRelations caseFileRelations) {
    // return negotiationsDataMapper.UpCaseFileRelations(caseFileRelations);
    // }
}
