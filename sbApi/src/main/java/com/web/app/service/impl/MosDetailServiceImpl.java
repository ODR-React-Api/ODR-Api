package com.web.app.service.impl;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.app.domain.Entity.CaseMediations;
import com.web.app.domain.Entity.CaseNegotiations;
import com.web.app.domain.Entity.Files;
import com.web.app.domain.Entity.MasterPlatforms;
import com.web.app.domain.Entity.OdrUsers;
import com.web.app.domain.MosDetail.CaseInfo;
import com.web.app.domain.MosDetail.CasesData;
import com.web.app.domain.MosDetail.UpdShowTuritorParameter;
import com.web.app.domain.constants.Constants;
import com.web.app.domain.MosDetail.CaseMediationsData;
import com.web.app.domain.MosDetail.CaseNegotiationsData;
import com.web.app.mapper.GetCaseInfoMapper;
import com.web.app.mapper.GetCaseMediationsDataMapper;
import com.web.app.mapper.GetCaseNegotiationsDataMapper;
import com.web.app.mapper.UpdShowTuritorMapper;
import com.web.app.service.MosDetailService;

/**
 * S04_申立て概要画面
 * Service層実装クラス
 * MosDetailServiceImpl
 * 
 * @author DUC 張明慧
 * @since 2024/04/22
 * @version 1.0
 */
@Service
public class MosDetailServiceImpl implements MosDetailService {
    @Autowired
    private GetCaseInfoMapper getCaseInfoMapper;

    @Autowired
    private UpdShowTuritorMapper updShowTuritorMapper;

    @Autowired
    private GetCaseNegotiationsDataMapper getCaseNegotiationsDataMapper;

    @Autowired
    private GetCaseMediationsDataMapper getCaseMediationsDataMapper;

    /**
     * API_案件状態取得
     * 申立て一覧画面より渡されたCaseIdを引数として、DBから該当する案件のステータスを取得する。
     * 
     * @param caseId     渡し項目.CaseId
     * @param platformId セッション.PlatformId
     * @param userId     セッション.ユーザID
     * @return caseInfo API「案件状態取得」を呼び出すData
     */
    @Override
    public CaseInfo GetCaseInfo(String caseId, String platformId, String userId) {
        CaseInfo caseInfo = new CaseInfo();
        // 該当案件のステータスを取得
        CasesData casesData = getCaseInfoMapper.getCasesData(caseId);

        // 案件内容の設定
        if (casesData != null) {
            caseInfo = setCaseInfoCaseStatus(casesData);
        } else {
            // （網羅外ステータス）を設定する
            caseInfo.setCaseStatus(Constants.S9A9B9C9);
        }

        // 利用モジュール状況取得
        MasterPlatforms masterPlatforms = getCaseInfoMapper.getPhases(platformId);
        // 利用モジュール状況の設定
        if (masterPlatforms != null) {
            int moudleFlg = setCaseInfoMoudleFlg(masterPlatforms);
            caseInfo.setMoudleFlg(moudleFlg);
        }

        // チュートリアル表示制御取得
        OdrUsers odrUsers = getCaseInfoMapper.getShowTuritor(userId, platformId);
        if (odrUsers != null) {
            // チュートリアル表示（申立）
            int showTuritor1 = odrUsers.getShowTuritor1();
            // チュートリアル表示（回答）
            int showTuritor2 = odrUsers.getShowTuritor2();
            // チュートリアル表示（調停）
            int showTuritor3 = odrUsers.getShowTuritor3();
            caseInfo.setShowTuritor1(showTuritor1);
            caseInfo.setShowTuritor2(showTuritor2);
            caseInfo.setShowTuritor3(showTuritor3);
        }

        return caseInfo;
    }

    /**
     * API_チュートリアル表示制御変更
     * 
     * @param updShowTuritorParameter API_チュートリアル表示制御変更の引数
     * @return int チュートリアル表示制御変更の状況
     */
    @Override
    public int UpdShowTuritor(UpdShowTuritorParameter updShowTuritorParameter) {
        return updShowTuritorMapper.updShowTuritor(updShowTuritorParameter);
    }

