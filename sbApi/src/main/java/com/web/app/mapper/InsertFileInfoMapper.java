package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.MedUserChange.InsertFileInfo;

/**
 * ファイル関連情報更新Mapper
 * 
 * @author DUC 耿浩哲
 * @since 2024/05/06
 * @version 1.0
 */
@Mapper
public interface InsertFileInfoMapper {
    // 添付ファイルinsert
    int insertFile(InsertFileInfo insertFileInfo);
    // 案件-添付ファイルリレーションinsert
    int insertCaseFileRelations(InsertFileInfo insertFileInfo);
}
