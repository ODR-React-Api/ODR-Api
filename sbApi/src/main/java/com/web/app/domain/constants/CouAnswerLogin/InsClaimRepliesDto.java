package com.web.app.domain.constants.CouAnswerLogin;

import io.swagger.annotations.ApiModel;
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
public class InsClaimRepliesDto {
    // プラットフォームID
    private String PlatformId;
    // ケースID
    private String CaseId;
    // 反訴への回答
    private String replyContext;
    // LastModifiedBy
    private String LastModifiedBy;
    // ファイル名
    private String FileName;
    // 拡張子
    private String FileExtension;
    // URL
    private String FileUrl;
    // ファイルサイズ
    private Integer FileSize;
    // ユーザーID
    private String RegisterUserId;
}
