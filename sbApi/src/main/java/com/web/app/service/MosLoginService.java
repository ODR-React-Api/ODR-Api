package com.web.app.service;

/**
 * S8_申立登録画面
 * Service層
 * MosLoginService
 * 
 * @author DUC 閆文静
 * @since 2024/05/08
 * @version 1.0
 */
public interface MosLoginService {
    // TBL「申立（case_petitions）」の新規登録
    int insCasePetitions(String uuId, String loginUser);

    // TBL「案件別個人情報リレーション（case_relations）」の新規登録
    int insCaseRelations(String uuId, String loginUser, String userId);

}
