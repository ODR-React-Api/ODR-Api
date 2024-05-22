package com.web.app.service.impl;

import com.web.app.service.NegotiatAgreeService;
import com.web.app.service.UtilService;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.Entity.ActionHistories;
import com.web.app.domain.Entity.CaseRelations;
import com.web.app.domain.Entity.Cases;
import com.web.app.domain.negotiatAgree.UpdNegotiatAgree;
import com.web.app.domain.util.SendMailRequest;
import com.web.app.mapper.UpdNegotiatAgreeMapper;
import com.web.app.service.CommonService;

/**
 * 和解案合意更新API
 * 
 * @author DUC 賈文志
 * @since 2024/05/13
 * @version 1.0
 */
@Service
public class NegotiatAgreeServiceImpl implements NegotiatAgreeService {

    @Autowired
    private UpdNegotiatAgreeMapper updNegotiatAgreeMapper;
    @Autowired
    private CommonService commonService;
    @Autowired
    private UtilService utilService;

    /**
     * 和解案合意更新
     * 
     * @param updNegotiatAgree 前台伝出のデータ
     * @return 和解案合意更新状態
     */
    @Transactional
    @Override
    public Boolean updNegotiatAgree(UpdNegotiatAgree updNegotiatAgree) {
        Boolean FinllyStatus = true;
        // 和解案合意更新
        int updateCount = updNegotiatAgreeMapper.updateCount(updNegotiatAgree);

        if (updateCount == 1) {
            // ユーザーの立場の判断
            int userStance = 0;
            // 「アクロン履歴」新規登録時に必要な属性の設定
            ActionHistories actionHistories = new ActionHistories();
            actionHistories.setPlatformId(updNegotiatAgree.getPlatformId());
            actionHistories.setCaseId(updNegotiatAgree.getCaseId());
            actionHistories.setActionType("NegotiationAgreed");
            actionHistories.setCaseStage(3);
            actionHistories.setUserId(updNegotiatAgree.getUid());
            // CaseIdに基づいてcase_relationsからデータを取得する
            CaseRelations UserSearch = updNegotiatAgreeMapper.UserSearch(updNegotiatAgree);
            // Emailが取得したデータから申請者かどうかを判断する
            // アイデンティティに基づいた「アクロン履歴」新規ログインにおけるUserTypeプロパティの設定
            if (updNegotiatAgree.getEmail().equals(UserSearch.getPetitionUserInfo_Email()) ||
                    updNegotiatAgree.getEmail().equals(UserSearch.getAgent1_Email()) ||
                    updNegotiatAgree.getEmail().equals(UserSearch.getAgent2_Email()) ||
                    updNegotiatAgree.getEmail().equals(UserSearch.getAgent3_Email()) ||
                    updNegotiatAgree.getEmail().equals(UserSearch.getAgent4_Email()) ||
                    updNegotiatAgree.getEmail().equals(UserSearch.getAgent5_Email())) {
                actionHistories.setUserType(1);
                userStance = 1;

            }
            // Emailが取得したデータから相守側であるかどうかを判断する
            if (updNegotiatAgree.getEmail().equals(UserSearch.getTraderUserEmail()) ||
                    updNegotiatAgree.getEmail().equals(UserSearch.getTraderAgent1_UserEmail()) ||
                    updNegotiatAgree.getEmail().equals(UserSearch.getTraderAgent2_UserEmail()) ||
                    updNegotiatAgree.getEmail().equals(UserSearch.getTraderAgent3_UserEmail()) ||
                    updNegotiatAgree.getEmail().equals(UserSearch.getTraderAgent4_UserEmail()) ||
                    updNegotiatAgree.getEmail().equals(UserSearch.getTraderAgent5_UserEmail())) {
                actionHistories.setUserType(2);
                userStance = 2;
            }
            // Emailが取得したデータから調停者かどうかを判断する
            if (updNegotiatAgree.getEmail().equals(UserSearch.getMediatorUserEmail())) {
                actionHistories.setUserType(3);
                userStance = 3;
            }
            actionHistories.setMessageGroupId(null);
            actionHistories.setMessageId(null);
            actionHistories.setOther01(null);
            actionHistories.setOther02(null);
            actionHistories.setOther03(null);
            actionHistories.setOther04(null);
            actionHistories.setOther05(null);
            actionHistories.setLastModifiedBy(updNegotiatAgree.getLastModifiedBy());
            // 「アクロン履歴」新規登録
            if (updateCount != 0) {
                Boolean ActionHistoriesInsertStatus = commonService.InsertActionHistories(actionHistories, null, false,
                        false);
                if (ActionHistoriesInsertStatus) {
                    SendMailRequest sendMailRequest = new SendMailRequest();
                    // casesテーブルのCaseTitleを検索し、メール送信に設定する
                    Cases cases = new Cases();
                    cases.setCid(updNegotiatAgree.getCaseId());
                    String CaseTitle = updNegotiatAgreeMapper.CaseTitleSearch(updNegotiatAgree.getCaseId());

                    sendMailRequest.setPlatformId("0001");
                    sendMailRequest.setLanguageId("JP");
                    sendMailRequest.setTempId("M029");
                    sendMailRequest.setCaseId(updNegotiatAgree.getCaseId());
                    ArrayList<String> recipientEmail = new ArrayList<String>();
                    if (userStance == 1) {
                        if (UserSearch.getTraderUserEmail() != null) {
                            recipientEmail.add(UserSearch.getTraderUserEmail());
                        }
                        if (UserSearch.getTraderAgent1_UserEmail() != null) {
                            recipientEmail.add(UserSearch.getTraderAgent1_UserEmail());
                        }
                        if (UserSearch.getTraderAgent2_UserEmail() != null) {
                            recipientEmail.add(UserSearch.getTraderAgent2_UserEmail());
                        }
                        if (UserSearch.getTraderAgent3_UserEmail() != null) {
                            recipientEmail.add(UserSearch.getTraderAgent3_UserEmail());
                        }
                        if (UserSearch.getTraderAgent4_UserEmail() != null) {
                            recipientEmail.add(UserSearch.getTraderAgent4_UserEmail());
                        }
                        if (UserSearch.getTraderAgent5_UserEmail() != null) {
                            recipientEmail.add(UserSearch.getTraderAgent5_UserEmail());
                        }
                        if (UserSearch.getMediatorUserEmail() != null) {
                            recipientEmail.add(UserSearch.getMediatorUserEmail());
                        }
                        recipientEmail.add("jia.wenzhi@trans-cosmos.com.cn");
                    }
                    if (userStance == 2) {
                        if (UserSearch.getPetitionUserInfo_Email() != null) {
                            recipientEmail.add(UserSearch.getPetitionUserInfo_Email());
                        }
                        if (UserSearch.getAgent1_Email() != null) {
                            recipientEmail.add(UserSearch.getAgent1_Email());
                        }
                        if (UserSearch.getAgent2_Email() != null) {
                            recipientEmail.add(UserSearch.getAgent2_Email());
                        }
                        if (UserSearch.getAgent3_Email() != null) {
                            recipientEmail.add(UserSearch.getAgent3_Email());
                        }
                        if (UserSearch.getAgent4_Email() != null) {
                            recipientEmail.add(UserSearch.getAgent4_Email());
                        }
                        if (UserSearch.getAgent5_Email() != null) {
                            recipientEmail.add(UserSearch.getAgent5_Email());
                        }
                        if (UserSearch.getMediatorUserEmail() != null) {
                            recipientEmail.add(UserSearch.getMediatorUserEmail());
                        }
                        recipientEmail.add("jia.wenzhi@trans-cosmos.com.cn");
                    }
                    if (userStance == 3) {
                        if (UserSearch.getTraderUserEmail() != null) {
                            recipientEmail.add(UserSearch.getTraderUserEmail());
                        }
                        if (UserSearch.getTraderAgent1_UserEmail() != null) {
                            recipientEmail.add(UserSearch.getTraderAgent1_UserEmail());
                        }
                        if (UserSearch.getTraderAgent2_UserEmail() != null) {
                            recipientEmail.add(UserSearch.getTraderAgent2_UserEmail());
                        }
                        if (UserSearch.getTraderAgent3_UserEmail() != null) {
                            recipientEmail.add(UserSearch.getTraderAgent3_UserEmail());
                        }
                        if (UserSearch.getTraderAgent4_UserEmail() != null) {
                            recipientEmail.add(UserSearch.getTraderAgent4_UserEmail());
                        }
                        if (UserSearch.getTraderAgent5_UserEmail() != null) {
                            recipientEmail.add(UserSearch.getTraderAgent5_UserEmail());
                        }
                        if (UserSearch.getPetitionUserInfo_Email() != null) {
                            recipientEmail.add(UserSearch.getPetitionUserInfo_Email());
                        }
                        if (UserSearch.getAgent1_Email() != null) {
                            recipientEmail.add(UserSearch.getAgent1_Email());
                        }
                        if (UserSearch.getAgent2_Email() != null) {
                            recipientEmail.add(UserSearch.getAgent2_Email());
                        }
                        if (UserSearch.getAgent3_Email() != null) {
                            recipientEmail.add(UserSearch.getAgent3_Email());
                        }
                        if (UserSearch.getAgent4_Email() != null) {
                            recipientEmail.add(UserSearch.getAgent4_Email());
                        }
                        if (UserSearch.getAgent5_Email() != null) {
                            recipientEmail.add(UserSearch.getAgent5_Email());
                        }
                        recipientEmail.add("jia.wenzhi@trans-cosmos.com.cn");
                    }
                    sendMailRequest.setRecipientEmail(recipientEmail);
                    ArrayList<String> parameter = new ArrayList<String>();
                    parameter.add(updNegotiatAgree.getCaseId());
                    parameter.add(CaseTitle);
                    parameter.add("https://http://localhost:3000/");
                    sendMailRequest.setParameter(parameter);
                    sendMailRequest.setUserId("001");
                    sendMailRequest.setControlType(1);
                    Boolean bool = utilService.SendMail(sendMailRequest);
                    FinllyStatus = bool;
                } else {
                    FinllyStatus = false;
                }
            } else {
                FinllyStatus = false;
            }
        } else {
            FinllyStatus = false;
        }
        return FinllyStatus;
    }
}