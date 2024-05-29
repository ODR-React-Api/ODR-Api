package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.Entity.CaseFileRelations;
import com.web.app.domain.Entity.Files;
import com.web.app.domain.Entity.CaseNegotiations;
/**
 * 和解案編集依頼データ更新
 * 
 * @author DUC 馬芹
 * @since 2024/05/10
 * @version 1.0
 */
@Mapper
public interface UpdNegotiationsEditMapper {
    // 「和解案」更新
    int updateCaseNegotiations(CaseNegotiations caseNegotiations);
    // 「添付ファイル」論理削除
    int deleteFiles(Files files);
    // 「案件-添付ファイルリレーション」論理削除
    int deleteCaseFileRelations(CaseFileRelations caseFileRelations);

}
