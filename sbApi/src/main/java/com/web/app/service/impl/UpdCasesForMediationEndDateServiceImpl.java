package com.web.app.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.MedDateExtension.CasesForMediationEndDate;
import com.web.app.mapper.UpdCasesForMediationEndDateMapper;
import com.web.app.service.UpdCasesForMediationEndDateService;

/**
 * サービス実装クラス
 * 
 * @author DUC 王 エンエン
 * @since 2024/05/20
 * @version 1.0
 */
@Service
public class UpdCasesForMediationEndDateServiceImpl implements UpdCasesForMediationEndDateService{

    // マッパーオブジェクト
    @Autowired
    private UpdCasesForMediationEndDateMapper updCasesForMediationEndDateMapper;

    /**
     * 
     * データを処理してDBを更新する
     * 
     * 
     * @param negotiation 更新に使用する案件idが含まれています
     * @return DB更新に成功したレコード
     */
    @Override
    @Transactional
    public int updCasesForMediationEndDate(CasesForMediationEndDate casesForMediationEndDate) {
        Date mediationEndDate = casesForMediationEndDate.getMediationEndDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss.SSS");
        String date = sdf.format(mediationEndDate);
        System.out.println("================date===============");
        System.out.println(date);

        return updCasesForMediationEndDateMapper.setUpdCasesForMediationEndDate(date,casesForMediationEndDate.getCid());
    }
    
    
    

}
