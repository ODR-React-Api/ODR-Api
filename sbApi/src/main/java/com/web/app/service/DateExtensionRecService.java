package com.web.app.service;

import com.web.app.domain.DateExtensionRec.UpdNegotiationEndDate;

/**
 * 期日延長承認画面Service
 * 
 * @author DUC 耿浩哲
 * @since 2024/06/06
 * @version 1.0
 */
public interface DateExtensionRecService {

    // 期日延長申請承認
    int updNegotiationEndDate(UpdNegotiationEndDate updNegotiationEndDate) throws Exception;

}
