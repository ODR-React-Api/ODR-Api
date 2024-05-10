package com.web.app.domain.updNegotiatDeny;

import java.io.Serializable;

import lombok.Data;

/**
 * 和解案拒否更新API domain
 * 
 * @author DUC 徐義然
 * @since 2024/05/06
 * @version 1.0
 */
@Data
public class Negotiation implements Serializable {
    //バッファリング
    private static final long serialVersionUID = 1L;
    //和解案id
    private String negotiationId;
    //和解案ステータス
    private int status;
    //システム日付
    private String lastModifiedDate;
    //ログインユーザ
    private String loginUser;
}
