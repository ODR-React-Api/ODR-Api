package com.web.app.service;

import com.web.app.domain.MosDetail.ParticipatedStatusChangeResultInfo;

/**
 * 参加済状態変更
 * 
 * @author DUC 朱暁芳
 * @since 2024/04/23
 * @version 1.0
 */
public interface MosDetailService {
    ParticipatedStatusChangeResultInfo ParticipatedStatusChangeInfoSearch(String caseId);
}
