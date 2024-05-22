package com.web.app.service;

import com.web.app.domain.MosDetail.ParticipatedStatusChangeResultInfo;

/**
 * 申立て詳細画面_概要Service
 * 
 * @author DUC 朱暁芳
 * @since 2024/04/17
 * @version 1.0
 */
public interface MosDetailService {
    // 参加済状態変更
    ParticipatedStatusChangeResultInfo participatedStatusSearch(String caseId, String uId);
}
