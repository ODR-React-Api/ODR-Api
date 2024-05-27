package com.web.app.service;


import com.web.app.domain.MediationsMake.ResultMediation;

/**
 * 調停案作成画面
 * 
 * @author DUC 徐義然
 * @since 2024/05/07
 * @version 1.0
 */
public interface MediationsMakeService {
    //API_ID:調停案データ取得API
    ResultMediation getMediationsData(ResultMediation resultMediation);
    //調停案の存在を判断する
    int isExistMediations(String mediationId);
    //API_ID:調停案データ更新API
    void saveMediton(ResultMediation resultMediation) throws Exception;
}
