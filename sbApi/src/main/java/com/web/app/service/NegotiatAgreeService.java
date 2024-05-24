package com.web.app.service;

import com.web.app.domain.Entity.CaseNegotiations;
import com.web.app.domain.NegotiatAgree.NegotiatAgree;

/**
 * 和解案合意画面
 * 
 * @author DUC 李志文
 * @since 2024/05/14
 * @version 1.0
 */
public interface NegotiatAgreeService {
    //和解案確認データ取得
    CaseNegotiations SelCaseNegotiations(NegotiatAgree negotiatAgree);
}
