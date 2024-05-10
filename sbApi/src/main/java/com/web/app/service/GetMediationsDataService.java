package com.web.app.service;

import com.web.app.domain.getMediationsData.ResultMediation;

/**
 * サービスインタフェース
 * 
 * @author DUC 徐義然
 * @since 2024/05/07
 * @version 1.0
 */
public interface GetMediationsDataService {
    
    ResultMediation getResultMediation(ResultMediation resultMediation);
}
