package com.web.app.domain;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;

/**
 * 和解案合意更新API
 * 
 * @author DUC jiawenzhi
 * @since 2024/05/09
 * @version 1.0
 */
@Data
public class ReconciliationUser implements Serializable {
    // 缓冲
    private static final long serialVersionUID = 1L;
    // 和解案id
    private String id;
    // 案件ID
    private String CaseId;
    // 和解案 内容
    private String HtmlContext;
    // 和解案合意書 内容
    private String HtmlContext2;
    // 合意日
    private Date AgreementDate;
    // 'システム日付
    private Date LastModifiedDate;
    // 'ログインユーザ
    private String LastModifiedBy;

}
