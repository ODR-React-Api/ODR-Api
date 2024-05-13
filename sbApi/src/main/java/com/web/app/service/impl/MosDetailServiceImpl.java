package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.Entity.CaseRelations;
import com.web.app.mapper.GetCaseRelationsMapper;
import com.web.app.service.MosDetailService;

/**
 * 申立て詳細画面ServiceImpl
 * 
 * @author DUC 耿浩哲
 * @since 2024/04/25
 * @version 1.0
 */
@Service
public class MosDetailServiceImpl implements MosDetailService {

    @Autowired
    private GetCaseRelationsMapper getCaseRelationsMapper;

    /**
     * 関係者メアド取得ControllerAPI
     *
     * @param CaseId 案件ID
     * @return 案件別個人情報リレーション
     * @throws Exception エラーの説明内容
     */
    @Override
    public CaseRelations getCaseRelations(String CaseId) throws Exception {
        return getCaseRelationsMapper.getCaseRelations(CaseId);
    }
}
