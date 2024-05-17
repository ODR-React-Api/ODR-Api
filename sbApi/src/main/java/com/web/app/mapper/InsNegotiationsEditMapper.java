package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.NegotiatMake.CaseFileRelations;
import com.web.app.domain.NegotiatMake.Files;
import com.web.app.domain.NegotiatMake.Negotiations;

@Mapper
public interface InsNegotiationsEditMapper {

    int insertNegotiations(Negotiations negotiationsEdit);

    int insertFiles(Files files);

    int insertCaseFileRelations(CaseFileRelations caseFileRelations);

}
