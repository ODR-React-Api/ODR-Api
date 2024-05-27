package com.web.app.domain.CouAnswerLogin;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import lombok.Data;

/**
 * 反訴回答登録画面 domain
 * 
 * @author DUC 信召艶
 * @since 2024/04/29
 * @version 1.0
 */
@ApiModel
@Data
public class RepliesContext implements Serializable {
    // バッファリング
    private static final long serialVersionUID = 1L;
    // 反訴内容
    private String CounterClaimContext;
    // ファイル名
    private String FileName;
    // URL
    private String FileUrl;
}
