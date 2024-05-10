package com.web.app.service;

import com.web.app.domain.MedDateExtension.CasesForMediationEndDate;

/**
 * 和解案確認更新API Service
 * 
 * @author DUC 王 エンエン
 * @since 2024/05/06
 * @version 1.0
 */
public interface UpdCasesForMediationEndDateService {
    
    int updCasesForMediationEndDate(CasesForMediationEndDate updCasesForMediationEndDateResult);
}
