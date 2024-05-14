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
    public String Uid;

    // プラットフォームID
    public String PlatformId;

    // パスワード
    public String password;

    // サイトナンバー
    public String RegisteredSiteNo;

    // メールアドレス
    public String Email;

    // 通知用メールアドレス
    public String NoticeEmail;

    // 名前
    public String FirstName;

    // ミドルネーム
    public String MiddleName;

    // 名字
    public String LastName;

    // 英語表示名
    public String FirstName_EN;

    // 英語表示ミドルネーム
    public String MiddleName_EN;

    // 英語表示姓
    public String LastName_EN;

    // 名前　カナ
    public String FirstName_kana;

    // ミドルネーム　カナ
    public String MiddleName_kana;

    // 名字　カナ
    public String LastName_kana;

    // 言語ID
    public String LanguageId;

    // 停止　ステータス
    public int Status;

    // タイムゾーン
    public String TimeZone;

    // メインテーマ
    public String ThemeId;

    // 規約確認状況
    public int TermsConfirmStatus;

    // 規約確認状況Version
    public int ConfirmedVersionNoOfTerms;

    // プライバシーポリシー確認Version
    public int ConfirmedVersionNoOfPolicy;

    public String MessageFrequency;

    // 規約確認状況
    public String TermsConfirmed;

    // 履歴書
    public String ResumeFileId;

    // 自己紹介
    public String SelfIntroduce;

    // 略歴
    public String HistoryInfo;

    // 専門分野
    public String Major;

    // 職位
    public String Position;

    // プロフィール画像
    public String ProfilePictureFileId;

    // ユーザ種類
    public int UserType;

    // 所属会社名
    public String CompanyName;

    // 所属会社名_英語表示
    public String CompanyName_en;

    // チュートリアル表示（申立）
    public Integer ShowTuritor1;

    // チュートリアル表示（回答）
    public Integer ShowTuritor2;

    // チュートリアル表示（調停）
    public Integer ShowTuritor3;

    // 最終ログイン日
    public String LastLoginDate;

    // 登録日
    public String RegisterDate;

    public String Other01;

    public String Other02;

    public String Other03;

    public String Other04;

    public String Other05;

    public Integer DeleteFlag;

    public String LastModifiedDate;

    public String LastModifiedBy;
}
