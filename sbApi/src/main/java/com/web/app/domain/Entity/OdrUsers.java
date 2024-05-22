
package com.web.app.domain.Entity;

import lombok.Data;

/**
 * ユーザ
 * 
 * @author DUC 耿浩哲
 * @since 2024/05/10
 * @version 1.0
 */
@Data
public class OdrUsers {

    // ID
    private String Uid;

    // プラットフォームID
    private String PlatformId;

    // パスワード
    private String password;

    // サイトナンバー
    private String RegisteredSiteNo;

    // メールアドレス
    private String Email;

    // 通知用メールアドレス
    private String NoticeEmail;

    // 名前
    private String FirstName;

    // ミドルネーム
    private String MiddleName;

    // 名字
    private String LastName;

    // 英語表示名
    private String FirstName_EN;

    // 英語表示ミドルネーム
    private String MiddleName_EN;

    // 英語表示姓
    private String LastName_EN;

    // 名前　カナ
    private String FirstName_kana;

    // ミドルネーム　カナ
    private String MiddleName_kana;

    // 名字　カナ
    private String LastName_kana;

    // 言語ID
    private String LanguageId;

    // 停止　ステータス
    private int Status;

    // タイムゾーン
    private String TimeZone;

    // メインテーマ
    private String ThemeId;

    // 規約確認状況
    private int TermsConfirmStatus;

    // 規約確認状況Version
    private int ConfirmedVersionNoOfTerms;

    // プライバシーポリシー確認Version
    private int ConfirmedVersionNoOfPolicy;

    private String MessageFrequency;

    // 規約確認状況
    private int TermsConfirmed;

    // 履歴書
    private String ResumeFileId;

    // 自己紹介
    private String SelfIntroduce;

    // 略歴
    private String HistoryInfo;

    // 専門分野
    private String Major;

    // 職位
    private String Position;

    // プロフィール画像
    private String ProfilePictureFileId;

    // ユーザ種類
    private int UserType;

    // 所属会社名
    private String CompanyName;

    // 所属会社名_英語表示
    private String CompanyName_en;

    // チュートリアル表示（申立）
    private Integer ShowTuritor1;

    // チュートリアル表示（回答）
    private Integer ShowTuritor2;

    // チュートリアル表示（調停）
    private Integer ShowTuritor3;

    // 最終ログイン日
    private String LastLoginDate;

    // 登録日
    private String RegisterDate;


    public String Other01;

    public String Other02;

    public String Other03;

    public String Other04;

    public String Other05;

    private Integer DeleteFlag;

    private String LastModifiedDate;

    private String LastModifiedBy;
}
