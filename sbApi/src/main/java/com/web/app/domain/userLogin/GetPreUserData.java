package com.web.app.domain.userLogin;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

import lombok.Data;

/**
 * API_仮会員登録データ取得 domain
 * 
 * @author DUC 信召艶
 * @since 2024/04/18
 * @version 1.0
 */
@ApiModel
@Data
public class GetPreUserData implements Serializable {
    // バッファリング
    private static final long serialVersionUID = 1L;
    // Eメール
    private String email;
}
