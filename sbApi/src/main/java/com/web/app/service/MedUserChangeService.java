package com.web.app.service;

import com.web.app.domain.MedUserChange.InsertFileInfo;

/**
 * 調停人変更画面Service
 * 
 * @author DUC 耿浩哲
 * @since 2024/05/06
 * @version 1.0
 */
public interface MedUserChangeService {

    int insertFileInfo(InsertFileInfo insertFileInfo) throws Exception;

}
