package com.web.app.service.impl;

import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.web.app.mapper.UpdCaseCancelDateMapper;
import com.web.app.service.UsesaseCancelService;

/**
 * 手続き中止画面
 * 
 * 任意のタイミングで、
 * 当事者はODR手続きを中止して申立てを終了させる
 * 
 * @author DUC 徐義然
 * @since 2024/05/16
 * @version 1.0
 */
@Service
public class UsesaseCancelServiceImpl implements UsesaseCancelService {

    // 手続き中止マッパーオブジェクト
    @Autowired
    private UpdCaseCancelDateMapper updCaseCancelDateMapper;


    /**
     * 
     * API_ID:手続き中止API
     * 
     * 手続き中止を行う
     * 案件ステージ、案件ステータス、手続き中止日を更新する。
     * 
     * @param mediationId:セッション.案件ID
     * @return 更新レコード数
     */
    @Override
    @Transactional
    public int updCaseCancelDate(String mediationId){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return updCaseCancelDateMapper.updCaseCancelDate(sdf.format(System.currentTimeMillis()),sdf.format(System.currentTimeMillis()),mediationId);
    }
}
