package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.app.mapper.UpdNegotiatConMapper;
import com.web.app.service.UpdNegotiatConService;

@Service
public class UpdNegotiatConServiceImpl implements UpdNegotiatConService{
    @Autowired
    private UpdNegotiatConMapper updNegotiatConMapper;

    @Override
    public int updNegotiat(String id, Integer userstatus){
        
        String updNegotiatCon = updNegotiatConMapper.getNegotiationStatus(id);

        if (updNegotiatCon == "4" && updNegotiatCon == "5") {
            updNegotiatConMapper.setNegotiationStatus("6");
        }
        if (updNegotiatCon == "3") {
            //ログインユーザが申立人
            if(userstatus=='0'){
                updNegotiatConMapper.setNegotiationStatus("4");
            // ログインユーザが相手方
            }else if(userstatus=='1'){
                updNegotiatConMapper.setNegotiationStatus("5");
            }
        }
        return 1;

    }

}
