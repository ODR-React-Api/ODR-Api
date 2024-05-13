package com.web.app.domain.UpdNegotiatAgree;

import java.io.Serializable;
import lombok.Data;

/**
 * 和解案合意更新API
 * 
 * @author DUC 賈文志
 * @since 2024/05/13
 * @version 1.0
 */
@Data
public class ReconciliationUser implements Serializable {
    // 缓冲
    private static final long serialVersionUID = 1L;
    // 和解案id
    private String id;
    // 和解案 内容
    private String HtmlContext;
    // 和解案合意書 内容
    private String HtmlContext2;
    // 合意日
    private String AgreementDate;
    // 'システム日付
    private String LastModifiedDate;
    // 'ログインユーザ
    private String LastModifiedBy;

}
