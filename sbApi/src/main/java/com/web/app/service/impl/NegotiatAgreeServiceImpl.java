package com.web.app.service.impl;

import com.web.app.service.NegotiatAgreeService;
import com.web.app.service.UtilService;
import java.util.ArrayList;
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
import com.web.app.domain.constants.Constants;
import com.web.app.domain.constants.MailConstants;

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

        // コンセンサス状態
        Boolean FinllyStatus;
        // 和解案合意更新
        int updateCount = updNegotiatAgreeMapper.updateCount(updNegotiatAgree);
        // 和解案合意更新に成功
        if (updateCount == Constants.NUM_1) {
            // ユーザーの立場の判断
            int userStance = Constants.NUM_0;
            // 「アクロン履歴」新規登録時に必要な属性の設定
            ActionHistories actionHistories = new ActionHistories();
            actionHistories.setPlatformId(updNegotiatAgree.getPlatformId());
            actionHistories.setCaseId(updNegotiatAgree.getCaseId());
            actionHistories.setActionType("NegotiationAgreed");
            actionHistories.setCaseStage(Constants.NUM_3);
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
                actionHistories.setUserType(Constants.NUM_1);
                userStance = Constants.NUM_1;
            }
            // Emailが取得したデータから相守側であるかどうかを判断する
            if (updNegotiatAgree.getEmail().equals(UserSearch.getTraderUserEmail()) ||
                    updNegotiatAgree.getEmail().equals(UserSearch.getTraderAgent1_UserEmail()) ||
                    updNegotiatAgree.getEmail().equals(UserSearch.getTraderAgent2_UserEmail()) ||
                    updNegotiatAgree.getEmail().equals(UserSearch.getTraderAgent3_UserEmail()) ||
                    updNegotiatAgree.getEmail().equals(UserSearch.getTraderAgent4_UserEmail()) ||
                    updNegotiatAgree.getEmail().equals(UserSearch.getTraderAgent5_UserEmail())) {
                actionHistories.setUserType(Constants.NUM_2);
                userStance = Constants.NUM_2;
            }
            // Emailが取得したデータから調停者かどうかを判断する
            if (updNegotiatAgree.getEmail().equals(UserSearch.getMediatorUserEmail())) {
                actionHistories.setUserType(Constants.NUM_3);
                userStance = Constants.NUM_3;
            }
            actionHistories.setLastModifiedBy(updNegotiatAgree.getLastModifiedBy());
            // ログインユーザに立場があるかどうかを判断する
            if (userStance != Constants.NUM_0) {
                // 「アクロン履歴」新規登録
                Boolean ActionHistoriesInsertStatus = commonService.InsertActionHistories(actionHistories, null, false,
                        false);
                // 「アクロン履歴」新規登録に成功
                if (ActionHistoriesInsertStatus) {
                    SendMailRequest sendMailRequest = new SendMailRequest();
                    // casesテーブルのCaseTitleを検索し、メール送信に設定する
                    String CaseTitle = updNegotiatAgreeMapper.CaseTitleSearch(updNegotiatAgree.getCaseId());
                    sendMailRequest.setPlatformId(updNegotiatAgree.getPlatformId());
                    sendMailRequest.setLanguageId(Constants.JP);
                    sendMailRequest.setTempId(MailConstants.MailId_M029);
                    sendMailRequest.setCaseId(updNegotiatAgree.getCaseId());
                    ArrayList<String> recipientEmail = new ArrayList<String>();
                    // 受信者のメールボックスを立場に合わせて保存する
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
                    }
                    sendMailRequest.setRecipientEmail(recipientEmail);
                    ArrayList<String> parameter = new ArrayList<String>();
                    parameter.add(updNegotiatAgree.getCaseId());
                    parameter.add(CaseTitle);
                    parameter.add(Constants.SENDMAIL_LINK);
                    sendMailRequest.setParameter(parameter);
                    sendMailRequest.setUserId(updNegotiatAgree.getUid());
                    sendMailRequest.setControlType(Constants.NUM_1);
                    // メール送信
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