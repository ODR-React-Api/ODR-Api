package com.web.app.service;

import java.util.Date;

/**
 * S29_調停期日延長画面
 * Service层
 * MedDateExtensionService
 * 
 * @author DUC 王 エンエン
 * @since 2024/05/16
 * @version 1.0
 */
public interface MedDateExtensionService {
    // API_案件情報更新
    int updCasesForMediationEndDate(Date mediationEndDate, String cid);
}
