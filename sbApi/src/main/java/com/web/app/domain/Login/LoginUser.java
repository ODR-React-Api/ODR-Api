package com.web.app.domain.Login;

import java.io.Serializable;
import lombok.Data;

/**
 * API_申立データ取得用内容
 * 
 * @author DUC 馮淑慧
 * @since 2024/05/17
 * @version 1.0
 */
@Data
public class LoginUser implements Serializable {
    private static final long serialVersionUID = 1L;

    // 画面項目.メールアドレス
    private String email;

    // 画面項目.パスワード
    private String passWord;

    // セッション.platformId
    private String platformId;

    // ログインユーザーId
    private String userId;

    // ログインユーザー名
    private String userName;

    // ログインEmail
    private String userEmail;
}
