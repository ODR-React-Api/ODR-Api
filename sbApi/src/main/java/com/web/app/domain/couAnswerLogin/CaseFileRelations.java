package com.web.app.domain.CouAnswerLogin;

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
public class CaseFileRelations implements Serializable {
    // バッファリング
    private static final long serialVersionUID = 1L;
    // プラットフォームID
    private String PlatformId;
    // ケースID
    private String CaseId;
    // 案件種類ID
    private String RelatedId;
    // ファイルID
    private String fileId;
    // LastModifiedBy
    private String LastModifiedBy;
}
