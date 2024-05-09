package com.web.app.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class WithdrawalReturn implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer updateFlag;

    private CaseRelations caseRelations;
}
