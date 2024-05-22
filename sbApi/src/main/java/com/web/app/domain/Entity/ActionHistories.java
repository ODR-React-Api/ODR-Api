package com.web.app.domain.Entity;

import java.util.Date;

import lombok.Data;

@Data
public class ActionHistories {

    // ID
    private String id;

    // プラットフォームID
    private String PlatformId;

    // 案件ID
    private String CaseId;

    // アクション区分
    private String ActionType;

    // 案件ステージ
    private Integer CaseStage;

    // ユーザーID
    private String UserId;

    // 立場
    private Integer UserType;

    // 日付
    private Date ActionDateTime;

    // グループID
    private String MessageGroupId;

    // メッセージID
    private String MessageId;

    // ファイル フラグ
    private Boolean HaveFile;

    private String Parameters;

    private String Other01;

    private String Other02;

    private String Other03;

    private String Other04;

    private String Other05;

    private Boolean DeleteFlag;

    private Date LastModifiedDate;

    private String LastModifiedBy;
}