package com.web.app.domain.Entity;

import java.util.Date;

import lombok.Data;

@Data
public class ActionFileRelations {

    // ID
    private String Id;
    // プラットフォームID
    private String PlatformId;
    // 案件ID
    private String CaseId;
    // アクション履歴ID
    private String ActionHistoryId;
    // ファイルID
    private String FileId;

    private String Other01;

    private String Other02;

    private String Other03;

    private String Other04;

    private String Other05;

    private boolean DeleteFlag;

    private Date LastModifiedDate;

    private String LastModifiedBy;
}