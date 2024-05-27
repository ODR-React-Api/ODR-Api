package com.web.app.config.AnswerLogin;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import lombok.Data;

/**
 * S11_回答登録画面
 * 
 * @author DUC 信召艶
 * @since 2024/04/25
 * @version 1.0
 */
@ApiModel
@Data
public class RepliesData implements Serializable {
    // バッファリング
    private static final long serialVersionUID = 1L;
    // 申立種類、希望する解決方法種類、回答種類、対応方法の種類など
    private String replyType;
    // 内容
    private String replyContext;
    // 反訴有無
    private String HaveCounterClaim;
    // 反訴内容
    private String CounterClaimContext;
    // 相手方代理人1
    private String TraderAgent1_UserEmail;
    // 相手方代理人2
    private String TraderAgent2_UserEmail;
    // 相手方代理人3
    private String TraderAgent3_UserEmail;
    // 相手方代理人4
    private String TraderAgent4_UserEmail;
    // 相手方代理人5
    private String TraderAgent5_UserEmail;
    // ファイル名
    private String FileName;
    // ファイルURL
    private String FileUrl;
}
