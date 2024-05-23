package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.Entity.CaseFileRelations;
import com.web.app.domain.Entity.CaseMediations;
import com.web.app.domain.Entity.Files;
import com.web.app.domain.mediationsMake.InsMediationsData;

/**
 * 調停案データ新規登録
 * 
 * @author DUC 賈文志
 * @since 2024/05/23
 * @version 1.0
 */
@Mapper
public interface InsMediationsDataMapper {

    // 「調停案」にデータが存在するかどうかを判断する
    int mediationDataCount(InsMediationsData mediationcase);

    // 「案件-添付ファイル」、「添付ファイル」データが存在するかどうかを判断する
    int filesDataCount(InsMediationsData mediationcase);

    // 「調停案」新規登録
    int insMediationsData(CaseMediations CaseMediations);

    // 「添付ファイル」の新規登録
    int insertFiles(Files insertFiles);

    // 「案件-添付ファイルリレーション」新規登録
    int insCaseFileRelations(CaseFileRelations caseFileRelations);

}