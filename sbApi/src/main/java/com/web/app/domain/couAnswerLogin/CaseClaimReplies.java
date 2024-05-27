package com.web.app.domain.couAnswerLogin;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import lombok.Data;

/**
 * S14_反訴回答登録画面
 * 
 * @author DUC 信召艶
 * @since 2024/05/13
 * @version 1.0
 */
@ApiModel
@Data
public class CaseClaimReplies implements Serializable {
    // バッファリング
    private static final long serialVersionUID = 1L;
    // ID
    private String id;
    // プラットフォームID
    private String PlatformId;
    // 案件ID
    private String CaseId;
    // 反訴への回答
    private String replyContext;
    // LastModifiedBy
    private String LastModifiedBy;
}


