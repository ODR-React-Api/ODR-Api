package com.web.app.domain.Entity;

import lombok.Data;

/**
 * 案件-添付ファイルリレーション
 * 
 * @author DUC 賈文志
 * @since 2024/05/10
 * @version 1.0
 */
@Data
public class CaseFileRelations {

    // ID
    private String id;

    // プラットフォームID
    private String PlatformId;

    // 案件ID
    private String CaseId;

    // 案件種類
    private Integer RelationType;

    // 案件種類ID
    private String RelatedId;

    // ファイルID
    private String fileId;

    // その他項目
    private String Other01;

    private String Other02;

    private String Other03;

    private String Other04;

    private String Other05;

    private Integer DeleteFlag;

    private String LastModifiedDate;

    private String LastModifiedBy;
}