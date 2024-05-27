package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.Entity.CaseFileRelations;
import com.web.app.domain.Entity.CaseMediations;
import com.web.app.domain.Entity.Files;

/**
 * 調停案データ新規登録
 * 
 * @author DUC 賈文志
 * @since 2024/05/24
 * @version 1.0
 */
@Mapper
public interface InsMediationsDataMapper {

    // 「調停案」新規登録
    int insMediationsData(CaseMediations CaseMediations);

    // 「添付ファイル」の新規登録
    int insertFiles(Files insFiles);

    // 「案件-添付ファイルリレーション」新規登録
    int insCaseFileRelations(CaseFileRelations caseFileRelations);

}