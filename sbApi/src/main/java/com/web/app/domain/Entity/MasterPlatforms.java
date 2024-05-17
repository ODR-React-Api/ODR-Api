package com.web.app.domain.Entity;

import lombok.Data;

/**
 * プラットフォームマスタ
 * 
 * @author DUC 耿浩哲
 * @since 2024/05/10
 * @version 1.0
 */
@Data
public class MasterPlatforms {

    // ID
    private String Id;

    // プラットフォーム名
    private String PlatformName;

    // ロゴ
    private String Logo;

    //ロゴ小
    private String Logo_Small;

    // タイムゾーン
    private String TimeZone;

    // 通貨
    private String Currency;

    // 通貨記号
    private String CurrencyMark;

    // ヒートアップ閾値
    private double HeatUpWeight;

    // 期日リマインド - 申立に対する回答（相手方）
    private int RemindTiming_reply;

    // 期日リマインド - 反訴に対する回答（申立人）
    private int RemindTiming_claimreply;

    // 期日リマインド - 交渉 
    private int RemindTiming_negotiation;

    // 期日リマインド - 交渉延長ある場合
    private int RemindTiming_ex_neg;

    // 期日リマインド - 調停 
    private int RemindTiming_mediation;

    // 期日リマインド - 調停延長ある場合
    private int RemindTiming_ex_med;

    // メッセージメール頻度
    private int InfoFrequency;

    // 作業日のみ
    private Integer WorkDayBase;

    // 相手への非通知設定
    private Integer PetitionNoticeMode;

    // 通知用メールアドレス
    private String PetitionNoticeEmail;

    // 申立回答期限
    private int ReplyLimitDays;

    // 反訴期限日数
    private int CounterclaimLimitDays;

    // 交渉期限日数
    private int NegotiationLimitDays;

    // 交渉期限延長可能日数
    private int NegotiationExtendDays;

    // 調停期限日数
    private int MediationLimitDays;

    // 調停期限延長可能日数
    private int MediationExtentdDays;

    // 商品IDの利用設定
    private int UserProductId;

    // 販売者名利用設定
    private int UseTraderName;

    // 販売元URL利用設定
    private int UseProductUrl;

    // 拡張項目利用設定
    private int UseOther;

    // 回答タブ拡張項目利用設定
    private int UseRepliesOther;

    // 反訴タブ拡張項目利用設定
    private int UseClaimRepliesOther;

    // 和解タブ拡張項目利用設定
    private int UseNegotiationsOther;

    // 調停タブ拡張項目利用設定
    private int UseMediationsOther;

    // デフォルト販売者Email自動設定
    private Integer UseDefaultTraderEmail;

    // デフォルト販売者Email
    private String DefaultTraderEmail;

    // メッセージ更新頻度
    private int MessageUpdateFrequency;

    private String languageId;

    private Integer ShowMiddleName;

    // 調停人指名方法
    private int MediatorAssignType;

    // 調停人との個別やりとり
    private Integer MediatorPrivateMessage;

    // 調停人との個別やりとり付加方法
    private Integer MediatorPrivateMessageAddMode;

    // 調停人の交渉フェーズ情報閲覧制限
    private Integer MediatorInfoLimit;

    // 情報閲覧制限できる期間
    private int MediatorInfoLimitConfirmDays;

    // 交渉機能利用有無
    private Integer Phase_negotiation;

    // 調停機能利用有無
    private Integer Phase_mediation;

    // 仲裁機能利用有無
    private Integer Phase_arbitration;

    // 反訴機能利用有無
    private Integer Phase_reply;

    // サービスサイトURL
    private String ServiceSiteUrl;

    // 申立内容-添付ファイル
    private int PetitionsContexFile;

    // 回答の内容-添付ファイル
    private int RepliesContextFile;

    // 回答画面_反訴の回答内容－添付ファイル
    private int ClaimRepliesContextFileR;

    // 反訴画面_反訴の回答内容－添付ファイル
    private int ClaimRepliesContextFileC;

    // 和解案内容-添付ファイル
    private int NegotiationsContextFile;

    // 調停案内容-添付ファイル
    private int MediationsContextFile;

    private String Other01;

    private String Other02;

    private String Other03;

    private String Other04;

    private String Other05;

    private Integer DeleteFlag;

    private String LastModifiedDate;

    private String LastModifiedBy;

    // 「利用モジュール管理」の保存ボタンを押下したユーザID
    private String moduleLastModifiedBy;

    // 「利用モジュール管理」の保存ボタンを押下時のタイムスタンプ
    private String moduleLastModifiedDate;

    // 「利用機能コントロール」の保存ボタンを押下したユーザID
    private String functionlastModifiedBy;

    // 「利用機能コントロール」の保存ボタンを押下時のタイムスタンプ
    private String functionlastModifiedDate;

    // 「手続き日程カレンダー管理」の保存ボタンを押下したユーザID
    private String limitLastModifiedBy;

    // 「手続き日程カレンダー管理」の保存ボタンを押下時のタイムスタンプ
    private String limitLastModifiedDate;

    // 「通知機能管理」の保存ボタンを押下したユーザID
    private String notificationLastModifiedBy;

    // 「通知機能管理」の保存ボタンを押下時のタイムスタンプ
    private String notificationLastModifiedDate;

    // 「プラットフォーム基本情報」の保存ボタンを押下したユーザID
    private String platformLastModifiedBy;

    // 「プラットフォーム基本情報」の保存ボタンを押下時のタイムスタンプ
    private String platformLastModifiedDate;

    // 「申立て登録項目管理」「申立て」の保存ボタンを押下したユーザID
    private String pfOptionPetitionsLastModifiedBy;

    // 「申立て登録項目管理」「申立て」の保存ボタンを押下時のタイムスタンプ
    private String pfOptionPetitionsLastModifiedDate;

    // 「申立て登録項目管理」「回答」の保存ボタンを押下したユーザID
    private String pfOptionRepliesLastModifiedBy;

    // 「申立て登録項目管理」「回答」の保存ボタンを押下時のタイムスタンプ
    private String pfOptionRepliesLastModifiedDate;

    // 「申立て登録項目管理」「反訴の回答」の保存ボタンを押下したユーザID
    private String pfOptionClaimRepliesLastModifiedBy;

    // 「申立て登録項目管理」「反訴の回答」の保存ボタンを押下時のタイムスタンプ
    private String pfOptionClaimRepliesLastModifiedDate;

    // 「申立て登録項目管理」「和解案」の保存ボタンを押下したユーザID
    private String pfOptionNegotiationsLastModifiedBy;

    // 「申立て登録項目管理」「和解案」の保存ボタンを押下時のタイムスタンプ
    private String pfOptionNegotiationsLastModifiedDate;

    // 「申立て登録項目管理」「調停案」の保存ボタンを押下したユーザID
    private String pfOptionMediationsLastModifiedBy;

    // 「申立て登録項目管理」「調停案」の保存ボタンを押下時のタイムスタンプ
    private String pfOptionMediationsLastModifiedDate;

}
