package com.web.app.service;

import com.web.app.domain.Entity.Cases;

/**
 * 調停人確認画面
 * 
 * @author DUC 李志文
 * @since 2024/05/16
 * @version 1.0
 */
public interface MedUserConfirmService {
    //ファイル名取得
    String GetFileName(String fileId);

    //調停変更回数取得
    Cases GetMediatorChangeableCount(String caseId);
}
