package com.web.app.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.Entity.ActionHistories;
import com.web.app.domain.Entity.CaseNegotiations;
import com.web.app.domain.Entity.CaseRelations;
import com.web.app.domain.Entity.Cases;
import com.web.app.domain.NegotiatAgree.CaseEstablish;
import com.web.app.domain.NegotiatAgree.Negotiation;
import com.web.app.domain.NegotiatAgree.UpdNegotiatAgree;
import com.web.app.domain.NegotiatAgree.UpdNegotiatCon;
import com.web.app.domain.constants.MailConstants;
import com.web.app.domain.constants.Num;
import com.web.app.domain.util.SendMailRequest;
import com.web.app.mapper.GetNegotiatConInfoMapper;
import com.web.app.mapper.UpdCaseEstablishMapper;
import com.web.app.mapper.UpdNegotiatAgreeMapper;
import com.web.app.mapper.UpdNegotiatConMapper;
import com.web.app.mapper.UpdNegotiatDenyMapper;
import com.web.app.service.CommonService;
import com.web.app.service.NegotiatAgreeService;
import com.web.app.service.UtilService;
import com.web.app.domain.constants.Constants;

/**
 * 
 * 和解案合意画面
 * 
 * 本画面は、和解案のプレビュー表示と
 * 確認書表示画面です。作成した和解案の合意、
 * 拒否および確認を行う。
 * 
 * @author DUC 徐義然 李志文 賈文志 王 エンエン 馮淑慧
 * @since 2024/05/06
 * @version 1.0
 */
@Service
public class NegotiatAgreeServiceImpl implements NegotiatAgreeService {

    @Autowired
    private GetNegotiatConInfoMapper getNegotiatConInfoMapper;
    @Autowired
    private UpdNegotiatAgreeMapper updNegotiatAgreeMapper;
    @Autowired
    private CommonService commonService;
    @Autowired
    private UtilService utilService;
    //和解案拒否更新マッパーオブジェクト
    @Autowired
    private UpdNegotiatDenyMapper updNegotiatDenyMapper;
    // マッパーオブジェクト
    @Autowired
    private UpdNegotiatConMapper updNegotiatConMapper;

    @Autowired
    private UpdCaseEstablishMapper updCaseEstablishMapper;

    /**
     * 和解案確認データ取得
     *
     * @param NegotiatAgree セッション値
     * @return 置換後のテキスト
     */
    @Override
    public CaseNegotiations SelCaseNegotiations(String CaseID) {
        CaseNegotiations caseNegotiations = getNegotiatConInfoMapper.selCaseNegotiations(CaseID);
        String year = String.valueOf(LocalDate.now().getYear());
        String month = String.valueOf(LocalDate.now().getMonthValue());
        String day = String.valueOf(LocalDate.now().getDayOfMonth());
        if (caseNegotiations != null) {
            if (Num.NUM2.equals(caseNegotiations.getStatus()) ||
                    Num.NUM3.equals(caseNegotiations.getStatus()) ||
                    Num.NUM9.equals(caseNegotiations.getStatus()) ||
                    Num.NUM12.equals(caseNegotiations.getStatus()) ||
                    Num.NUM15.equals(caseNegotiations.getStatus())) {
                String htmlContext = caseNegotiations.getHtmlContext().replace("#YYYY#", year).replace("#MM#", month)
                        .replace("#DD#", day);
                String htmlContext2 = caseNegotiations.getHtmlContext2().replace("#YYYY#", year).replace("#MM#", month)
                        .replace("#DD#", day);
                caseNegotiations.setHtmlContext(htmlContext);
                caseNegotiations.setHtmlContext2(htmlContext2);
            } else {
                return null;
            }
        } else {
            return null;
        }
        return caseNegotiations;
    }

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

    /**
     * 
     * API_ID:和解案拒否更新
     * 
     * データを処理してDBを更新する
     * 
     * @param negotiation 更新に使用するログィンユザと和解案idが含まれています
     * @return DB更新に成功したレコード
     */
    @Override
    @Transactional
    public int updNegotiatDeny(Negotiation negotiation){
        //和解案のステータスの取得
        Integer status = updNegotiatDenyMapper.getNegotiationStatus(negotiation.getNegotiationId());
        //更新前Status=２，１２の場合、７で更新する
        //更新前Status=９、１５の場合、１０で更新する
        if (status == null) {
            negotiation.setStatus(null);  
        }else if(status == 2 || status == 12){
            negotiation.setStatus(7);
        }else if (status == 9 || status == 15) {
            negotiation.setStatus(10);
        }else{
            negotiation.setStatus(status);
        }
        //取得システム時間を文字列に変換してDBに挿入
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        negotiation.setLastModifiedDate(sdf.format(System.currentTimeMillis()));
        return updNegotiatDenyMapper.setNegotiationStatus(negotiation);
    }

