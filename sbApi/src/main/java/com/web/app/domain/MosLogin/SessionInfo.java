package com.web.app.domain.MosLogin;

import java.io.Serializable;
import lombok.Data;

/**
 * セッション内容
 * 
 * @author DUC 馮淑慧
 * @since 2024/04/25
 * @version 1.0
 */
@Data
public class SessionInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    // セッション.ユーザID
    private String sessionId;

    // セッション.PlatformId
    private String platformId;

    // セッション.ログインユーザ
    private String loginUser;
}
