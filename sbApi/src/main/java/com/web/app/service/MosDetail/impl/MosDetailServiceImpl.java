package com.web.app.service.MosDetail.impl;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.app.domain.MosDetail.CaseInfo;
import com.web.app.domain.MosDetail.CaseMediations;
import com.web.app.domain.MosDetail.CaseNegotiations;
import com.web.app.domain.MosDetail.Cases;
import com.web.app.domain.MosDetail.Files;
import com.web.app.domain.MosDetail.GetCaseInfoParameter;
import com.web.app.domain.MosDetail.MasterPlatforms;
import com.web.app.domain.MosDetail.MediationsData;
import com.web.app.domain.MosDetail.NegotiationsData;
import com.web.app.domain.MosDetail.OdrUsers;
import com.web.app.mapper.MosDetail.MosDetailMapper;
import com.web.app.service.MosDetail.MosDetailService;

/**
 * 申立て概要画面
 * Service层实现类
 * MosDetailServiceImpl
 */
@Service
public class MosDetailServiceImpl implements MosDetailService {
    // 定数
    // 申立後-参加待ち
    public static final String WAIT_FOR_JOIN = "0000";
    // 申立後-参加済-回答待ち
    public static final String WAIT_FOR_REPLY = "0001";
    // S01 （申立て登録直後）
    public static final String S01 = "1";
    // S02 （参加表明）
    public static final String S02 = "2";
    // S3B0 （相手方和解案作成待ち）
    public static final String S3B0 = "0";
    // S3B99 （相手方和解案作成待ち）
    public static final String S3B99 = "99";
    // S3B1 （相手方和解案下書き（共同編集））
    public static final String S3B1 = "4";
    // S3B2 （相手方提出済み）
    public static final String S3B2 = "2";
    // S3B3 （合意済み）
    public static final String S3B3 = "3";
    // S3B4 （確認済み（申立人のみ））
    public static final String S3B4 = "4";
    // S3B5 （確認済み（相手方のみ））
    public static final String S3B5 = "5";
    // S3B7 （申立人対案作成まち）
    public static final String S3B7 = "7";
    // S3B8 （申立人対案作成まち・下書き中）
    public static final String S3B8 = "8";
    // S3B9 （申立人対案提出済み）
    public static final String S3B9 = "9";
    // S3B10 （相手方対案作成待ち）
    public static final String S3B10 = "10";
    // S3B11 （相手方対案下書き）
    public static final String S3B11 = "11";
    // S3B12 （相手方対案提出済み）
    public static final String S3B12 = "12";
    // S3B13 （申立人和解案下書き）
    public static final String S3B13 = "13";
    // S3B14 （申立人和解案下書き・共同編集）
    public static final String S3B14 = "14";
    // S3B15 （申立人和解案提出済み）
    public static final String S3B15 = "15";
    // S61 （調定人未指名）
    public static final String S61 = "1";
    // S62 （調定人指名後）
    public static final String S62 = "2";
    // S7C0 （調停案提出待ち）
    public static final String S7C0 = "0";
    // S7C99 （調停案提出待ち）
    public static final String S7C99 = "99";
    // S7C1 （調停案が提出された）
    public static final String S7C1 = "1";
    // S7C2 （調停案を合意した（申立人）
    public static final String S7C2 = "2";
    // S7C3 （調停案を合意した（相手方））
    public static final String S7C3 = "3";
    // S7C4 （調停案を合意した（両方））
    public static final String S7C4 = "4";
    // S7C5 （調停案が確認済みでした。（申立人））
    public static final String S7C5 = "5";
    // S7C6 （調停案が確認済みでした。（相手方））
    public static final String S7C6 = "6";
    // 上記判定条件以外の場合はCaseStatusにS9A9B9C9（網羅外ステータス）を設定する。
    public static final String S9A9B9C9 = "S9A9B9C9";
    // S3A0 （期日変更依頼なし）
    public static final int S3A0 = 0;
    // S3A1 （相手方期日変更依頼あり）
    public static final int S3A1 = 1;
    // S3A2 （申立人期日変更依頼あり）
    public static final int S3A2 = 2;
    // S3A3 （期日変更後、（再変更不可））
    public static final int S3A3 = 3;
    // S7A1 （調停期限延長可能）
    public static final int S7A1 = 1;
    // S7A2 （調停期限延長不可）
    public static final int S7A2 = 2;
    // S7B0 （個別やりとり依頼可能）
    public static final int S7B0 = 0;
    // S7B1 （個別やりとり依頼あり（申立人依頼））
    public static final int S7B1 = 1;
    // S7B2 （個別やりとり依頼が承認された（申立人依頼））
    public static final int S7B2 = 2;
    // S7B3 （個別やりとり依頼が拒否された（申立人依頼））
    public static final int S7B3 = 3;
    // S7B4 （個別やりとり依頼あり（相手方依頼））
    public static final int S7B4 = 4;
    // S7B5 （個別やりとり依頼が承認された（相手方依頼））
    public static final int S7B5 = 5;
    // S7B6 （個別やりとり依頼が拒否された（相手方依頼）））
    public static final int S7B6 = 6;

