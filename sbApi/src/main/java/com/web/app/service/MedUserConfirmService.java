package com.web.app.service;

import com.web.app.domain.MedUserConfirm.MedUserConfirmSession;
import com.web.app.domain.MedUserConfirm.MediatorInfo;
import com.web.app.domain.MedUserConfirm.OdrUsers;

/**
 * 調停人確認画面
 * 
 * @author DUC 馬芹
 * @since 2024/05/06
 * @version 1.0
 */
public interface MedUserConfirmService {
    // 調停人ユーザ情報取得
    OdrUsers getOdrUserInfo(MedUserConfirmSession medUserConfirmSession)throws Exception;
    // 調停人の経験取得
    MediatorInfo getMediatorInfo(MedUserConfirmSession medUserConfirmSession)throws Exception;

}
