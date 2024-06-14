package com.web.app.domain.constants;

// import java.util.Map;

public class Constants {
    // 字典值常量
    public static final String VEHICLE_TYPE_DICT_TYPE = "vehicle_type";

    public static final String MSG_SUCCESS = "Success";
    public static final String MSG_ERROR = "Error";
    public static final String MSG_UNAUTHORIZED = "アクセス権限がありません。";
    public static final String MSG_NOT_FOUND = "サービスが存在しません。";
    public static final String MSG_BAD_GATEWAY = "Http請求にエラーがあります。";
    public static final String MSG_NOT_SUCCESS = "未知エラーが発生しました。";
    public static final String RETCD_OK = "OK";
    public static final String RETCD_NG = "NG";
    public static final int RETCD_SUCCESS = 200;
    public static final int RETCD_ERROR = 500;
    public static final int RETCD_BAD_GATEWAY = 502;
    public static final int RETCD_UNAUTHORIZED = 401;
    public static final int RETCD_NOACCESS = 403;
    public static final int RETCD_NOT_FOUND = 404;
    public static final String SECRET_KEY = "THIS IS USED TO SIGN AND VERIFY JWT TOKENS, REPLACE IT WITH YOUR OWN SECRET, IT CAN BE ANY STRING";
    public static final String JURISDICTION = "user";
    public static final String CONTENT_TYPE = "application/json;charset=utf-8";
    public static final String DEFAULT_BANNGO = "0000000001";
    public static final String LOG_PATH = "log/log4net.config";
    public static final String REPOSITORY = "NETCoreRepository";
    public static final String DBCONNECTION = "DefaultConnection";
    public static final String SETTING = "AppSettings";
    public static final String CORS = "CorsPolicy";
    public static final int SEND_STATE = 2;
    public static final int RESULT_STATE_SUCCESS = 1;
    public static final int RESULT_STATE_ERROR = 0;
    public static final String EN = "en";
    public static final String JP = "jp";
    public static final String DATE_FORMAT = "yyyyMMddHHmmssfff";
    public static final String STR_ACTION_TYPE_REMOVE = "PetitionCancelled";
    public static final String STR_ACTION_TYPE_SUSPENSION = "CaseAborted";
    public static final String STR_ACTION_TYPE_AGREE = "NegotiationAgreed";
    public static final String STR_ACTION_TYPE_ESTABLISH = "NegotiationCompleted";
    public static final String STR_ACTION_TYPE_MEDIATIONMOVE = "MoveToMediation";
    public static final String STR_ACTION_TYPE_NEGOTIATIONDENIED = "NegotiationDenied";
    public static final String STR_ACTION_TYPE_NEGOTIATION2DENIED = "Negotiation2Denied";
    public static final String STR_ACTION_TYPE_NEGOTIATIONCONFIRMED = "NegotiationConfirmed";
    public static final String STR_ACTION_TYPE_UPDNEGOENDOK = "NegotiationLimitDayExtendRequestOK";
    public static final String STR_ACTION_TYPE_UPDNEGOENDNG = "NegotiationLimitDayExtendRequestNG";
    public static final String STR_ACTION_TYPE_UPDCASESINFO = "NegotiationLimitDayExtendRequest";
    public static final String STR_ACTION_TYPE_MCA = "MediatiorChangeAccepted";
    public static final String STR_ACTION_TYPE_MCD = "MediatiorChangeDenied";
    public static final String STR_ACTION_TYPE_TJ = "TraderJoined";
    public static final String STR_ACTION_TYPE_NEWMEDIATIOR = "NewMediatior";
    public static final String STR_ACTION_TYPE_NISD = "NegotiationInfoShareDenied";
    public static final String STR_ACTION_TYPE_MCR = "MediatiorChangeRequest";
    public static final String STR_ACTION_TYPE_MCW = "MediatiorChangeRequestWithReason";
    public static final String STR_ACTION_TYPE_MC = "MediatiorChanged";
    public static final String STR_ACTION_TYPE_MTM = "MoveToMediation";
    public static final int STR_CASE_STAGE_REPLY = 0;
    public static final int STR_CASE_STAGE_REMOVE = 1;
    public static final int STR_CASE_STAGE_CLAIMREPLY = 2;
    public static final int STR_CASE_STAGE_NEGOTIATION = 3;
    public static final int STR_CASE_STAGE_SUSPENSION = 4;
    public static final int STR_CASE_STAGE_ESTABLISH = 5;
    public static final int STR_CASE_STAGE_MAC = 6;
    public static final int STR_CASE_STAGE_MEDIATIONMOVE = 7;
    public static final int STR_CASE_STAGE_MEDIATIONCOMPLETED = 11;
    public static final int STR_CASE_STAGE_NEGOTIATIONDENIED = 12;
    public static final String STR_CASE_STATUS_REMOVE = "0100";
    public static final String STR_CASE_STATUS_SUSPENSION = "0400";
    public static final String STR_CASE_STATUS_ESTABLISH = "0500";
    public static final String STR_CASE_STATUS_MEDIATIONMOVE = "0700";
    public static final int STR_NEGOTIATION_STATUS_SUSPENSION = 3;
    public static final int STR_NEGOTIATION_STATUS_NEGOTIATIONCONFIRMED2 = 4;
    public static final int STR_NEGOTIATION_STATUS_NEGOTIATIONCONFIRMED3 = 5;
    public static final int STR_NEGOTIATION_STATUS_NEGOTIATIONCONFIRMED1 = 6;
    public static final int STR_NEGOTIATION_STATUS_NEGOTIATIONDENIED1 = 7;
    public static final int STR_NEGOTIATION_STATUS_NEGOTIATIONDENIED2 = 10;
    public static final int STR_CASE_NEGODATECHANGESTATUS = 0;
    public static final int STR_CASE_NEGODATECHANGESTATUS1 = 1;
    public static final int STR_CASE_NEGODATECHANGESTATUS2 = 2;
    public static final String STR_LAST_MODIFIED_BY_MDL = "Service_MediatorDecissionLogic";
    public static final int NEGOENDDATECHANGECOUNT = 1;
    public static final String STR_ACTION_TYPE_MR = "MediatiorResigned";
    public static final int STR_CASE_STAGE_ABORTEDCONFIRM = 13;
    public static final String STR_CASE_STATUS_ABORTEDCONFIRM = "1300";
    public static final String STR_ACTION_TYPE_ABORTEDCONFIRM = "CaseAbortedConfirmMailSend";
    public static final String HELP_URL = "http://uat-odr-service.azurewebsites.net/help";

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
    public static final String S3B1 = "1";
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

