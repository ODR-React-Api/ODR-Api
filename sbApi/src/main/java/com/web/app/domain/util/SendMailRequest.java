package com.web.app.domain.util;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class SendMailRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    // 平台Id
    private String PlatformId;

    // 语言ID
    private String LanguageId;

    // 邮件ID
    private String TempId;

    // 案件ID
    private String CaseId;

    // 收信人邮箱List
    private List<String> RecipientEmail;

    // 邮件模板对应值
    private List<String> Parameter;

    // 送信人ID
    private String UserId;

    private int ControlType;

    
}
