package com.web.app.domain.MosContentConfirm;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * case_extensionitem_values更新用
 * 
 * @author DUC 王魯興
 * @since 2024/05/29
 * @version 1.0
 */
@Data
public class UpdateOrInsertCaseExtensionitemValues implements Serializable {
    private static final long serialVersionUID = 1L;

    // ID
    private String id;

    // プラットフォームID
    private String platformId;

    // 案件ID
    private String caseId;

    // 申立てID
    private String casePetitionId;

    // 拡張項目ID
    private String extensionitemId;

    // 拡張項目値
    private String extensionitemValue;

    // 删除Flag
    private short deleteFlag;

    // LastModifiedDate
    private Date lastModifiedDate;

    // LastModifiedBy
    private String lastModifiedBy;
}
