package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.Entity.CaseFileRelations;
import com.web.app.domain.Entity.Files;
import com.web.app.domain.Entity.CaseNegotiations;
/**
 * 和解案編集依頼データ新規登録
 * 
 * @author DUC 馬芹
 * @since 2024/05/10
 * @version 1.0
 */
@Mapper
public interface InsNegotiationsEditMapper {
    // 「和解案」新規登録
    int insertCaseNegotiations(CaseNegotiations caseNegotiations);
    // 「添付ファイル」の新規登録									
    int insertFiles(Files files);
    // 「案件-添付ファイルリレーション」新規登録
    int insertCaseFileRelations(CaseFileRelations caseFileRelations);

}