    /**
     * API_和解内容取得
     * 渡し項目.CaseIdを引数に、DBよりケースに該当する和解の内容を取得して、画面へ返す。
     * 
     * @param caseId 渡し項目.CaseId
     * @return negotiationsData API「和解内容取得」を呼び出すData
     */
    @Override
    public CaseNegotiationsData GetCaseNegotiationsData(String caseId) {
        CaseNegotiationsData caseNegotiationsData = new CaseNegotiationsData();
        // 和解内容の取得
        CaseNegotiations caseNegotiations = getCaseNegotiationsDataMapper.getCaseNegotiations(caseId);
        // 和解内容の設定
        if (caseNegotiations != null) {
            caseNegotiationsData = setCaseNegotiationsData(caseNegotiations);

            if (caseNegotiations.getCaseId() != null) {
                // 和解案 添付資料の取得
                List<Files> files = getCaseNegotiationsDataMapper.getFiles(caseNegotiations.getCaseId());
                // 添付資料リストの設定
                caseNegotiationsData.setFile(files);
            }
        }

        return caseNegotiationsData;
    }

    /**
     * API_調停内容取得
     * 渡し項目.CaseIdを引数に、DBよりケースに該当する調停の内容を取得して、画面へ返す。
     * 
     * @param caseId 渡し項目.CaseId
     * @return caseMediationsData API「調停内容取得」を呼び出すData
     */
    @Override
    public CaseMediationsData GetCaseMediationsData(String caseId) {
        CaseMediationsData caseMediationsData = new CaseMediationsData();
        // 調停内容の取得
        CaseMediations caseMediations = getCaseMediationsDataMapper.getCaseMediations(caseId);
        // 調停内容の設定
        if (caseMediations != null) {
            caseMediationsData = setCaseMediationsData(caseMediations);

            if (caseMediations.getCaseId() != null) {
                // 調停案 添付資料の取得
                List<Files> files = getCaseMediationsDataMapper.getFiles(caseMediations.getCaseId());
                // 添付資料リストの設定
                caseMediationsData.setFile(files);
            }
        }

        return caseMediationsData;
    }

