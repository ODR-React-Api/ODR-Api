package com.web.app.service.impl;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.UpdNegotiatCon;
import com.web.app.mapper.UpdNegotiatConMapper;
import com.web.app.service.UpdNegotiatConService;

@Service
public class UpdNegotiatConServiceImpl implements UpdNegotiatConService {

    @Autowired
    private UpdNegotiatConMapper updNegotiatConMapper;

    @Override
    @Transactional
    public int updateNegotiatData(UpdNegotiatCon updNegotiatCon) {

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
        // 调停人
        if (userEmail.getEmail().equals(relationsEmail.getMediatorUserEmail())) {
            userstatus = "3";
        }

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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        updNegotiatCon.setLastModifiedDate(sdf.format(System.currentTimeMillis()));
        return updNegotiatConMapper.setNegotiationStatus(updNegotiatCon);

    }

}
