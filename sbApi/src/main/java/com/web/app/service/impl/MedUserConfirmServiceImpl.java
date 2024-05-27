package com.web.app.service.impl;

import com.web.app.service.MedUserConfirmService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.medUserConfirm.GetMediatorGen;
import com.web.app.domain.medUserConfirm.GetUserIDbyMail;
import com.web.app.mapper.GetMediationStatusMapper;
import com.web.app.mapper.GetUserIDbyMailMapper;
import com.web.app.mapper.GetMediatorGenMapper;

/**
 * 調停案ステータス取得
 * 調停者メールとユザーIDを取得
 * 調停人情報取得
 * 
 * @author DUC 賈文志
 * @since 2024/05/20
 * @version 1.0
 */

@Service
public class MedUserConfirmServiceImpl implements MedUserConfirmService {

    @Autowired
    private GetMediationStatusMapper getMediationStatusMapper;
    @Autowired
    private GetUserIDbyMailMapper getUserIDbyMailMapper;
    @Autowired
    private GetMediatorGenMapper getMediatorGenMapper;

    /**
     * 
     * 調停案ステータス取得
     * 
     * @param CaseId 受付カウンターからの案件ID
     * @return 調停案ステータスを取得する
     */
    @Override
    public String getMediationStatus(String CaseId) {
        // 調停案ステータス取得
        String getMediationStatus = getMediationStatusMapper.getMediationStatus(CaseId);
        return getMediationStatus;
    }

    /**
     * 
     * 調停者メールとユザーIDを取得
     * 
     * @param CaseId 受付カウンターからの案件ID
     * @return 調停者メールボックスとユーザーID
     */
    @Override
    public GetUserIDbyMail getUserIDbyMail(String CaseId) {
        // 取得したコーディネータメールボックスとユーザーIDを保存する
        GetUserIDbyMail getUserIDbyMail = new GetUserIDbyMail();
        // 調停人メール取得
        String mediatorUserEmail = getUserIDbyMailMapper.mediatorUserEmail(CaseId);
        getUserIDbyMail.setMediatorUserEmail(mediatorUserEmail);
        // 調停者メールボックスからユーザUidを取得する
        String userUid = getUserIDbyMailMapper.userUid(mediatorUserEmail);
        getUserIDbyMail.setUid(userUid);
        // 取得した調停者メールボックスとユーザーUidを返す
        return getUserIDbyMail;
    }

    /**
     * 
     * 調停人情報取得
     * 
     * @param CaseId 受付カウンターからの案件ID
     * @return 調停人情報
     */
    @Override
    public ArrayList<GetMediatorGen> getMediatorGen(String CaseId) {
        // 調停人情報取得
        ArrayList<GetMediatorGen> getMediatorGen = getMediatorGenMapper.getMediatorGen(CaseId);
        return getMediatorGen;
    }
}
