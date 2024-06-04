package com.web.app.domain.MosDetail;

import java.io.Serializable;
import java.util.List;
import com.web.app.domain.Entity.Files;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * S4 申立て詳細画面
 * API_GetCaseRepliesMosDetail
 * Dao層
 * CaseRepliesMosDetail
 * 
 * @author DUC 楊バイバイ
 * @since 2024/05/01
 * @version 1.0
 */
@ApiModel
@Data
public class CaseRepliesMosDetail implements Serializable {
    // ID
    private String CaseId;
    // ステータス
    private Integer Status;

    private Integer DeleteFlag;
    // 申立種類、希望する解決方法種類、回答種類、対応方法の種類など
    private String replyType;
    // 内容
    private String replyContext;
    // 反訴有無
    private Boolean HaveCounterClaim;
    // 反訴内容
    private String CounterClaimContext;
    
    private String LastModifiedDate;

    // 添付資料リスト
    private List<Files> file;

    private Integer draftFlg;

    private static final long serialVersionUID = 1L;
}
