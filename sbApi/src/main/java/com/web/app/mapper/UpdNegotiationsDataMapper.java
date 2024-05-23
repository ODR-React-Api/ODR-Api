package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.Entity.CaseFileRelations;
import com.web.app.domain.Entity.CaseNegotiations;
import com.web.app.domain.Entity.CaseRelations;
import com.web.app.domain.Entity.File;

/**
 * 和解案データ更新
 * 
 * @author DUC 李志文
 * @since 2024/05/10
 * @version 1.0
 */
@Mapper
public interface UpdNegotiationsDataMapper {
    //和解案抽出
    CaseNegotiations SearchCaseNegotiations(String caseId);
    //「和解案」更新
    int UpCaseNegotiations(CaseNegotiations caseNegotiations);
    //「添付ファイル」論理削除
    int UpFile(File file);
    //「案件-添付ファイルリレーション」論理削除
    int UpCaseFileRelations(CaseFileRelations caseFileRelations);

    //案件関係者Email
    CaseRelations SearchCaseRelations(String caseId);
}
