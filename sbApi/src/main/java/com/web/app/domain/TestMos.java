package com.web.app.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class TestMos implements Serializable {
    private static final long serialVersionUID = 1L;
    //案件ID
    private String caseId;
    //申立て人
    private String petitionUserId;
}
