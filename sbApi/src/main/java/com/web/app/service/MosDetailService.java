package com.web.app.service;

import com.web.app.domain.Entity.CaseRelations;

/**
 * 申立て詳細画面Service
 * 
 * @author DUC 耿浩哲
 * @since 2024/04/25
 * @version 1.0
 */
public interface MosDetailService {

    CaseRelations getCaseRelations(String CaseId) throws Exception;

}
