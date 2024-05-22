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
    OdrUsers getOdrUserInfo(MedUserConfirmSession medUserConfirmSession)throws Exception;
    MediatorInfo getMediatorInfo(MedUserConfirmSession medUserConfirmSession)throws Exception;

}