    /**
     * 案件内容の設定 下記項目を返す
     * CaseTitle タイトル名
     * 案件ステータス
     * ・Stage
     * ・CaseStatus
     * ・DateRequestStatus
     * ・MessageStatus
     * 期日用項目
     * ・ReplyEndDate 回答期限日
     * ・CounterclaimEndDate 反訴の回答期限日
     * ・CancelDate 手続き中止日
     * ・ResolutionDate 解決日時
     * ・MediationEndDate 調停期限日
     * 
     * @param casesData 案件のステータスを取得
     * @return caseInfo API「案件状態取得」を呼び出すData---案件内容の設定
     */
    public CaseInfo setCaseInfoCaseStatus(CasesData casesData) {
        CaseInfo caseInfo = new CaseInfo();
        // タイトル名
        String caseTitle = casesData.getCaseTitle();
        // 案件ステージ
        int caseStage = casesData.getCaseStage();
        // 案件ステータス
        String status = casesData.getCaseStatus();
        // 和解案.ステータス
        String negotiationStatus = casesData.getNegotiationStatus();
        // 案件別個人情報リレーション.調停人
        String mediatorUserEmail = casesData.getMediatorUserEmail();
        // 調停案.ステータス
        String mediationStatus = casesData.getMediationStatus();
        // 交渉期限日変更ステータス
        int negotiationEndDateChangeStatus = casesData.getNegotiationEndDateChangeStatus();
        // 交渉期限日変更回数
        int negotiationEndDateChangeCount = casesData.getNegotiationEndDateChangeCount();
        // 調停期限変更回数
        int mediationEndDateChangeCount = casesData.getMediationEndDateChangeCount();
        // 個別やりとりステータス（申立人↔調停人）
        int groupMessageFlag1 = casesData.getGroupMessageFlag1();
        // 個別やりとりステータス（相手方↔調停人）
        int groupMessageFlag2 = casesData.getGroupMessageFlag2();
        String caseStatus = status;
        int dateRequestStatus = 99;
        int messageStatus = 99;
        int stage = caseStage;
        // 案件ステータスの判定
        if (caseStage == 0) {
            // CaseStatus
            if (Constants.WAIT_FOR_JOIN.equals(status)) {
                caseStatus = Constants.S01;
            } else if (Constants.WAIT_FOR_REPLY.equals(status)) {
                caseStatus = Constants.S02;
            } else {
                caseStatus = Constants.S9A9B9C9;
            }
        } else if (caseStage == 3) {
            // CaseStatus
            if (negotiationStatus == "0") {
                caseStatus = Constants.S3B0;
            } else if (negotiationStatus == null) {
                caseStatus = Constants.S3B99;
            } else if (negotiationStatus == "1") {
                caseStatus = Constants.S3B1;
            } else if (negotiationStatus == "2") {
                caseStatus = Constants.S3B2;
            } else if (negotiationStatus == "3") {
                caseStatus = Constants.S3B3;
            } else if (negotiationStatus == "4") {
                caseStatus = Constants.S3B4;
            } else if (negotiationStatus == "5") {
                caseStatus = Constants.S3B5;
            } else if (negotiationStatus == "7") {
                caseStatus = Constants.S3B7;
            } else if (negotiationStatus == "8") {
                caseStatus = Constants.S3B8;
            } else if (negotiationStatus == "9") {
                caseStatus = Constants.S3B9;
            } else if (negotiationStatus == "10") {
                caseStatus = Constants.S3B10;
            } else if (negotiationStatus == "11") {
                caseStatus = Constants.S3B11;
            } else if (negotiationStatus == "12") {
                caseStatus = Constants.S3B12;
            } else if (negotiationStatus == "13") {
                caseStatus = Constants.S3B13;
            } else if (negotiationStatus == "14") {
                caseStatus = Constants.S3B14;
            } else if (negotiationStatus == "15") {
                caseStatus = Constants.S3B15;
            } else {
                caseStatus = Constants.S9A9B9C9;
            }
            // DateRequestStatus
            if (negotiationEndDateChangeStatus == 0 && negotiationEndDateChangeCount == 0) {
                dateRequestStatus = Constants.S3A0;
            } else if (negotiationEndDateChangeStatus == 1) {
                dateRequestStatus = Constants.S3A1;
            } else if (negotiationEndDateChangeStatus == 2) {
                dateRequestStatus = Constants.S3A2;
            } else if (negotiationEndDateChangeStatus == 0 && negotiationEndDateChangeCount == 1) {
                dateRequestStatus = Constants.S3A3;
            }
        } else if (caseStage == 6) {
            // CaseStatus
            if (mediatorUserEmail == null) {
                caseStatus = Constants.S61;
            } else {
                caseStatus = Constants.S62;
            }
        } else if (caseStage == 7) {
            // CaseStatus
            if (mediationStatus == "0") {
                caseStatus = Constants.S7C0;
            } else if (mediationStatus == null) {
                caseStatus = Constants.S7C99;
            } else if (mediationStatus == "1") {
                caseStatus = Constants.S7C1;
            } else if (mediationStatus == "2") {
                caseStatus = Constants.S7C2;
            } else if (mediationStatus == "3") {
                caseStatus = Constants.S7C3;
            } else if (mediationStatus == "4") {
                caseStatus = Constants.S7C4;
            } else if (mediationStatus == "7") {
                caseStatus = Constants.S7C5;
            } else if (mediationStatus == "8") {
                caseStatus = Constants.S7C6;
            } else {
                caseStatus = Constants.S9A9B9C9;
            }
            // DateRequestStatus
            if (mediationEndDateChangeCount == 0) {
                dateRequestStatus = Constants.S7A1;
            } else if (mediationEndDateChangeCount == 1) {
                dateRequestStatus = Constants.S7A2;
            }
            // MessageStatus
            if (groupMessageFlag1 == 0 && groupMessageFlag2 == 0) {
                messageStatus = Constants.S7B0;
            } else if (groupMessageFlag1 == 1) {
                messageStatus = Constants.S7B1;
            } else if (groupMessageFlag1 == 3) {
                messageStatus = Constants.S7B2;
            } else if (groupMessageFlag1 == 2) {
                messageStatus = Constants.S7B3;
            } else if (groupMessageFlag2 == 1) {
                messageStatus = Constants.S7B4;
            } else if (groupMessageFlag2 == 3) {
                messageStatus = Constants.S7B5;
            } else if (groupMessageFlag2 == 2) {
                messageStatus = Constants.S7B6;
            }
        }
        // タイトル名の設定
        caseInfo.setCaseTitle(caseTitle);
        // 案件ステータスの設定
        caseInfo.setStage(stage);
        caseInfo.setCaseStatus(caseStatus);
        caseInfo.setDateRequestStatus(dateRequestStatus);
        caseInfo.setMessageStatus(messageStatus);

        // 日付フォーマット変換
        // 回答期限日
        Date replyEndDate = casesData.getReplyEndDate();
        // 反訴の回答期限日
        Date counterclaimEndDate = casesData.getCounterclaimEndDate();
        // 手続き中止日
        Date cancelDate = casesData.getCancelDate();
        // 解決日時
        Date resolutionDate = casesData.getResolutionDate();
        // 交渉期限日
        Date negotiationEndDate = casesData.getNegotiationEndDate();
        // 調停期限日
        Date mediationEndDate = casesData.getMediationEndDate();
        // 期日用項目の設定
        if (replyEndDate != null) {
            caseInfo.setReplyEndDate(stringToStringFormat(dateToString(replyEndDate)));
        }
        if (counterclaimEndDate != null) {
            caseInfo.setCounterclaimEndDate(stringToStringFormat(dateToString(counterclaimEndDate)));
        }
        if (cancelDate != null) {
            caseInfo.setCancelDate(stringToStringFormat(dateToString(cancelDate)));
        }
        if (resolutionDate != null) {
            caseInfo.setResolutionDate(stringToStringFormat(dateToString(resolutionDate)));
        }
        if (negotiationEndDate != null) {
            caseInfo.setNegotiationEndDate(stringToStringFormat(dateToString(negotiationEndDate)));
        }
        if (mediationEndDate != null) {
            caseInfo.setMediationEndDate(stringToStringFormat(dateToString(mediationEndDate)));
        }

        return caseInfo;
    }

