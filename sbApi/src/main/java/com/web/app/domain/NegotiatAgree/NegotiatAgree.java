package com.web.app.domain.NegotiatAgree;

import java.io.Serializable;

import lombok.Data;

@Data
public class NegotiatAgree implements Serializable{

    private static final long serialVersionUID = 1L;

    //案件ID
    private String CaseId;

    //DeleteFlag
    private String DeleteFlag;
}
