package com.web.app.domain.Entity;

import lombok.Data;

/**
 * 拡張項目設定値
 * 
 * @author DUC 馮淑慧
 * @since 2024/05/10
 * @version 1.0
 */
@Data
public class CaseExtensionitemValues {

    // ID
    private String id;

    // プラットフォームID
    private String PlatformId;

    // 案件ID
    private String CaseId;

    // 申立てID
    private String case_petitionId;

    // 拡張項目ID
    private String ExtensionitemId;

    // 拡張項目値
    private String ExtensionitemValue;

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
