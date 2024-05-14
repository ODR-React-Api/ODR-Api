package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.NegotiatMake.CaseFileRelations;
import com.web.app.domain.NegotiatMake.Files;
import com.web.app.domain.NegotiatMake.Negotiations;

@Mapper
public interface UpdNegotiationsEditMapper {

    int updateNegotiations(Negotiations negotiationsEdit);

    int deleteFiles(Files files);

    int deleteCaseFileRelations(CaseFileRelations caseFileRelations);

}
