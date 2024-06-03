package com.web.app.service;

import java.util.Date;


/**
 * 期日延長画面Service
 * 
 * @author DUC 徐義然 耿浩哲
 * @since 2024/05/02
 * @version 1.0
 */
public interface DateExtensionService {
    //API_ID:交渉期限延長可能日数取得API
    String getNegotiationExtendDays(String platformId);

    // 案件情報取得
    Date getToCaseInfo(String caseId, String platformId) throws Exception;

}