    // 添付資料の取得 RelationType案件種類
    // 和解案
    public static final int CASE_NEGOTIATIONS = 4;
    // 調停案
    public static final int CASE_MEDIATIONS = 5;

    @Autowired
    private MosDetailMapper mosDetailMapper;

    /**
     * 申立て一覧画面より渡されたCaseIdを引数として、DBから該当する案件のステータスを取得する。
     */
    @Override
    public CaseInfo getCaseInfo(String caseId, String platformId, String userId) {
        CaseInfo caseInfo = new CaseInfo();
        // 該当案件のステータスを取得
        Cases cases = mosDetailMapper.getCaseInfo(caseId);

        // 案件ステータスの設定
        if (cases != null) {
            caseInfo = setCaseInfoCaseStatus(cases);
        } else {
            // （網羅外ステータス）を設定する
            caseInfo.setCaseStatus(S9A9B9C9);
        }

        // 利用モジュール状況取得
        MasterPlatforms masterPlatforms = mosDetailMapper.getPhases(platformId);
        // 利用モジュール状況の設定
        if (masterPlatforms != null) {
            int moudleFlg = setCaseInfoMoudleFlg(masterPlatforms);
            caseInfo.setMoudleFlg(moudleFlg);
        }

        // チュートリアル表示制御取得
        OdrUsers odrUsers = mosDetailMapper.getShowTuritor(userId, platformId);
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
     * チュートリアル表示制御変更
     */
    // @Transactional
    @Override
    public int updShowTuritor(GetCaseInfoParameter getCaseInfoParameter) {
        return mosDetailMapper.updShowTuritor(getCaseInfoParameter);
    }

    /**
     * DBよりケースに該当する和解の内容を取得して、画面へ返す。
     */
    @Override
    public NegotiationsData getNegotiationsData(String caseId) {
        NegotiationsData negotiationsData = new NegotiationsData();
        // 和解内容の取得
        CaseNegotiations caseNegotiations = mosDetailMapper.getCaseNegotiations(caseId);
        // 和解内容の設定
        if (caseNegotiations != null) {
            negotiationsData = setNegotiationsData(caseNegotiations);

            if (caseNegotiations.getCaseId() != null) {
                // 和解案 添付資料の取得
                List<Files> files = mosDetailMapper.getFiles(caseNegotiations.getCaseId(), CASE_NEGOTIATIONS);
                if (files != null) {
                    // 添付資料リストの設定
                    negotiationsData.setFile(files);
                }
            }

            return negotiationsData;
        } else {
            return null;
        }
    }

    /**
     * 渡し項目.CaseIdを引数に、DBよりケースに該当する調停の内容を取得して、画面へ返す。
     */
    @Override
    public MediationsData getMediationsData(String caseId) {
        MediationsData mediationsData = new MediationsData();
        // 調停内容の取得
        CaseMediations caseMediations = mosDetailMapper.getCaseMediations(caseId);
        // 調停内容の設定
        if (caseMediations != null) {
            mediationsData = setMediationsData(caseMediations);

            if (caseMediations.getCaseId() != null) {
                // 調停案 添付資料の取得
                List<Files> files = mosDetailMapper.getFiles(caseMediations.getCaseId(), CASE_MEDIATIONS);
                if (files != null) {
                    // 添付資料リストの設定
                    mediationsData.setFile(files);
                }
            }

            return mediationsData;
        } else {
            return null;
        }
    }

    // 画面内容取得 下記項目を返す
    public CaseInfo setCaseInfoCaseStatus(Cases cases) {
        CaseInfo caseInfo = new CaseInfo();
        // タイトル名
        String caseTitle = cases.getCaseTitle();
        // 案件ステージ
        int caseStage = cases.getCaseStage();
        // 案件ステータス
        String status = cases.getCaseStatus();
        // 和解案.ステータス
        String negotiationStatus = cases.getNegotiationStatus();
        // 案件別個人情報リレーション.調停人
        String mediatorUserEmail = cases.getMediatorUserEmail();
        // 調停案.ステータス
        String mediationStatus = cases.getMediationStatus();
        // 交渉期限日変更ステータス
        int negotiationEndDateChangeStatus = cases.getNegotiationEndDateChangeStatus();
        // 交渉期限日変更回数
        int negotiationEndDateChangeCount = cases.getNegotiationEndDateChangeCount();
        // 調停期限変更回数
        int mediationEndDateChangeCount = cases.getMediationEndDateChangeCount();
        // 個別やりとりステータス（申立人↔調停人）
        int groupMessageFlag1 = cases.getGroupMessageFlag1();
        // 個別やりとりステータス（相手方↔調停人）
        int groupMessageFlag2 = cases.getGroupMessageFlag2();
        String caseStatus = status;
        int dateRequestStatus = 99;
        int messageStatus = 99;
        int stage = caseStage;
        // 案件ステータスの判定
        if (caseStage == 0) {
            // CaseStatus
            if (status == "0000") {
                caseStatus = S01;
            } else if (status == "0001") {
                caseStatus = S02;
            } else {
                caseStatus = S9A9B9C9;
            }
        } else if (caseStage == 3) {
            // CaseStatus
            if (negotiationStatus == "0") {
                caseStatus = S3B0;
            } else if (negotiationStatus == null) {
                caseStatus = S3B99;
            } else if (negotiationStatus == "1") {
                caseStatus = S3B1;
            } else if (negotiationStatus == "2") {
                caseStatus = S3B2;
            } else if (negotiationStatus == "3") {
                caseStatus = S3B3;
            } else if (negotiationStatus == "4") {
                caseStatus = S3B4;
            } else if (negotiationStatus == "5") {
                caseStatus = S3B5;
            } else if (negotiationStatus == "7") {
                caseStatus = S3B7;
            } else if (negotiationStatus == "8") {
                caseStatus = S3B8;
            } else if (negotiationStatus == "9") {
                caseStatus = S3B9;
            } else if (negotiationStatus == "10") {
                caseStatus = S3B10;
            } else if (negotiationStatus == "11") {
                caseStatus = S3B11;
            } else if (negotiationStatus == "12") {
                caseStatus = S3B12;
            } else if (negotiationStatus == "13") {
                caseStatus = S3B13;
            } else if (negotiationStatus == "14") {
                caseStatus = S3B14;
            } else if (negotiationStatus == "15") {
                caseStatus = S3B15;
            } else {
                caseStatus = S9A9B9C9;
            }
            // DateRequestStatus
            if (negotiationEndDateChangeStatus == 0 && negotiationEndDateChangeCount == 0) {
                dateRequestStatus = S3A0;
            } else if (negotiationEndDateChangeStatus == 1) {
                dateRequestStatus = S3A1;
            } else if (negotiationEndDateChangeStatus == 2) {
                dateRequestStatus = S3A2;
            } else if (negotiationEndDateChangeStatus == 0 && negotiationEndDateChangeCount == 1) {
                dateRequestStatus = S3A3;
            }
        } else if (caseStage == 6) {
            // CaseStatus
            if (mediatorUserEmail == null) {
                caseStatus = S61;
            } else {
                caseStatus = S62;
            }
        } else if (caseStage == 7) {
            // CaseStatus
            if (mediationStatus == "0") {
                caseStatus = S7C0;
            } else if (mediationStatus == null) {
                caseStatus = S7C99;
            } else if (mediationStatus == "1") {
                caseStatus = S7C1;
            } else if (mediationStatus == "2") {
                caseStatus = S7C2;
            } else if (mediationStatus == "3") {
                caseStatus = S7C3;
            } else if (mediationStatus == "4") {
                caseStatus = S7C4;
            } else if (mediationStatus == "7") {
                caseStatus = S7C5;
            } else if (mediationStatus == "8") {
                caseStatus = S7C6;
            } else {
                caseStatus = S9A9B9C9;
            }
            // DateRequestStatus
            if (mediationEndDateChangeCount == 0) {
                dateRequestStatus = S7A1;
            } else if (mediationEndDateChangeCount == 1) {
                dateRequestStatus = S7A2;
            }
            // MessageStatus
            if (groupMessageFlag1 == 0 && groupMessageFlag2 == 0) {
                messageStatus = S7B0;
            } else if (groupMessageFlag1 == 1) {
                messageStatus = S7B1;
            } else if (groupMessageFlag1 == 3) {
                messageStatus = S7B2;
            } else if (groupMessageFlag1 == 2) {
                messageStatus = S7B3;
            } else if (groupMessageFlag2 == 1) {
                messageStatus = S7B4;
            } else if (groupMessageFlag2 == 3) {
                messageStatus = S7B5;
            } else if (groupMessageFlag2 == 2) {
                messageStatus = S7B6;
            }
        }
        caseInfo.setCaseTitle(caseTitle);
        caseInfo.setStage(stage);
        caseInfo.setCaseStatus(caseStatus);
        caseInfo.setDateRequestStatus(dateRequestStatus);
        caseInfo.setMessageStatus(messageStatus);

        // 日付フォーマット変換
        // 回答期限日
        Date replyEndDate = cases.getReplyEndDate();
        // 反訴の回答期限日
        Date counterclaimEndDate = cases.getCounterclaimEndDate();
        // 手続き中止日
        Date cancelDate = cases.getCancelDate();
        // 解決日時
        Date resolutionDate = cases.getResolutionDate();
        // 交渉期限日
        Date negotiationEndDate = cases.getNegotiationEndDate();
        // 調停期限日
        Date mediationEndDate = cases.getMediationEndDate();
        if (replyEndDate != null) {
            caseInfo.setReplyEndDate(stringToStringFormat(dateToString(replyEndDate)));
            ;
        }
        if (counterclaimEndDate != null) {
            caseInfo.setCounterclaimEndDate(stringToStringFormat(dateToString(counterclaimEndDate)));
            ;
        }
        if (cancelDate != null) {
            caseInfo.setCancelDate(stringToStringFormat(dateToString(cancelDate)));
        }
        if (resolutionDate != null) {
            caseInfo.setResolutionDate(stringToStringFormat(dateToString(resolutionDate)));
            ;
        }
        if (negotiationEndDate != null) {
            caseInfo.setNegotiationEndDate(stringToStringFormat(dateToString(negotiationEndDate)));
        }
        if (mediationEndDate != null) {
            caseInfo.setMediationEndDate(stringToStringFormat(dateToString(mediationEndDate)));
            ;
        }

        return caseInfo;
    }

    // モジュール利用状況Flgの設定
    public int setCaseInfoMoudleFlg(MasterPlatforms masterPlatforms) {
        // モジュール利用状況Flg
        int moudleFlg = 0;
        // 交渉機能利用有無
        int phaseNegotiation = masterPlatforms.getPhaseNegotiation();
        // 調停機能利用有無
        int phaseMediation = masterPlatforms.getPhaseMediation();
        // 反訴機能利用有無
        int phaseReply = masterPlatforms.getPhaseReply();
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

    // 和解内容の戻り項目の設定
    public NegotiationsData setNegotiationsData(CaseNegotiations caseNegotiations) {
        NegotiationsData negotiationsData = new NegotiationsData();

        // ステータス
        int status = caseNegotiations.getStatus();
        // 希望する解決方法
        String expectResloveTypeValue = caseNegotiations.getExpectResloveTypeValue();
        // その他 内容
        String otherContext = caseNegotiations.getOtherContext();
        // 支払金額
        double payAmount = caseNegotiations.getPayAmount();
        // 反訴の支払金額
        double counterClaimPayment = caseNegotiations.getCounterClaimPayment();
        // 支払期日
        Date paymentEndDate = caseNegotiations.getPaymentEndDate();
        // 返送時送料負担区分
        int shipmentPayType = caseNegotiations.getShipmentPayType();
        // 特記事項
        String specialItem = caseNegotiations.getSpecialItem();
        // 最終変更日
        Date lastModifiedDate = caseNegotiations.getLastModifiedDate();

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
        negotiationsData.setStatus(status);
        negotiationsData.setOverview(overview);
        negotiationsData.setPayAmount(doubleToStringFormat(payAmount));
        negotiationsData.setCounterClaimPayment(doubleToStringFormat(counterClaimPayment));
        if (paymentEndDate != null) {
            negotiationsData.setPaymentEndDate(stringToStringFormat(dateToString(paymentEndDate)));
        }
        negotiationsData.setShipmentPayType(shipmentPayType);
        negotiationsData.setSpecialItem(specialItem);
        if (lastModifiedDate != null) {
            negotiationsData.setLastModifiedDate(dateToString(lastModifiedDate));
        }

        return negotiationsData;
    }

    // 調停内容の戻り項目の設定
    public MediationsData setMediationsData(CaseMediations caseMediations) {
        MediationsData mediationsData = new MediationsData();

        // ステータス
        int status = caseMediations.getStatus();
        // 希望する解決方法
        String expectResloveTypeValue = caseMediations.getExpectResloveTypeValue();
        // 支払金額
        double payAmount = caseMediations.getPayAmount();
        // 反訴の支払金額
        double counterClaimPayment = caseMediations.getCounterClaimPayment();
        // 支払期日
        Date paymentEndDate = caseMediations.getPaymentEndDate();
        // 返送時送料負担区分
        int shipmentPayType = caseMediations.getShipmentPayType();
        // 特記事項
        String specialItem = caseMediations.getSpecialItem();
        // 最終変更日
        Date lastModifiedDate = caseMediations.getLastModifiedDate();
        // 調停内容の戻り項目の設定
        mediationsData.setStatus(status);
        mediationsData.setExpectResloveTypeValue(expectResloveTypeValue);
        mediationsData.setPayAmount(doubleToStringFormat(payAmount));
        mediationsData.setCounterClaimPayment(doubleToStringFormat(counterClaimPayment));
        if (paymentEndDate != null) {
            mediationsData.setPaymentEndDate(stringToStringFormat(dateToString(paymentEndDate)));
        }
        mediationsData.setShipmentPayType(shipmentPayType);
        mediationsData.setSpecialItem(specialItem);
        if (lastModifiedDate != null) {
            mediationsData.setLastModifiedDate(dateToString(lastModifiedDate));
        }

        return mediationsData;
    }

    // 日付フォーマット定数
    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String MENU_FORMAT = "yyyy年MM月dd日";

    // Date to String (yyyyMMdd)
    public static String dateToString(Date formatDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT);
        return simpleDateFormat.format(formatDate);
    }

    // String to String (yyyy年MM月dd日)
    public static String stringToStringFormat(String parseString) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT);
            SimpleDateFormat simpleDateMenuFormat = new SimpleDateFormat(MENU_FORMAT);
            simpleDateFormat.setLenient(false);
            return simpleDateMenuFormat.format(simpleDateFormat.parse(parseString));
        } catch (ParseException e) {
            return parseString;
        }
    }

    // double to String ($x,xxx.xx)
    public static String doubleToStringFormat(double money) {
        NumberFormat nf = NumberFormat.getInstance();
        String formattedMoney = "$" + nf.format(money);
        return formattedMoney;
    }
}
