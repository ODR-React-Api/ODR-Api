package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.app.mapper.InsRelationsTempMapper;
import com.web.app.service.MosLoginService;

/**
 * S8_申立登録画面
 * Service層実現類
 * MosLoginServiceImpl
 * 
 * @author DUC 閆文静
 * @since 2024/05/08
 * @version 1.0
 */
@Service
public class MosLoginServiceImpl implements MosLoginService {
    @Autowired
    private InsRelationsTempMapper insRelationsTempMapper;

    /**
     * TBL「申立（case_petitions）」の新規登録
     * 
     * @param uuId      自動採番
     * @param loginUser ログインユーザ
     */
    @Override
    public int insCasePetitions(String uuId, String loginUser) {
        return insRelationsTempMapper.insCasePetitions(uuId, loginUser);
    }

    /**
     * TBL「案件別個人情報リレーション（case_relations）」の新規登録
     * 
     * @param uuId      自動採番
     * @param loginUser ログインユーザ
     * @param userId    セッション.ユーザID
     */
    @Override
    public int insCaseRelations(String uuId, String loginUser, String userId) {
        return insRelationsTempMapper.insCaseRelations(uuId, loginUser, userId);
    }
}
