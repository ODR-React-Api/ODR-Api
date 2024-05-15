package com.web.app.service;


/**
 * 期日延長画面
 * 
 * @author DUC 徐義然
 * @since 2024/05/15
 * @version 1.0
 */
public interface DateExtensionService {
    //API_ID:交渉期限延長可能日数取得API
    String getNegotiationExtendDays(String platformId);
}
