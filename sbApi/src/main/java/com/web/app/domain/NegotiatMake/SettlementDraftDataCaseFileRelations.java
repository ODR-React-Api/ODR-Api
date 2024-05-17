package com.web.app.domain.NegotiatMake;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 「案件-添付ファイルリレーション」が新規登録される
 * 
 * @author DUC 朱暁芳
 * @since 2024/04/23
 * @version 1.0
 */
@Data
public class SettlementDraftDataCaseFileRelations implements Serializable {
    private static final long serialVersionUID = 1L;

    // ID
    private String id;
    // プラットフォームID
    private String PlatformId;
    // ケース
    private String CaseId;
    // 案件種類
    private Integer RelationType;
    // 案件種類ID
    private String RelatedId;
    // ファイルID
    private String fileId;

    private String Other01;

    private String Other02;

    private String Other03;

    private String Other04;

    private String Other05;

    private Integer DeleteFlag;

    private Date LastModifiedDate;

    private String LastModifiedBy;

}
