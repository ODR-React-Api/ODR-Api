package com.web.app.service;

import com.web.app.domain.mediationsMake.ResultMediation;

/**
 * サービスインタフェース
 * 
 * @author DUC 徐義然
 * @since 2024/05/07
 * @version 1.0
 */
public interface MediationsMakeService {
    
    ResultMediation getResultMediation(ResultMediation resultMediation);
    
    int isExistMediations(String mediationId);
}
