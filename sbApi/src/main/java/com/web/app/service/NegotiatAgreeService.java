package com.web.app.service;

import com.web.app.domain.Entity.CaseNegotiations;
import com.web.app.domain.NegotiatAgree.NegotiatAgree;
import com.web.app.domain.NegotiatAgree.UpdNegotiatAgree;;

/**
 * 和解案合意更新
 * 
 * @author DUC 李志文 賈文志
 * @since 2024/05/13
 * @version 1.0
 */
public interface NegotiatAgreeService {
    //和解案確認データ取得
    CaseNegotiations SelCaseNegotiations(NegotiatAgree negotiatAgree);

    // 和解案合意更新
    Boolean updNegotiatAgree(UpdNegotiatAgree updNegotiatAgree);

}
