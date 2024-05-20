package com.web.app.domain.MedDateExtension;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * S29案件情報更新更新API domain
 * 
 * @author DUC 王 エンエン
 * @since 2024/05/20
 * @version 1.0
 */
@Data
public class CasesForMediationEndDate implements Serializable {

    // 缓冲
    private static final long serialVersionUID = 1L;

    // 案件id
    private String cid;

    // 案件ステージ
    private Integer caseStage;

    // 案件ステータス
    private String caseStatus;

    // 調停期限日
    private Date mediationEndDate;

    // 調停期限変更回数
    private Integer mediationEndDateChangeCount;

}
