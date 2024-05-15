package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.mediationsMake.Mediation;


/**
 * API_ID:調停案データ更新API
 * 調停案が存在する場合、画面入力したデータをDBへ更新を行う
 * 
 * @author DUC 徐義然
 * @since 2024/05/10
 * @version 1.0
 */

@Mapper
public interface SaveMeditonMapper {
    //調停案の存在を判断する
    int isExistMediations(String mediationId);
    //調停案更新
    int updateMediations(Mediation mediation);
    //「添付ファイル」論理削除
    int deleteFiles(Mediation mediation);
    //「案件-添付ファイルリレーション」論理削除
    int deleteFileRelations(Mediation mediation);
    //「添付ファイル」の新規登録
    int addFiles(Mediation mediation);
    //「案件-添付ファイルリレーション」新規登録
    int addFileRelations(Mediation mediation);
}
