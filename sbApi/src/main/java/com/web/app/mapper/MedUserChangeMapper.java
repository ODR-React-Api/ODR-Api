package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.MedUserChange.InsertFileInfo;

@Mapper
public interface MedUserChangeMapper {
    // 添付ファイルinsert
    int insertFile(InsertFileInfo insertFileInfo);
    // 案件-添付ファイルリレーションinsert
    int insertCaseFileRelations(InsertFileInfo insertFileInfo);
}