    /**
     * モジュール利用状況Flgの設定
     * 
     * @param masterPlatforms 利用モジュール状況取得
     * @return int モジュール利用状況Flg
     */
    public int setCaseInfoMoudleFlg(MasterPlatforms masterPlatforms) {
        // モジュール利用状況Flg
        int moudleFlg = 0;
        // 交渉機能利用有無
        int phaseNegotiation = masterPlatforms.getPhase_negotiation();
        // 調停機能利用有無
        int phaseMediation = masterPlatforms.getPhase_mediation();
        // 反訴機能利用有無
        int phaseReply = masterPlatforms.getPhase_reply();
        // モジュール利用状況Flgの設定
        if (phaseNegotiation == 1 && phaseMediation == 1 && phaseReply == 1) {
            moudleFlg = 1;
        } else if (phaseNegotiation == 1 && phaseMediation == 1 && phaseReply == 0) {
            moudleFlg = 2;
        } else if (phaseNegotiation == 1 && phaseMediation == 0 && phaseReply == 1) {
            moudleFlg = 3;
        } else if (phaseNegotiation == 1 && phaseMediation == 0 && phaseReply == 0) {
            moudleFlg = 4;
        } else if (phaseNegotiation == 0 && phaseMediation == 1 && phaseReply == 1) {
            moudleFlg = 5;
        } else if (phaseNegotiation == 0 && phaseMediation == 1 && phaseReply == 0) {
            moudleFlg = 6;
        } else if (phaseNegotiation == 0 && phaseMediation == 0 && phaseReply == 1) {
            moudleFlg = 7;
        } else if (phaseNegotiation == 0 && phaseMediation == 0 && phaseReply == 0) {
            moudleFlg = 8;
        }
        return moudleFlg;
    }

    /**
     * 和解内容の戻り項目の設定 下記項目を返す
     * ①Overview (概要)
     * ②PayAmount （申し立て支払い金額）
     * ③CounterClaimPayment （反訴支払い金額）
     * ④PaymentEndDate （支払期日）
     * ⑤ShipmentPayType （返送時送料負担区分）
     * ⑥SpecialItem （特記事項）
     * ⑦Status
     * 
     * @param caseNegotiations 和解内容の取得
     * @return caseNegotiationsData API「和解内容取得」を呼び出すData---和解内容の設定
     */
    public CaseNegotiationsData setCaseNegotiationsData(CaseNegotiations caseNegotiations) {
        CaseNegotiationsData caseNegotiationsData = new CaseNegotiationsData();

        // ステータス
        int status = caseNegotiations.getStatus();
        // 希望する解決方法
        String expectResloveTypeValue = caseNegotiations.getExpectResloveTypeValue();
        // その他 内容
        String otherContext = caseNegotiations.getOtherContext();
        // 支払金額
        Double payAmount = caseNegotiations.getPayAmount();
        // 反訴の支払金額
        Double counterClaimPayment = caseNegotiations.getCounterClaimPayment();
        // 支払期日
        String paymentEndDate = caseNegotiations.getPaymentEndDate();
        // 返送時送料負担区分
        int shipmentPayType = caseNegotiations.getShipmentPayType();
        // 特記事項
        String specialItem = caseNegotiations.getSpecialItem();
        // 最終変更日
        String lastModifiedDate = caseNegotiations.getLastModifiedDate();

        // Overview 概要
        String overview = null;
        // 概要を設定
        // OtherContextがNullでない場合
        if (otherContext != null) {
            // Overviewに ExpectResloveTypeValue + '、' + OtherContext を設定
            overview = expectResloveTypeValue + "、" + otherContext;
        }
        // OtherContextがNullの場合
        else {
            // Overviewに ExpectResloveTypeValue を設定
            overview = expectResloveTypeValue;
        }
        // 和解内容の戻り項目の設定
        caseNegotiationsData.setStatus(status);
        caseNegotiationsData.setOverview(overview);
        if (payAmount != null) {
            caseNegotiationsData.setPayAmount(doubleToStringFormat(payAmount));
        }
        if (counterClaimPayment != null) {
            caseNegotiationsData.setCounterClaimPayment(doubleToStringFormat(counterClaimPayment));
        }
        if (paymentEndDate != null) {
            caseNegotiationsData.setPaymentEndDate(stringToStringFormat(paymentEndDate));
        }
        caseNegotiationsData.setShipmentPayType(shipmentPayType);
        caseNegotiationsData.setSpecialItem(specialItem);
        caseNegotiationsData.setLastModifiedDate(lastModifiedDate);

        return caseNegotiationsData;
    }

