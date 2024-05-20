package com.web.app.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.MedDateExtension.CasesForMediationEndDate;
import com.web.app.mapper.UpdCasesForMediationEndDateMapper;
import com.web.app.service.MedDateExtensionService;

/**
 * S29_調停期日延長画面
 * ServiceImpl层
 * MedDateExtensionServiceImpl
 * 
 * @author DUC 王 エンエン
 * @since 2024/05/16
 * @version 1.0
 */
@Service
public class MedDateExtensionServiceImpl implements MedDateExtensionService {

    // マッパーオブジェクト
    @Autowired
    private UpdCasesForMediationEndDateMapper updCasesForMediationEndDateMapper;

    /**
     * API_案件情報更新
     * データを処理してDBを更新する
     * 
     * @param casesForMediationEndDate 更新に使用する案件idが含まれています
     * @return DB更新に成功したレコード
     */
    @Override
    @Transactional
    public int updCasesForMediationEndDate(CasesForMediationEndDate casesForMediationEndDate) {
        Date mediationEndDate = casesForMediationEndDate.getMediationEndDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String date = sdf.format(mediationEndDate);

        return updCasesForMediationEndDateMapper.setUpdCasesForMediationEndDate(date,
                casesForMediationEndDate.getCid());
    }
}
