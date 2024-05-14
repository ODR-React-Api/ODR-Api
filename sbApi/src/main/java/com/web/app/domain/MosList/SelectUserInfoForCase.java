package com.web.app.domain.MosList;

import java.io.Serializable;

import lombok.Data;

@Data
public class SelectUserInfoForCase implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String caseId;
    
    private String petitionUserId;
}