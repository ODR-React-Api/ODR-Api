package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * S08_申立て登録画面
 * API_下書き用準備データ登録
 * Mapper層
 * InsRelationsTempMapper
 * 
 * @author DUC 祭卉康
 * @since 2024/06/17
 * @version 1.0
 */
@Mapper
public interface InsRelationsTempMapper {

    // TBL「申立（case_petitions）」の新規登録
    int insCasePetitions(String id, String loginUser);

    // TBL「案件別個人情報リレーション（case_relations）」の新規登録
    int insCaseRelations(String id, String userId, String loginUser);

}
