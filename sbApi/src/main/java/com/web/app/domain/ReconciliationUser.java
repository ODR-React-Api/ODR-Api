package com.web.app.domain;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;

@Data
public class ReconciliationUser implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private int Status;

    private String HtmlContext;

    private String HtmlContext2;

    private Date AgreementDate;

    private Date LastModifiedDate;

    private String LastModifiedBy;
}
