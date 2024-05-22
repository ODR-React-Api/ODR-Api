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

    // S03
    public static final int STR_CASES_NEGOTIATIONENDDATECHANGESTATUS_1 = 1;
    public static final int STR_CASES_NEGOTIATIONENDDATECHANGESTATUS_2 = 2;
    public static final String CORRESPOND_FLAG_1 = "1";
    public static final String CORRESPOND_FLAG_0 = "0";
    public static final int STR_CASE_NEGOTIATIONS_STATUS_0 = 0;
    public static final int STR_CASE_NEGOTIATIONS_STATUS_1 = 1;
    public static final int STR_CASE_NEGOTIATIONS_STATUS_2 = 2;
    public static final int STR_CASE_NEGOTIATIONS_STATUS_3 = 3;
    public static final int STR_CASE_NEGOTIATIONS_STATUS_4 = 4;
    public static final int STR_CASE_NEGOTIATIONS_STATUS_5 = 5;
    public static final int STR_CASE_NEGOTIATIONS_STATUS_7 = 7;
    public static final int STR_CASE_NEGOTIATIONS_STATUS_8 = 8;
    public static final int STR_CASE_NEGOTIATIONS_STATUS_9 = 9;
    public static final int STR_CASE_NEGOTIATIONS_STATUS_10 = 10;
    public static final int STR_CASE_NEGOTIATIONS_STATUS_11 = 11;
    public static final int STR_CASE_NEGOTIATIONS_STATUS_12 = 12;
    public static final int STR_CASE_NEGOTIATIONS_STATUS_13 = 13;
    public static final int STR_CASE_NEGOTIATIONS_STATUS_14 = 14;
    public static final int STR_CASE_NEGOTIATIONS_STATUS_15 = 15;
    public static final int STR_CASE_MEDIATIONS_STATUS_1 = 1;
    public static final int STR_CASE_MEDIATIONS_STATUS_2 = 2;
    public static final int STR_CASE_MEDIATIONS_STATUS_3 = 3;
    public static final int STR_CASE_MEDIATIONS_STATUS_7 = 7;
    public static final int STR_CASE_MEDIATIONS_STATUS_8 = 8;
    public static final int STR_CASES_GROUPMESSAGEFLAG = 1;
    public static final int USER_MESSAGES_NOTREADEDCNT_0 = 0;
    public static final int NOREADCNT_0 = 0;
    public static final int STR_CASES_MEDIATORDISCLOSUREFLAG_1 = 1;
    public static final String SIMPLE_DATE_FORMAT = "yyyyMMdd";

    // S04
    public static final String CASE_STATUS_0 = "0000";
    public static final String CASE_STATUS_1 = "0001";
    public static final int UPDATE_NUMBER_0 = 0;
    public static final int PARTICIPATED_FLAG_1 = 1;
    public static final int PARTICIPATED_FLAG_0 = 0;

    // S03,S04 その他人と共有する
    public static final int STR_CASES_CASESTAGE_0 = 0;
    public static final String PLATFORMID_0001 = "0001";
    public static final int NUM_0 = 0;
    public static final int NUM_2 = 2;
    // S03 その他人と共有する
    public static final int STR_CASES_CASESTAGE_2 = 2;
    public static final int STR_CASES_CASESTAGE_3 = 3;
    public static final int STR_CASES_CASESTAGE_6 = 6;
    public static final int STR_CASES_CASESTAGE_7 = 7;
    public static final int POSITIONFLAG_PETITION = 1;
    public static final int POSITIONFLAG_TRADER = 2;
    public static final int POSITIONFLAG_MEDIATOR = 3;
    public static final String DEFAULT_CORRESPONDDATE = "99999999";

    // S17 その他人と共有する
    public static final int DELETE_FLAG_0 = 0;
    public static final int DELETE_FLAG_1 = 1;
    public static final String SPACE_STRING = "";

    // S17
    public static final int SESSIONLOGIN_FILES_SIZE_0 = 0;

}
