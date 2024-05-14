package com.web.app.service;

import com.web.app.domain.NegotiatMake.CaseFileRelations;
import com.web.app.domain.NegotiatMake.Files;
import com.web.app.domain.NegotiatMake.Negotiations;

public interface NegotiatMakeService {

    int addNegotiationsEdit(Negotiations negotiationsEdit, Files files, CaseFileRelations caseFileRelations);

    int updateNegotiationsEdit(Negotiations negotiationsEdit, Files files, CaseFileRelations caseFileRelations,
            Files files2, CaseFileRelations caseFileRelations2);

}
