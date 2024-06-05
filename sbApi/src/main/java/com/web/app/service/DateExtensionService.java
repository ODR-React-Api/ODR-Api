package com.web.app.service;

import com.web.app.domain.Entity.Cases;

/**
 * S26期日延長画面
 * Service層
 * DateExtensionService
 * 
 * @author DUC 田壮飞
 * @since 2024/05/27
 * @version 1.0
 */
public interface DateExtensionService {
    //ユーザ情報取得
    Cases GetCaseInfo(String caseId,String platformId);
}
