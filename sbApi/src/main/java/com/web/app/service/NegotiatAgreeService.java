package com.web.app.service;

import com.web.app.domain.negotiatAgree.Negotiation;

/**
 * サービスインタフェース
 * 
 * @author DUC 徐義然
 * @since 2024/05/06
 * @version 1.0
 */
public interface NegotiatAgreeService {

    int updateNegotiatData(Negotiation negotiation);
}
