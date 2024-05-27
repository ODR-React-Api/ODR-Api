package com.web.app.service;

import com.web.app.domain.Entity.CaseNegotiations;
import com.web.app.domain.NegotiatAgree.NegotiatAgree;
import com.web.app.domain.NegotiatAgree.Negotiation;
import com.web.app.domain.NegotiatAgree.UpdNegotiatAgree;
import com.web.app.domain.NegotiatAgree.UpdNegotiatCon;

/**
 * 
 * 和解案合意画面
 * 
 * 本画面は、和解案のプレビュー表示と
 * 確認書表示画面です。作成した和解案の合意、
 * 拒否および確認を行う。
 * 
 * @author DUC 徐義然 李志文 賈文志 王 エンエン
 * @since 2024/05/06
 * @version 1.0
 */
public interface NegotiatAgreeService {
    //和解案確認データ取得
    CaseNegotiations SelCaseNegotiations(NegotiatAgree negotiatAgree);

    // 和解案合意更新
    Boolean updNegotiatAgree(UpdNegotiatAgree updNegotiatAgree);
    //API_ID:和解案拒否更新
    int updNegotiatDeny(Negotiation negotiation);
    
    int updateNegotiatData(UpdNegotiatCon updNegotiatCon);

}
