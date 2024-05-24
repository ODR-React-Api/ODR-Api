package com.web.app.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.mapper.GetNegotiationExtendDaysMapper;
import com.web.app.mapper.GetToCaseInfoMapper;
import com.web.app.service.DateExtensionService;

/**
 * 期日延長画面ServiceImpl
 * 
 * 交渉中の相手に、一回だけ交渉期日の延長申請を行うことができる。
 * 
 * @author DUC 徐義然 耿浩哲
 * @since 2024/05/02
 * @version 1.0
 */
@Service
public class DateExtensionServiceImpl implements DateExtensionService {

    //交渉期限延長可能日数取得マッパーオブジェクト
    @Autowired
    private GetNegotiationExtendDaysMapper getNegotiationExtendDaysMapper;

    @Autowired
    private GetToCaseInfoMapper getToCaseInfoMapper;

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

    /**
     * 案件情報取得API
     *
     * @param CaseId 案件ID
     * @param PlatformId プラットフォームID
     * @return 交渉期限日
     * @throws Exception エラーの説明内容
     */
    @Override
    public Date getToCaseInfo(String CaseId, String PlatformId) throws Exception {
        return getToCaseInfoMapper.getToCaseInfo(CaseId, PlatformId);
    }
}
