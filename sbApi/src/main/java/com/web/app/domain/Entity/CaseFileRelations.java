package com.web.app.domain.Entity;

import lombok.Data;

/**
 * 案件-添付ファイルリレーション
 * 
 * @author DUC 馮淑慧
 * @since 2024/05/10
 * @version 1.0
 */
@Data
public class CaseFileRelations {

    // ID
    public String id;

    // プラットフォームID
    public String PlatformId;

    // 案件ID
    public String CaseId;

    // 案件種類
    public Integer RelationType;

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
