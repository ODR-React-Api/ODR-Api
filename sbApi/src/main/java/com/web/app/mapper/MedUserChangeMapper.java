package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.Entity.Cases;
import com.web.app.domain.MedUserChange.InsertFileInfo;

/**
 * @author HHH
 * @description 针对表【user】的数据库操作Mapper
 * @createDate 2023-01-31 20:38:15
 * @Entity com.web.app.domain.User
 */

@Mapper
public interface MedUserChangeMapper {
    // 添付ファイルinsert
    int insertFile(InsertFileInfo insertFileInfo);

    // 案件-添付ファイルリレーションinsert
    int insertCaseFileRelations(InsertFileInfo insertFileInfo);

    Boolean delAboutCasesMediations(String caseId);

    Boolean updAboutCasesInfo(Cases cases, Boolean withReason);

    Cases getMediatorChangeableCount(String caseId);
}
