package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.updNegotiatDeny.Negotiation;

/**
 * マッパー
 * 
 * @author DUC 徐義然
 * @since 2024/05/06
 * @version 1.0
 */

@Mapper
public interface UpdNegotiatDenyMapper {

    Integer getNegotiationStatus(String negotiationId);
    
    int setNegotiationStatus(Negotiation negotiat);
}
