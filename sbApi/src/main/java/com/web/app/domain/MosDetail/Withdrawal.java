package com.web.app.domain.MosDetail;

import java.io.Serializable;

import lombok.Data;

@Data
public class Withdrawal implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String cid;

    private Integer caseStage;
}
