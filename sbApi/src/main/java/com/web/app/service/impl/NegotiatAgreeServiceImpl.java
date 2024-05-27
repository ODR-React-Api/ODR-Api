package com.web.app.service.impl;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.NegotiatAgree.UpdNegotiatCon;
import com.web.app.mapper.UpdNegotiatConMapper;
import com.web.app.service.NegotiatAgreeService;

/**
 * 和解案確認更新 サービス実装クラス
 * 
 * @author DUC 王 エンエン
 * @since 2024/05/06
 * @version 1.0
 */
@Service
public class NegotiatAgreeServiceImpl implements NegotiatAgreeService {

    // マッパーオブジェクト
    @Autowired
    private UpdNegotiatConMapper updNegotiatConMapper;

    /**
     * 
     * データを処理してDBを更新する
     * 
     * 
     * @param negotiation 更新に使用するログィンユザと和解案idと案件idが含まれています
     * @return DB更新に成功したレコード
     */
    @Override
    @Transactional
    public int updateNegotiatData(UpdNegotiatCon updNegotiatCon) {

        // 和解案のステータスの取得
        Integer status = updNegotiatConMapper.getNegotiationStatus(updNegotiatCon.getNegotiationId());
        // 判断身份
        UpdNegotiatCon relationsEmail = updNegotiatConMapper.getRelationsEmail(updNegotiatCon);
        UpdNegotiatCon userEmail = updNegotiatConMapper.getUsersEmail(updNegotiatCon);

        String userstatus = null;
        // 申立人
        if (userEmail.getEmail().equals(relationsEmail.getPetitionUserInfoEmail()) ||
                userEmail.getEmail().equals(relationsEmail.getAgent1Email()) ||
                userEmail.getEmail().equals(relationsEmail.getAgent2Email()) ||
                userEmail.getEmail().equals(relationsEmail.getAgent3Email()) ||
                userEmail.getEmail().equals(relationsEmail.getAgent4Email()) ||
                userEmail.getEmail().equals(relationsEmail.getAgent5Email())) {
            userstatus = "1";
        }

        // 相手方
        if (userEmail.getEmail().equals(relationsEmail.getTraderUserEmail()) ||
                userEmail.getEmail().equals(relationsEmail.getTraderAgent1UserEmail()) ||
                userEmail.getEmail().equals(relationsEmail.getTraderAgent2UserEmail()) ||
                userEmail.getEmail().equals(relationsEmail.getTraderAgent3UserEmail()) ||
                userEmail.getEmail().equals(relationsEmail.getTraderAgent4UserEmail()) ||
                userEmail.getEmail().equals(relationsEmail.getTraderAgent5UserEmail())) {
            userstatus = "2";
        }

        // 更新前Statusが4 or 5の場合、6で更新する
        // 更新前Statusが3の場合、ログインユーザが申立人なら、4で更新する
        // ログインユーザが相手方なら、5で更新する
        try {
            if (status == 4 || status == 5) {
                updNegotiatCon.setStatus(6);
            } else if (status == 3) {
                // ログインユーザが申立人
                if (userstatus != null && userstatus.equals("1")) {
                    updNegotiatCon.setStatus(4);
                    // ログインユーザが相手方
                } else if (userstatus != null && userstatus.equals("2")) {
                    updNegotiatCon.setStatus(5);
                }
            } else {
                updNegotiatCon.setStatus(status);
            }
        } catch (NullPointerException e) {
            updNegotiatCon.setStatus(null);
            System.out.println(updNegotiatCon.getStatus());
        }
        // 取得システム時間を文字列に変換してDBに挿入
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        updNegotiatCon.setLastModifiedDate(sdf.format(System.currentTimeMillis()));
        return updNegotiatConMapper.setNegotiationStatus(updNegotiatCon);
    }
}
