package com.web.app.service;

import com.web.app.domain.mediationsMake.InsMediationsData;

/**
 * 調停案データ新規登録
 * 
 * @author DUC 賈文志
 * @since 2024/05/20
 * @version 1.0
 */
public interface MediationsMakeService {
    // 調停案データ新規登録
    int insMediationsData(InsMediationsData mediationcase);

}