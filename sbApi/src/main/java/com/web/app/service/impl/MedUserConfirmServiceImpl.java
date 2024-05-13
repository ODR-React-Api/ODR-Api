package com.web.app.service.impl;

import com.web.app.service.MedUserConfirmService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.app.mapper.GetMediationStatusMapper;
import com.web.app.mapper.GetUserIDbyMailMapper;
import com.web.app.domain.MediateUser;

/**
 * 調停案ステータス取得
 * 調停人メール取得
 * 調停人情報取得
 * 
 * @author DUC 賈文志
 * @since 2024/05/13
 * @version 1.0
 */

@Service
public class MedUserConfirmServiceImpl implements MedUserConfirmService {

    @Autowired
    private GetMediationStatusMapper getMediationStatusMapper;
    @Autowired
    private GetUserIDbyMailMapper getUserIDbyMailMapper;

    /**
     * 
     * 調停案ステータス取得
     * 
     * @param CaseId 受付カウンターからの案件ID
     * @return 調停案ステータスを取得する
     */
    @Override
    public String GetMediationStatus(String CaseId) {
        //調停案ステータス取得
        String Mediationstatus = getMediationStatusMapper.GetMediationStatus(CaseId);
        return Mediationstatus;
    }

    /**
     * 
     * 調停人メール取得
     * 
     * @param CaseId 受付カウンターからの案件ID
     * @return 調停者メールボックスとユーザーID
     */
    @Override
    public MediateUser GetUserIDbyMail(String CaseId) {
        // 取得したコーディネータメールボックスとユーザーIDを保存する
        MediateUser MediationEmail = new MediateUser();
        // 調停人メール取得
        String MediatorUserEmail = getUserIDbyMailMapper.MediatorUserEmail(CaseId);
        MediationEmail.setMediatorUserEmail(MediatorUserEmail);
        // 調停者メールボックスからユーザUidを取得する
        String MediatorUserUid = getUserIDbyMailMapper.UserUid(MediatorUserEmail);
        MediationEmail.setUid(MediatorUserUid);
        // 取得した調停者メールボックスとユーザーUidを返す
        return MediationEmail;
    }

    @Override
    public ArrayList<MediateUser> GetMediatorInfo(String CaseId) {
        ArrayList<MediateUser> MediatorIntelligence = getUserIDbyMailMapper.GetMediatorInfo(CaseId);
        return MediatorIntelligence;
    }
}
