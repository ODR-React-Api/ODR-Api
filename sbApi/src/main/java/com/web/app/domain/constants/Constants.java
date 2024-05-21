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
    public static final int STR_ODR_USERS_INSERTFLAG = 0;
    public static final int STR_ODR_USERS_INSERT_STATUS = 0;
    public static final String STR_ODR_USERS_INSERT_MESSAGEFREQUENCY = "100";
    public static final int STR_ODR_USERS_INSERT_TERMSCONFIRMED = 1;
    public static final int STR_ODR_USERS_INSERT_USERTYPE = 0;
    public static final int STR_ODR_USERS_INSERT_DELETEFLAG = 0;
    public static final String STR_ODR_USERS_INSERT_PLATFORMID = "0001";
    public static final String STR_ODR_USERS_INSERT_LANGUAGEID = "JP";
    public static final String REGISTERUSER_MAILID_M002_URL = "http://localhost:3000/";
    public static final String REGISTERUSER_SENDMAIL_USERID = "ODR_Front";
    public static final int STR_CASES_CASESTAGE_0 = 0;
    public static final int STR_CASES_CASESTAGE_2 = 2;
    public static final int STR_CASES_CASESTAGE_3 = 3;
    public static final int STR_CASES_CASESTAGE_6 = 6;
    public static final int STR_CASES_CASESTAGE_7 = 7;
    public static final int POSITIONFLAG_PETITION = 1;
    public static final int POSITIONFLAG_TRADER = 2;
    public static final int POSITIONFLAG_MEDIATOR = 3;
    public static final String DEFAULT_CORRESPONDDATE = "99999999";
    public static final int NUM_0 = 0;
    public static final int NUM_1 = 1;
    public static final int NUM_2 = 2;
    public static final int NUM_3 = 3;
    public static final int NUM_4 = 4;
    public static final int NUM_5 = 5;
    public static final int NUM_6 = 6;
    public static final int NUM_7 = 7;
    public static final int NUM_8 = 8;
    public static final int NUM_9 = 9;
    public static final int NUM_10 = 10;
    public static final int NUM_11 = 11;
    public static final int NUM_12 = 12;
    public static final int NUM_13 = 13;
    public static final int NUM_14 = 14;
    public static final int NUM_15 = 15;
    public static final String CORRESPONDENCE_1 = "1";
    public static final String CORRESPONDENCE_0 = "0";
    public static final String STR_CASES_CASESTATUS_2 = "2";
    public static final String STR_CASES_CASESTATUS_3 = "3";
    public static final String STR_CASES_CASESTATUS_6 = "6";
    public static final String STR_CASES_CASESTATUS_7 = "7";
    public static final String STR_CASES_CASESTATUS_0 = "0";
    public static final int REGISTERUSER_SENDMAIL_CONTROLTYPE = 2;
    public static final String SPACE_STRING = " ";
    public static final int REGISTRATION_REGISTRATION = 1;
    public static final int REGISTRATION_REGISTRATION_UNREGISTERED = 0;
    public static final int MOSDETAIL_UPDATEFLAG_0 = 0;
    public static final int MOSDETAIL_UPDATEFLAG_1 = 1;
    public static final String AJAXRESULT_SUCCESS = "取得に成功しました。";

}
