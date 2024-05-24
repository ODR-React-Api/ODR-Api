package com.web.app.service;

import com.web.app.domain.mediationsMake.ResultMediation;

/**
 * 調停案作成画面
 * 
 * @author DUC 賈文志
 * @since 2024/05/24
 * @version 1.0
 */
public interface MediationsMakeService {

    // API_ID:調停案データ取得API
    ResultMediation getMediationsData(ResultMediation resultMediation);

    // 調停案の存在を判断する
    int isExistMediations(String mediationId);

    // API_ID:調停案データ更新API
    void saveMediton(ResultMediation resultMediation) throws Exception;

    // 調停案データ新規登録
    int insMediationsData(ResultMediation resultMediation);

}