package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.NegotiatAgree.Negotiation;

/**
 * 
 * API_ID:和解案拒否更新
 * 和解案データのステータスを「作成待ち」に更新する
 * 
 * @author DUC 徐義然
 * @since 2024/05/06
 * @version 1.0
 */

@Mapper
public interface UpdNegotiatDenyMapper {
    //和解案データのステータス取得
    Integer getNegotiationStatus(String negotiationId);
    //和解案データのステータス更新
    int setNegotiationStatus(Negotiation negotiat);
}
