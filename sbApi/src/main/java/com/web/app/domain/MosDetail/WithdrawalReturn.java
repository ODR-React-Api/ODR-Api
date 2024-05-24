package com.web.app.domain.MosDetail;

import java.io.Serializable;

import com.web.app.domain.Entity.CaseRelations;

import lombok.Data;

@Data
public class WithdrawalReturn implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer updateFlag;

    private CaseRelations caseRelations;
}
