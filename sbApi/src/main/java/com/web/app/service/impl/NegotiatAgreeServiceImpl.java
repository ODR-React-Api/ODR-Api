package com.web.app.service.impl;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.negotiatAgree.Negotiation;
import com.web.app.mapper.UpdNegotiatDenyMapper;
import com.web.app.service.NegotiatAgreeService;

import org.springframework.stereotype.Service;


/**
 * サービス実装クラス
 * 
 * @author DUC 徐義然
 * @since 2024/05/06
 * @version 1.0
 */
@Service
public class NegotiatAgreeServiceImpl implements NegotiatAgreeService {

    //和解案拒否更新マッパーオブジェクト
    @Autowired
    private UpdNegotiatDenyMapper updNegotiatDenyMapper;

    /**
     * 
     * API_ID:和解案拒否更新
     * 
     * データを処理してDBを更新する
     * 
     * @param negotiation 更新に使用するログィンユザと和解案idが含まれています
     * @return DB更新に成功したレコード
     */
    @Override
    @Transactional
    public int updateNegotiatData(Negotiation negotiation){
        //和解案のステータスの取得
        Integer status = updNegotiatDenyMapper.getNegotiationStatus(negotiation.getNegotiationId());
        //更新前Status=２，１２の場合、７で更新する
        //更新前Status=９、１５の場合、１０で更新する
        if(status == 2 || status == 12){
            negotiation.setStatus(7);
        }else if (status == 9 || status == 15) {
            negotiation.setStatus(10);
        }else{
            negotiation.setStatus(status);
        }
        //取得システム時間を文字列に変換してDBに挿入
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        negotiation.setLastModifiedDate(sdf.format(System.currentTimeMillis()));
        return updNegotiatDenyMapper.setNegotiationStatus(negotiation);
    }

}
