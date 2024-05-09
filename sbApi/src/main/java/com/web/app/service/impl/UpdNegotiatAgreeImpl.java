package com.web.app.service.impl;

import com.web.app.service.UpdNegotiatAgreeService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.web.app.domain.ReconciliationUser;
import com.web.app.mapper.UpdNegotiatAgreeMapper;
import com.web.app.domain.ActionHistories;
import com.web.app.domain.Entity.Cases;

@Service
public class UpdNegotiatAgreeImpl implements UpdNegotiatAgreeService {

    @Autowired
    private UpdNegotiatAgreeMapper ReconciliationUpdate;
//和解案合意更新
    @Transactional
    @Override
    public int reconciliationUpdate(ReconciliationUser reconciliationuser) {
        int ReconciliationUpdateStatus = ReconciliationUpdate.reconciliationUpdate(reconciliationuser);
        if (ReconciliationUpdateStatus == 0) {
            return 0;
        }
        return 1;
    }
    //「アクロン履歴」新規登録
    @Transactional
    @Override
    public int ActionHistoriesInsert(ActionHistories ActionHistories) {
        //「アクロン履歴」新規登録時に必要な属性の設定
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        ActionHistories.setId(id);
        ActionHistories.setActionType("NegotiationAgreed");
        ActionHistories.setCaseStage(3);
        ActionHistories.setHaveFile(false);
        ActionHistories.setDeleteFlag(false);

        String userType = new String();
        // CaseIdに基づいてcase_relationsからデータを取得する
        ActionHistories UserSearch = ReconciliationUpdate.UserSearch(ActionHistories);
        // Emailが取得したデータから申請者かどうかを判断する
        if (ActionHistories.getEmail().equals(UserSearch.getPetitionUserInfo_Email()) ||
                ActionHistories.getEmail().equals(UserSearch.getAgent1_Email()) ||
                ActionHistories.getEmail().equals(UserSearch.getAgent2_Email()) ||
                ActionHistories.getEmail().equals(UserSearch.getAgent3_Email()) ||
                ActionHistories.getEmail().equals(UserSearch.getAgent4_Email()) ||
                ActionHistories.getEmail().equals(UserSearch.getAgent5_Email())) {
            userType = "1";
        }
        //Emailが取得したデータから相守側であるかどうかを判断する
        if (ActionHistories.getEmail().equals(UserSearch.getTraderUserEmail()) ||
                ActionHistories.getEmail().equals(UserSearch.getTraderAgent1_UserEmail()) ||
                ActionHistories.getEmail().equals(UserSearch.getTraderAgent2_UserEmail()) ||
                ActionHistories.getEmail().equals(UserSearch.getTraderAgent3_UserEmail()) ||
                ActionHistories.getEmail().equals(UserSearch.getTraderAgent4_UserEmail()) ||
                ActionHistories.getEmail().equals(UserSearch.getTraderAgent5_UserEmail())) {
            userType = "2";
        }
        // Emailが取得したデータから調停者かどうかを判断する
        if (ActionHistories.getEmail().equals(UserSearch.getMediatorUserEmail())) {
            userType = "3";
        }
        //アイデンティティに基づいた「アクロン履歴」新規ログインにおけるUserTypeプロパティの設定
        if (userType == "1") {
            ActionHistories.setUserType(1);
        }
        if (userType == "2") {
            ActionHistories.setUserType(2);
        }
        if (userType == "3") {
            ActionHistories.setUserType(3);
        }
        //「アクロン履歴」新規登録
        int ActionHistoriesInsertStatus = ReconciliationUpdate.ActionHistoriesInsert(ActionHistories);
        if (ActionHistoriesInsertStatus == 0) {
            return 0;
        }
        return 1;
    }
    //casesテーブルのCaseTitleを検索し、メール送信に設定する
    @Override
    public String CaseTitleSearch(Cases cases) {
        String CaseTitleSearch = ReconciliationUpdate.CaseTitleSearch(cases);
        return CaseTitleSearch;
    }
}
