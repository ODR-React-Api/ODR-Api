package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.Entity.CaseFileRelations;
import com.web.app.domain.Entity.CaseMediations;
import com.web.app.domain.Entity.Files;
import com.web.app.domain.mediationsMake.InsMediationsData;

@Mapper
public interface InsMediationsDataMapper {
    int insMediationsData2(CaseMediations CaseMediations);

    int insertFiles(Files insertFiles);

    int insCaseFileRelations(CaseFileRelations caseFileRelations);

    CaseMediations mediationCount(InsMediationsData mediationcase);

    int dataSearch(InsMediationsData mediationcase);

}