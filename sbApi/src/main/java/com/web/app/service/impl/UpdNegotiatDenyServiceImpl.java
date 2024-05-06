package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.Negotiation;
import com.web.app.mapper.UpdNegotiatDenyMapper;
import com.web.app.service.UpdNegotiatDenyService;

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
        negotiation.setLastModifiedDate(new java.sql.Date(new java.util.Date().getTime()));
        return updNegotiatDenyMapper.setNegotiationStatus(negotiation);
    }
}
