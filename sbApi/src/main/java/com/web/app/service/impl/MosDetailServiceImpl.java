package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.Entity.CaseRelations;
import com.web.app.mapper.GetCaseRelationsMapper;
import com.web.app.service.MosDetailService;

/**
 * S04申立て概要画面
 * Service層実現類
 * MosDetailServiceImpl
 * 
 * @author DUC 田壮飞
 * @since 2024/05/27
 * @version 1.0
 */
@Service
public class MosDetailServiceImpl implements MosDetailService {

    @Autowired
    private GetCaseRelationsMapper getCaseRelationsMapper;

    /**
     * API_関係者メアド取得
     * DpIdに基づいてcase_relationsテーブルから事件を検索した場合、事件関係者のメールアドレスに返信します。
     * 
     * @param caseId 照会対象の案件idです
     * @return List<CaseRelations> 関係者メアド
     * 
     */
    @Override
    public CaseRelations getCaseRelations(String caseId) throws Exception {
        CaseRelations relatedPersonsEmails = getCaseRelationsMapper.findCaseRelations(caseId);
        return relatedPersonsEmails;
    }
}
