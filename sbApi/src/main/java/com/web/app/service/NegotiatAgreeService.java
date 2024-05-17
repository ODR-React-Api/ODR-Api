package com.web.app.service;

import com.web.app.domain.NegotiatAgree.CaseEstablish;

/**
 * S20_和解案合意画面
 * Service層
 * NegotiatAgreeService
 * 
 * @author DUC 馮淑慧
 * @since 2024/05/09
 * @version 1.0
 */
public interface NegotiatAgreeService {

    // API_案件成立更新
    int updCaseEstablish(CaseEstablish caseEstablish);
}