    // 案件種類
    // 「case_file_relations.RelationType案件種類」 0:申立て
    public static final int CASE_PETITIONS = 0;
    // 「case_file_relations.RelationType案件種類」 1:回答
    public static final int CASE_REPLIES_REPLY = 1;
    // 「case_file_relations.RelationType案件種類」 2:反訴
    public static final int CASE_REPLIES_COUNTERCLAIM = 2;
    // 「case_file_relations.RelationType案件種類」 3:反訴への回答
    public static final int CASE_CLAIMREPLIES = 3;
    // 「case_file_relations.RelationType案件種類」 4:和解案
    public static final int CASE_NEGOTIATIONS = 4;
    // 「case_file_relations.RelationType案件種類」 5:調停案
    public static final int CASE_MEDIATIONS = 5;

    // 日付フォーマット定数
    public static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String MENU_FORMAT = "yyyy年MM月dd日";
    
    // DeleteFlag 0:Active, 1:Deleted
    public static final int DELETE_FLAG_0 = 0;
    public static final int DELETE_FLAG_1 = 1;
    
    // public static final Map<Object,String> SIFANGJI_SIGNAL_STATE_MAP = new
    // HashMap<Object,String>(){{

    // }};

    // public static String[,] PRODUCTNAMELIST => new string[5, 3] {
    // { "SH0001", "ツヤ肌サプリメント", "aaa.111@yahoo.co.jp" },
    // { "SH0002", "ダウンコード", "bbb.222@gmail.com" },
    // { "SH0003", "パンホームベーカリー", "ccc.333@qq.com" },
    // { "SH0004", "美白肌ファンテーション", "ddd.444@gmail.com" },
    // { "SH0005", "書きやすいボールペン", "eee.555@icloud.com" },
    // };
    // public static string[,] HANBAISHALIST => new string[5, 2] {
    // { "aaa.111@yahoo.co.jp", "渡部 順" },
    // { "bbb.222@gmail.com", "大野 花子" },
    // { "ccc.333@qq.com", "さくら 商店" },
    // { "ddd.444@gmail.com", "ABCマート" },
    // { "eee.555@icloud.com", "ドン・キホーテ" },
    // };
}
