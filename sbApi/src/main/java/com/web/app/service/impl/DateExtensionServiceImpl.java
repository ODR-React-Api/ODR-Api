package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.app.mapper.GetNegotiationExtendDaysMapper;
import com.web.app.service.DateExtensionService;


/**
 * 期日延長画面
 * 
 * @author DUC 徐義然
 * @since 2024/05/15
 * @version 1.0
 */
@Service
public class DateExtensionServiceImpl implements DateExtensionService {

    //交渉期限延長可能日数取得マッパーオブジェクト
    @Autowired
    private GetNegotiationExtendDaysMapper getNegotiationExtendDaysMapper;

    /**
     * 
     * API_ID:交渉期限延長可能日数取得
     * 
     * プラットフォームマスタテーブルから交渉期限延長可能日数を取得する。
     * 
     * @param platformId:案件情報取得API(getCaseInfo)の戻り値.PlatformId
     * @return 交渉期限延長可能日数
     */
    @Override
    public String getNegotiationExtendDays(String platformId){
        return getNegotiationExtendDaysMapper.getNegotiationExtendDays(platformId);
    }
}
