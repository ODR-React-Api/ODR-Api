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
    public String Id;

    // プラットフォーム名
    public String PlatformName;

    // ロゴ
    public String Logo;

    //ロゴ小
    public String Logo_Small;

    // タイムゾーン
    public String TimeZone;

    // 通貨
    public String Currency;

    // 通貨記号
    public String CurrencyMark;

    // ヒートアップ閾値
    public double HeatUpWeight;

    // 期日リマインド - 申立に対する回答（相手方）
    public int RemindTiming_reply;

    // 期日リマインド - 反訴に対する回答（申立人）
    public int RemindTiming_claimreply;

    // 期日リマインド - 交渉 
    public int RemindTiming_negotiation;

    // 期日リマインド - 交渉延長ある場合
    public int RemindTiming_ex_neg;

    // 期日リマインド - 調停 
    public int RemindTiming_mediation;

    // 期日リマインド - 調停延長ある場合
    public int RemindTiming_ex_med;

    // メッセージメール頻度
    public int InfoFrequency;

    // 作業日のみ
    public Integer WorkDayBase;

    // 相手への非通知設定
    public Integer PetitionNoticeMode;

    // 通知用メールアドレス
    public String PetitionNoticeEmail;

    // 申立回答期限
    public int ReplyLimitDays;

    // 反訴期限日数
    public int CounterclaimLimitDays;

    // 交渉期限日数
    public int NegotiationLimitDays;

    // 交渉期限延長可能日数
    public int NegotiationExtendDays;

    // 調停期限日数
    public int MediationLimitDays;

    // 調停期限延長可能日数
    public int MediationExtentdDays;

    // 商品IDの利用設定
    public int UserProductId;

    // 販売者名利用設定
    public int UseTraderName;

    // 販売元URL利用設定
    public int UseProductUrl;

    // 拡張項目利用設定
    public int UseOther;

    // 回答タブ拡張項目利用設定
    public int UseRepliesOther;

    // 反訴タブ拡張項目利用設定
    public int UseClaimRepliesOther;

    // 和解タブ拡張項目利用設定
    public int UseNegotiationsOther;

    // 調停タブ拡張項目利用設定
    public int UseMediationsOther;

    // デフォルト販売者Email自動設定
    public Integer UseDefaultTraderEmail;

    // デフォルト販売者Email
    public String DefaultTraderEmail;

    // メッセージ更新頻度
    public int MessageUpdateFrequency;

    public String languageId;

    public Integer ShowMiddleName;

    // 調停人指名方法
    public int MediatorAssignType;

    // 調停人との個別やりとり
    public Integer MediatorPrivateMessage;

    // 調停人との個別やりとり付加方法
    public Integer MediatorPrivateMessageAddMode;

    // 調停人の交渉フェーズ情報閲覧制限
    public Integer MediatorInfoLimit;

    // 情報閲覧制限できる期間
    public int MediatorInfoLimitConfirmDays;

    // 交渉機能利用有無
    public Integer Phase_negotiation;

    // 調停機能利用有無
    public Integer Phase_mediation;

    // 仲裁機能利用有無
    public Integer Phase_arbitration;

    // 反訴機能利用有無
    public Integer Phase_reply;

    // サービスサイトURL
    public String ServiceSiteUrl;

    // 申立内容-添付ファイル
    public int PetitionsContexFile;

    // 回答の内容-添付ファイル
    public int RepliesContextFile;

    // 回答画面_反訴の回答内容－添付ファイル
    public int ClaimRepliesContextFileR;

    // 反訴画面_反訴の回答内容－添付ファイル
    public int ClaimRepliesContextFileC;

    // 和解案内容-添付ファイル
    public int NegotiationsContextFile;

    // 調停案内容-添付ファイル
    public int MediationsContextFile;

    public String Other01;

    public String Other02;

    public String Other03;

    public String Other04;

    public String Other05;

    public Integer DeleteFlag;

    public String LastModifiedDate;

    public String LastModifiedBy;

    // 「利用モジュール管理」の保存ボタンを押下したユーザID
    public String moduleLastModifiedBy;

    // 「利用モジュール管理」の保存ボタンを押下時のタイムスタンプ
    public String moduleLastModifiedDate;

    // 「利用機能コントロール」の保存ボタンを押下したユーザID
    public String functionlastModifiedBy;

    // 「利用機能コントロール」の保存ボタンを押下時のタイムスタンプ
    public String functionlastModifiedDate;

    // 「手続き日程カレンダー管理」の保存ボタンを押下したユーザID
    public String limitLastModifiedBy;

    // 「手続き日程カレンダー管理」の保存ボタンを押下時のタイムスタンプ
    public String limitLastModifiedDate;

    // 「通知機能管理」の保存ボタンを押下したユーザID
    public String notificationLastModifiedBy;

    // 「通知機能管理」の保存ボタンを押下時のタイムスタンプ
    public String notificationLastModifiedDate;

    // 「プラットフォーム基本情報」の保存ボタンを押下したユーザID
    public String platformLastModifiedBy;

    // 「プラットフォーム基本情報」の保存ボタンを押下時のタイムスタンプ
    public String platformLastModifiedDate;

    // 「申立て登録項目管理」「申立て」の保存ボタンを押下したユーザID
    public String pfOptionPetitionsLastModifiedBy;

    // 「申立て登録項目管理」「申立て」の保存ボタンを押下時のタイムスタンプ
    public String pfOptionPetitionsLastModifiedDate;

    // 「申立て登録項目管理」「回答」の保存ボタンを押下したユーザID
    public String pfOptionRepliesLastModifiedBy;

    // 「申立て登録項目管理」「回答」の保存ボタンを押下時のタイムスタンプ
    public String pfOptionRepliesLastModifiedDate;

    // 「申立て登録項目管理」「反訴の回答」の保存ボタンを押下したユーザID
    public String pfOptionClaimRepliesLastModifiedBy;

    // 「申立て登録項目管理」「反訴の回答」の保存ボタンを押下時のタイムスタンプ
    public String pfOptionClaimRepliesLastModifiedDate;

    // 「申立て登録項目管理」「和解案」の保存ボタンを押下したユーザID
    public String pfOptionNegotiationsLastModifiedBy;

    // 「申立て登録項目管理」「和解案」の保存ボタンを押下時のタイムスタンプ
    public String pfOptionNegotiationsLastModifiedDate;

    // 「申立て登録項目管理」「調停案」の保存ボタンを押下したユーザID
    public String pfOptionMediationsLastModifiedBy;

    // 「申立て登録項目管理」「調停案」の保存ボタンを押下時のタイムスタンプ
    public String pfOptionMediationsLastModifiedDate;

}