    /**
     * 調停内容の戻り項目の設定 下記項目を返す
     * ①ExpectResloveTypeValue （対応方法）
     * ②PayAmount （申し立て支払い金額）
     * ③CounterClaimPayment （反訴支払い金額）
     * ④PaymentEndDate （支払期日）
     * ⑤ShipmentPayType （返送時送料負担区分）
     * ⑥SpecialItem （特記事項）
     * ⑦Status
     * 
     * @param caseMediations
     * @return caseMediationsData API「調停内容取得」を呼び出すData---調停内容の設定
     */
    public CaseMediationsData setCaseMediationsData(CaseMediations caseMediations) {
        CaseMediationsData caseMediationsData = new CaseMediationsData();

        // ステータス
        int status = caseMediations.getStatus();
        // 希望する解決方法
        String expectResloveTypeValue = caseMediations.getExpectResloveTypeValue();
        // 支払金額
        Double payAmount = caseMediations.getPayAmount();
        // 反訴の支払金額
        Double counterClaimPayment = caseMediations.getCounterClaimPayment();
        // 支払期日
        String paymentEndDate = caseMediations.getPaymentEndDate();
        // 返送時送料負担区分
        int shipmentPayType = caseMediations.getShipmentPayType();
        // 特記事項
        String specialItem = caseMediations.getSpecialItem();
        // 最終変更日
        String lastModifiedDate = caseMediations.getLastModifiedDate();

        // 調停内容の戻り項目の設定
        caseMediationsData.setStatus(status);
        caseMediationsData.setExpectResloveTypeValue(expectResloveTypeValue);
        if (payAmount != null) {
            caseMediationsData.setPayAmount(doubleToStringFormat(payAmount));
        }
        if (counterClaimPayment != null) {
            caseMediationsData.setCounterClaimPayment(doubleToStringFormat(counterClaimPayment));
        }
        if (paymentEndDate != null) {
            caseMediationsData.setPaymentEndDate(stringToStringFormat(paymentEndDate));
        }
        caseMediationsData.setShipmentPayType(shipmentPayType);
        caseMediationsData.setSpecialItem(specialItem);
        caseMediationsData.setLastModifiedDate(lastModifiedDate);

        return caseMediationsData;
    }

    // 共通メソッド
    /**
     * 日付書式設定
     * Date to String (yyyyMMdd)
     * 
     * @param formatDate Date
     * @return String String (yyyyMMdd)
     */
    public static String dateToString(Date formatDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.FORMAT);
        return simpleDateFormat.format(formatDate);
    }

    /**
     * 日付書式設定
     * String (yyyyMMdd) to String (yyyy年MM月dd日)
     * 
     * @param parseString String (yyyyMMdd)
     * @return String String (yyyy年MM月dd日)型日付
     */
    public static String stringToStringFormat(String parseString) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.FORMAT);
            SimpleDateFormat simpleDateMenuFormat = new SimpleDateFormat(Constants.MENU_FORMAT);
            simpleDateFormat.setLenient(false);
            return simpleDateMenuFormat.format(simpleDateFormat.parse(parseString));
        } catch (ParseException e) {
            return parseString;
        }
    }

    /**
     * 金額書式設定
     * double to String ($x,xxx.xx)
     * 
     * @param money 金額
     * @return String String ($x,xxx.xx)
     */
    public static String doubleToStringFormat(Double money) {
        NumberFormat nf = NumberFormat.getInstance();
        String formattedMoney = Constants.STR_DOLLAR + nf.format(money);
        return formattedMoney;
    }
}
