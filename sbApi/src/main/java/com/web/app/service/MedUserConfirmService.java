package com.web.app.service;

import com.web.app.domain.Entity.Cases;
import com.web.app.domain.MedUserConfirm.MedUserConfirmSession;
import com.web.app.domain.MedUserConfirm.MediatorInfo;
import com.web.app.domain.MedUserConfirm.OdrUsers;

/**
 * 調停人確認画面
 * 
 * @author DUC 李志文 馬芹
 * @since 2024/05/06
 * @version 1.0
 */
public interface MedUserConfirmService {
    OdrUsers getOdrUserInfo(MedUserConfirmSession medUserConfirmSession)throws Exception;
    MediatorInfo getMediatorInfo(MedUserConfirmSession medUserConfirmSession)throws Exception;
    //ファイル名取得
    String GetFileName(String fileId);

    //調停変更回数取得
    Cases SelCases(String caseId);
}
