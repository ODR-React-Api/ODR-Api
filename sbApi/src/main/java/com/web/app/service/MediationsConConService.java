package com.web.app.service;

import java.util.List;

import com.web.app.domain.MediationsConCon.MediationsContent;
import com.web.app.domain.MediationsConCon.MediationsTemplate;
import com.web.app.domain.MediationsConCon.MediationsUserData;

/**
 * 調停案内容確認画面
 * Service层
 * MediationsConConService
 * 
 * 
 * @author DUC 王 エンエン
 * @since 2024/05/10
 * @version 1.0
 */
public interface MediationsConConService {
    // API_ユーザデータ取得
    MediationsUserData findAllUser(String caseId, String platformId);

    // API_調停案テンプレート取得
    List<MediationsTemplate> findMediationsTemplate(String platformId, String languageId, Integer templateType);

    // API_調停案更新
    int upMediationsContent(MediationsContent mediationsContent);
}
