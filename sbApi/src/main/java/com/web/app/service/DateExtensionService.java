package com.web.app.service;

import java.util.Date;


/**
 * 期日延長画面Service
 * 
 * @author DUC 耿浩哲
 * @since 2024/05/02
 * @version 1.0
 */
public interface DateExtensionService {

    Date getToCaseInfo(String CaseId, String PlatformId) throws Exception;

}
