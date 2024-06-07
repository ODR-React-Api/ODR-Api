package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.app.mapper.InsRelationsTempMapper;
import com.web.app.service.MosLoginService;

/**
 * S08_申立て登録画面
 * Service層実現類
 * MosLoginServiceImpl
 * 
 * @author DUC 祭卉康
 * @since 2024/06/17
 * @version 1.0
 */
@Service
public class MosLoginServiceImpl implements MosLoginService {

    @Autowired 
    private InsRelationsTempMapper insRelationsTempMapper;
    
    /**
     * API_下書き用準備データ登録
     * 
     * @param id //自動採番ID
     * @param userId //セッション.ユーザID
     * @param loginUser //ログインユーザ
     * @return case_petitions.idとcase_relations.PetitionUserId
     */
    @Override  
    public int insRelationsTemp(String id, String userId, String loginUser) {

        insRelationsTempMapper.insCasePetitions(id, loginUser);
        int insRelations = insRelationsTempMapper.insCaseRelations(id, userId, loginUser);
        return insRelations;
    }

}


