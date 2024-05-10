package com.web.app.domain.Entity;

import lombok.Data;

@Data
public class CaseExtensionitemValues {

    // ID
    public String id;

    // プラットフォームID
    public String PlatformId;

    // 案件ID
    public String CaseId;

    // 申立てID
    public String case_petitionId;

    // 拡張項目ID
    public String ExtensionitemId;

    // 拡張項目値
    public String ExtensionitemValue;

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
