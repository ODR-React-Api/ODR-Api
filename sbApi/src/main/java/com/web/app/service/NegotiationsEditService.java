package com.web.app.service;

import com.web.app.domain.CaseFileRelations;
import com.web.app.domain.Files;
import com.web.app.domain.NegotiationsEdit;
import java.util.List;

public interface NegotiationsEditService {

    List<Integer> selectStatusList(NegotiationsEdit negotiationsEdit);

    int addNegotiationsEdit(NegotiationsEdit negotiationsEdit, Files files, CaseFileRelations caseFileRelations);

    int updateNegotiationsEdit(NegotiationsEdit negotiationsEdit, Files files, CaseFileRelations caseFileRelations,
            Files files2, CaseFileRelations caseFileRelations2);

}
