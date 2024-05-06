package com.web.app.service.impl;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.Negotiation;
import com.web.app.mapper.UpdNegotiatDenyMapper;
import com.web.app.service.UpdNegotiatDenyService;

import org.springframework.stereotype.Service;

@Service
public class UpdNegotiatDenyServiceImpl implements UpdNegotiatDenyService {

    @Autowired
    private UpdNegotiatDenyMapper updNegotiatDenyMapper;

    @Override
    @Transactional
    public int updateNegotiatData(Negotiation negotiation){
        Integer status = updNegotiatDenyMapper.getNegotiationStatus(negotiation.getNegotiationId());
        if(status == 2 || status == 12){
            negotiation.setStatus(7);
        }else if (status == 9 || status == 15) {
            negotiation.setStatus(10);
        }else{
            negotiation.setStatus(status);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        negotiation.setLastModifiedDate(sdf.format(System.currentTimeMillis()));
        return updNegotiatDenyMapper.setNegotiationStatus(negotiation);
    }

}
