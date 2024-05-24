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
    //（相手方和解案作成待ち）
    public static final String S3B0 = "0";
    //（相手方和解案下書き（共同編集））
    public static final String S3B1 = "1";
   
    //（申立人対案作成まち）
    public static final String S3B7 = "7";
    //（申立人対案作成まち・下書き中）
    public static final String S3B8 = "8";
    //（相手方対案作成待ち）
    public static final String S3B10 = "10";
    //（相手方対案下書き）
    public static final String S3B11 = "11";
    //（申立人和解案下書き）
    public static final String S3B13 = "13";
    //（申立人和解案下書き・共同編集）
    public static final String S3B14 = "14";
    public static final String FORMAT  = "yyyy-MM-dd HH:mm:ss";
    public static final String MENU_FORMAT  = "yyyy年MM月dd日";
    //ログインユーザーは申立人の場合
    public static final int POSITIONFLAG_PETITION = 1;
    //ログインユーザーは相手方の場合
    public static final int POSITIONFLAG_TRADER= 2;
    //DeleteFlag
    public static final int DELETE_FLAG_0 = 0;
    public static final int DELETE_FLAG_1 = 1;
    //「case_file_relations.RelationType案件種類」 4:和解案
    public static final int CASE_NEGOTIATIONS =4;
    //master_templatesテンプレートの種類
    //0:和解案,
    public static final int TEMPLATE_TYPE_0 = 0;
    //1:調停案,
    public static final int TEMPLATE_TYPE_1 = 1;
    //2:仲裁案,
    public static final int TEMPLATE_TYPE_2 = 2;
    //3:和解案合意書, 
    public static final int TEMPLATE_TYPE_3 = 3;
    //4: 調停案合意書
    public static final int TEMPLATE_TYPE_4 = 4;
    // 解決率: 調停件数が0の場合→ "-"で表示
    public static final String STR_YOKO = "-";
    // 件
    public static final String STR_KEN = "件";
    // 現在
    public static final String STR_GENZAI = "現在";
}
