package com.web.app.service;

import java.util.List;

import com.web.app.domain.RelatedPersonsEmail;

/**
 * S04申立て概要画面
 * Service層
 * MosDetailService
 * 
 * @author DUC 田壮飞
 * @since 2024/05/27
 * @version 1.0
 */
public interface MosDetailService {
    // 関係者メール取得です
    List<RelatedPersonsEmail> GetCaseRelations(String DpId);

}
