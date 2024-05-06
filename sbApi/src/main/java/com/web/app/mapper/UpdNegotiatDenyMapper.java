package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.Negotiation;

@Mapper
public interface UpdNegotiatDenyMapper {

    String getNegotiationStatus(String negotiationId);
    
    int setNegotiationStatus(Negotiation negotiat);
}
