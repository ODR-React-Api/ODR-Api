package com.web.app.service.impl;

import com.web.app.service.MedUserConfirmService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.app.mapper.GetMediationStatusMapper;
import com.web.app.domain.MediateUser;

/**
 * 調停案ステータス取得
 * 調停人メール取得
 * 調停人情報取得
 * 
 * @author DUC jiawenzhi
 * @since 2024/05/10
 * @version 1.0
 */

@Service
public class MedUserConfirmImpl implements MedUserConfirmService {

    @Autowired
    private GetMediationStatusMapper mediationMapper;

    /**
     * 
     * 調停案ステータス取得
     * 
     * @param mediateUser 受付カウンターからの案件ID
     * @return 調停案ステータスを取得する
     */
    @Override
    public String Mediationstatus(String CaseId) {
        String Mediationstatus = mediationMapper.Mediationstatus(CaseId);
        return Mediationstatus;
    }

    @Override
    public MediateUser MediationEmail(MediateUser mediateUser) {
        MediateUser MediationEmail = new MediateUser();
        // 检索调停人Email
        String MediatorUserEmail = mediationMapper.MediatorUserEmail(mediateUser);
        MediationEmail.setMediatorUserEmail(MediatorUserEmail);
        // 检索Uid
        String MediatorUserUid = mediationMapper.MediatorUserUid(MediatorUserEmail);
        MediationEmail.setUid(MediatorUserUid);
        return MediationEmail;
    }

    @Override
    public ArrayList<MediateUser> MediatorIntelligence(MediateUser mediateUser) {
        ArrayList<MediateUser> MediatorIntelligence = mediationMapper.MediatorIntelligence(mediateUser);
        return MediatorIntelligence;
    }
}
