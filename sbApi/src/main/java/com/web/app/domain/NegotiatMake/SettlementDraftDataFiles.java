package com.web.app.domain.NegotiatMake;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * テーブル「添付ファイル」が新規登録される
 * 
 * @author DUC 朱暁芳
 * @since 2024/04/23
 * @version 1.0
 */
@Data
public class SettlementDraftDataFiles implements Serializable {
    private static final long serialVersionUID = 1L;

    // ID
    private String id;
    // プラットフォームID
    private String PlatformId;
    // 案件ID
    private String CaseId;
    // ファイル名
    private String FileName;
    // 拡張子
    private String FileExtension;
    // URL
    private String FileUrl;
    // ストレージID
    private String FileBlobStorageId;
    // ファイルサイズ
    private Integer FileSize;
    // ユーザーID
    private String RegisterUserId;
    // 登録日
    private Date RegisterDate;

    private String Other01;

    private String Other02;

    private String Other03;

    private String Other04;

    private String Other05;

    private Integer DeleteFlag;

    private Date LastModifiedDate;

    private String LastModifiedBy;

}
