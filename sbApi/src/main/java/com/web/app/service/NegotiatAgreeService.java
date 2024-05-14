package com.web.app.service;

import com.web.app.domain.negotiatAgree.Negotiation;

/**
 * 
 * 和解案合意画面
 * 
 * 本画面は、和解案のプレビュー表示と
 * 確認書表示画面です。作成した和解案の合意、
 * 拒否および確認を行う。
 * 
 * @author DUC 徐義然
 * @since 2024/05/06
 * @version 1.0
 */
public interface NegotiatAgreeService {
    //API_ID:和解案拒否更新
    int updNegotiatDeny(Negotiation negotiation);
}
