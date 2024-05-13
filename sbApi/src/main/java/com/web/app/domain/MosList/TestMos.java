package com.web.app.domain.MosList;

import java.io.Serializable;

import lombok.Data;

@Data
public class TestMos implements Serializable {
    private static final long serialVersionUID = 1L;

    // 案件ID
    private String CaseId;

    // 申立て人
    private String PetitionUserId;
}
