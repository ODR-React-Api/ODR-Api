package com.web.app.domain.InsRepliesTemp;

import lombok.Data;

@Data
public class CaseFileRelations {

    // ID
    public String id;

    // プラットフォームID
    public String PlatformId;

    // 案件ID
    public String CaseId;

    // 案件種類
    public String RelationType;

    // 案件種類ID
    public String RelatedId;

    // ファイルID
    public String fileId;

    // その他項目
    public String Other01;

    public String Other02;

    public String Other03;

    public String Other04;

    public String Other05;

    public Integer DeleteFlag;

    public String LastModifiedDate;

    public String LastModifiedBy;
}
