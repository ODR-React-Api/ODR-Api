package com.web.app.service.impl;

import com.web.app.service.ReconciliationService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.web.app.domain.ReconciliationUser;
import com.web.app.mapper.ReconciliationMapper;
import com.web.app.domain.ActionHistories;

@Service
public class ReconciliationServiceImpl implements ReconciliationService {

    @Autowired
    private ReconciliationMapper ReconciliationUpdate;

    @Override
    public ReconciliationUser reconciliationUserSearch(ReconciliationUser reconciliationuser) {
        return ReconciliationUpdate.reconciliationUserSearch(reconciliationuser);
    }

    @Transactional
    @Override
    public int reconciliationUpdate(ReconciliationUser reconciliationuser) {
        int ReconciliationUpdateStatus = ReconciliationUpdate.reconciliationUpdate(reconciliationuser);
        if (ReconciliationUpdateStatus == 0) {
            return 0;
        }
        return 1;
    }

    @Transactional
    @Override
    public int ActionHistoriesInsert(ActionHistories ActionHistories) {
        // id赋予
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        ActionHistories.setId(id);

        ActionHistories.setActionType("NegotiationAgreed");
        ActionHistories.setCaseStage(3);
        String userType = new String();
        // 判断身份
        ActionHistories UserSearch = ReconciliationUpdate.UserSearch(ActionHistories);
        // 申立人
        if (ActionHistories.getEmail().equals(UserSearch.getPetitionUserInfo_Email()) ||
                ActionHistories.getEmail().equals(UserSearch.getAgent1_Email()) ||
                ActionHistories.getEmail().equals(UserSearch.getAgent2_Email()) ||
                ActionHistories.getEmail().equals(UserSearch.getAgent3_Email()) ||
                ActionHistories.getEmail().equals(UserSearch.getAgent4_Email()) ||
                ActionHistories.getEmail().equals(UserSearch.getAgent5_Email())) {
            userType = "1";
        }
        // 相守人
        if (ActionHistories.getEmail().equals(UserSearch.getTraderUserEmail()) ||
                ActionHistories.getEmail().equals(UserSearch.getTraderAgent1_UserEmail()) ||
                ActionHistories.getEmail().equals(UserSearch.getTraderAgent2_UserEmail()) ||
                ActionHistories.getEmail().equals(UserSearch.getTraderAgent3_UserEmail()) ||
                ActionHistories.getEmail().equals(UserSearch.getTraderAgent4_UserEmail()) ||
                ActionHistories.getEmail().equals(UserSearch.getTraderAgent5_UserEmail())) {
            userType = "2";
        }
        // 调停人
        if (ActionHistories.getEmail().equals(UserSearch.getMediatorUserEmail())) {
            userType = "3";
        }

        if (userType == "1") {
            ActionHistories.setUserType(1);
        }
        if (userType == "2") {
            ActionHistories.setUserType(2);
        }
        if (userType == "3") {
            ActionHistories.setUserType(3);
        }
        ActionHistories.setHaveFile(false);
        ActionHistories.setDeleteFlag(false);

        int ActionHistoriesInsertStatus = ReconciliationUpdate.ActionHistoriesInsert(ActionHistories);
        if (ActionHistoriesInsertStatus == 0) {
            return 0;
        }
        return 1;
    }
}
