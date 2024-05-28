package com.web.app.service;

import java.util.ArrayList;

import com.web.app.domain.Entity.Cases;
import com.web.app.domain.MedUserConfirm.GetMediatorGen;
import com.web.app.domain.MedUserConfirm.GetUserIDbyMail;
import com.web.app.domain.MedUserConfirm.MedUserConfirmSession;
import com.web.app.domain.MedUserConfirm.MediatorInfo;
import com.web.app.domain.MedUserConfirm.OdrUsers;

/**
 * 調停人確認画面
 * 
 * @author DUC 李志文 馬芹 賈文志
 * @since 2024/05/06
 * @version 1.0
 */
public interface MedUserConfirmService {
    OdrUsers getOdrUserInfo(MedUserConfirmSession medUserConfirmSession)throws Exception;
    MediatorInfo getMediatorInfo(MedUserConfirmSession medUserConfirmSession)throws Exception;
    //ファイル名取得
    String GetFileName(String fileId);

    //調停変更回数取得
    Cases GetMediatorChangeableCount(String caseId);
    // 調停案ステータス取得
    String getMediationStatus(String CaseId);

    // 調停者メールとユザーIDを取得
    GetUserIDbyMail getUserIDbyMail(String CaseId);

    // 調停人情報取得
    ArrayList<GetMediatorGen> getMediatorGen(String CaseId);
}