    /**
     * API_和解案確認更新
     * データを処理してDBを更新する
     * 
     * 
     * @param negotiation 更新に使用するログィンユザと和解案idと案件idが含まれています
     * @return DB更新に成功したレコード
     */
    @Override
    @Transactional
    public int updateNegotiatData(UpdNegotiatCon updNegotiatCon) {

        // 和解案のステータスの取得
        Integer status = updNegotiatConMapper.getNegotiationStatus(updNegotiatCon.getNegotiationId());
        // 判断身份
        UpdNegotiatCon relationsEmail = updNegotiatConMapper.getRelationsEmail(updNegotiatCon);
        UpdNegotiatCon userEmail = updNegotiatConMapper.getUsersEmail(updNegotiatCon);

        String userstatus = null;
        // 申立人
        if (userEmail.getEmail().equals(relationsEmail.getPetitionUserInfoEmail()) ||
                userEmail.getEmail().equals(relationsEmail.getAgent1Email()) ||
                userEmail.getEmail().equals(relationsEmail.getAgent2Email()) ||
                userEmail.getEmail().equals(relationsEmail.getAgent3Email()) ||
                userEmail.getEmail().equals(relationsEmail.getAgent4Email()) ||
                userEmail.getEmail().equals(relationsEmail.getAgent5Email())) {
            userstatus = "1";
        }

        // 相手方
        if (userEmail.getEmail().equals(relationsEmail.getTraderUserEmail()) ||
                userEmail.getEmail().equals(relationsEmail.getTraderAgent1UserEmail()) ||
                userEmail.getEmail().equals(relationsEmail.getTraderAgent2UserEmail()) ||
                userEmail.getEmail().equals(relationsEmail.getTraderAgent3UserEmail()) ||
                userEmail.getEmail().equals(relationsEmail.getTraderAgent4UserEmail()) ||
                userEmail.getEmail().equals(relationsEmail.getTraderAgent5UserEmail())) {
            userstatus = "2";
        }

        // 更新前Statusが4 or 5の場合、6で更新する
        // 更新前Statusが3の場合、ログインユーザが申立人なら、4で更新する
        // ログインユーザが相手方なら、5で更新する
        try {
            if (status == 4 || status == 5) {
                updNegotiatCon.setStatus(6);
            } else if (status == 3) {
                // ログインユーザが申立人
                if (userstatus != null && userstatus.equals("1")) {
                    updNegotiatCon.setStatus(4);
                    // ログインユーザが相手方
                } else if (userstatus != null && userstatus.equals("2")) {
                    updNegotiatCon.setStatus(5);
                }
            } else {
                updNegotiatCon.setStatus(status);
            }
        } catch (NullPointerException e) {
            updNegotiatCon.setStatus(null);
            System.out.println(updNegotiatCon.getStatus());
        }
        // 取得システム時間を文字列に変換してDBに挿入
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        updNegotiatCon.setLastModifiedDate(sdf.format(System.currentTimeMillis()));
        return updNegotiatConMapper.setNegotiationStatus(updNegotiatCon);
    }

    /**
     * API_案件成立更新
     * 和解案テーブルから取得した和解案Statusが6の場合、API_案件成立更新をコールし、案件のステータスを「成立」に更新する
     *
     * @param caseEstablish 更新に必要なセッション情報の和解案id、セッション情報の案件Caseとログインユーザ
     * @return num 案件成立更新成功件数
     */
    @Override
    public int updCaseEstablish(CaseEstablish caseEstablish) {

        // 「和解案確認更新API」をコール後、和解案確認データ（StatusとPayAmount）を取得する
        CaseNegotiations caseNegotiations = updCaseEstablishMapper
                .selectCaseNegotiations(caseEstablish.getCaseNegotiationsId());
        if (caseNegotiations != null && caseNegotiations.getStatus() == 6) {

            // 案件更新用数据初期化
            Cases cases = new Cases();

            // 金銭の支払い有無の設定
            // 取得した和解案のPayAmountが０より大きい場合、１で更新する。上記以外場合、０で更新する
            if (caseNegotiations.getPayAmount() > 0) {
                cases.setPayFlag(1);
            } else {
                cases.setPayFlag(0);
            }
            // ID
            cases.setCid(caseEstablish.getCasesId());
            // 上次修改者
            cases.setLastModifiedBy(caseEstablish.getLoginUser());

            // 案件成立更新
            return updCaseEstablishMapper.updateCases(cases);
        }

        return 0;
    }

}
