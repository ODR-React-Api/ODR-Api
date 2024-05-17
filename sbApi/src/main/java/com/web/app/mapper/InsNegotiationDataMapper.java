package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.Entity.CaseFileRelations;
import com.web.app.domain.Entity.CaseNegotiations;
import com.web.app.domain.Entity.File;

/**
 * 和解案データ新規登録
 * 
 * @author DUC 李志文
 * @since 2024/05/10
 * @version 1.0
 */
@Mapper
public interface InsNegotiationDataMapper {
    //「和解案」新規登録
    int AddCaseNegotiations(CaseNegotiations caseNegotiations);
    //「添付ファイル」の新規登録
    int AddFile(File file);
    //「案件-添付ファイルリレーション」新規登録
    int AddCaseFileRelations(CaseFileRelations caseFileRelations);
}
