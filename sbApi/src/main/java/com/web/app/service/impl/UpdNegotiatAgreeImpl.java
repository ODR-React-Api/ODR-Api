package com.web.app.service.impl;

import com.web.app.service.UpdNegotiatAgreeService;
import com.web.app.service.UtilService;

import java.util.ArrayList;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.web.app.domain.ReconciliationUser;
import com.web.app.mapper.UpdNegotiatAgreeMapper;
import com.web.app.domain.ActionHistories;
import com.web.app.domain.Entity.Cases;
import com.web.app.domain.util.SendMailRequest;

@Service
public class UpdNegotiatAgreeImpl implements UpdNegotiatAgreeService {

    @Autowired
    private UpdNegotiatAgreeMapper ReconciliationUpdate;

    @Autowired
    private UtilService utilService;

    /**
     * 和解案合意更新
     * 
     * @param reconciliationuser 前台伝出のデータ
     * @return 和解案合意更新は成功したか
     */
    @Transactional
    @Override
    public int reconciliationUpdate(ReconciliationUser reconciliationuser) {
        int ReconciliationUpdateStatus = ReconciliationUpdate.reconciliationUpdate(reconciliationuser);
        return ReconciliationUpdateStatus;
    }

    /**
     * 「アクロン履歴」新規登録
     *
     * @param reconciliationuser 前台伝出のデータ
     * @return 「アクロン履歴」新規登録は成功したか
     */
    @Transactional
    @Override
    public int ActionHistoriesInsert(ReconciliationUser reconciliationuser) {
        // 「アクロン履歴」新規登録時に必要な属性の設定
        ActionHistories actionHistories = new ActionHistories();
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        actionHistories.setId(id);
        actionHistories.setPlatformId(reconciliationuser.getPlatformId());
        actionHistories.setCaseId(reconciliationuser.getCaseId());
        actionHistories.setActionType("NegotiationAgreed");
        actionHistories.setCaseStage(3);
        actionHistories.setUserId(reconciliationuser.getUserId());
        // CaseIdに基づいてcase_relationsからデータを取得する
        ActionHistories UserSearch = ReconciliationUpdate.UserSearch(actionHistories);

        // Emailが取得したデータから申請者かどうかを判断する
        // アイデンティティに基づいた「アクロン履歴」新規ログインにおけるUserTypeプロパティの設定
        actionHistories.setEmail(reconciliationuser.getEmail());
        if (actionHistories.getEmail().equals(UserSearch.getPetitionUserInfo_Email()) ||
                actionHistories.getEmail().equals(UserSearch.getAgent1_Email()) ||
                actionHistories.getEmail().equals(UserSearch.getAgent2_Email()) ||
                actionHistories.getEmail().equals(UserSearch.getAgent3_Email()) ||
                actionHistories.getEmail().equals(UserSearch.getAgent4_Email()) ||
                actionHistories.getEmail().equals(UserSearch.getAgent5_Email())) {
            actionHistories.setUserType(1);

        }
        // Emailが取得したデータから相守側であるかどうかを判断する
        if (actionHistories.getEmail().equals(UserSearch.getTraderUserEmail()) ||
                actionHistories.getEmail().equals(UserSearch.getTraderAgent1_UserEmail()) ||
                actionHistories.getEmail().equals(UserSearch.getTraderAgent2_UserEmail()) ||
                actionHistories.getEmail().equals(UserSearch.getTraderAgent3_UserEmail()) ||
                actionHistories.getEmail().equals(UserSearch.getTraderAgent4_UserEmail()) ||
                actionHistories.getEmail().equals(UserSearch.getTraderAgent5_UserEmail())) {
            actionHistories.setUserType(2);
        }
        // Emailが取得したデータから調停者かどうかを判断する
        if (actionHistories.getEmail().equals(UserSearch.getMediatorUserEmail())) {
            actionHistories.setUserType(3);
        }
        actionHistories.setHaveFile(false);
        actionHistories.setDeleteFlag(false);
        actionHistories.setLastModifiedBy(reconciliationuser.getLastModifiedBy());
        // 「アクロン履歴」新規登録
        int ActionHistoriesInsertStatus = ReconciliationUpdate.ActionHistoriesInsert(actionHistories);
        if (ActionHistoriesInsertStatus == 0) {
            return 0;
        }
        return 1;
    }
    
    /**
     * メール送信
     * 
     * @param reconciliationuser 前台伝出のデータ
     * @return メール送信は成功したか
     */
    @Override
    public Boolean sendMailRequest(ReconciliationUser reconciliationuser) {
        SendMailRequest sendMailRequest = new SendMailRequest();
        // casesテーブルのCaseTitleを検索し、メール送信に設定する
        Cases cases = new Cases();
        cases.setCid(reconciliationuser.getCaseId());
        String CaseTitle = ReconciliationUpdate.CaseTitleSearch(cases);
        sendMailRequest.setPlatformId("0001");
        sendMailRequest.setLanguageId("JP");
        sendMailRequest.setTempId("M029");
        sendMailRequest.setCaseId(reconciliationuser.getCaseId());
        ArrayList<String> recipientEmail = new ArrayList<String>();
        recipientEmail.add("jia.wenzhi@trans-cosmos.com.cn");
        sendMailRequest.setRecipientEmail(recipientEmail);
        ArrayList<String> parameter = new ArrayList<String>();
        parameter.add(reconciliationuser.getCaseId());
        parameter.add(CaseTitle);
        parameter.add("https://http://localhost:3000/");
        sendMailRequest.setParameter(parameter);
        sendMailRequest.setUserId("001");
        sendMailRequest.setControlType(1);
        boolean bool = utilService.SendMail(sendMailRequest);
        return bool;
    }

}
