package com.web.app.domain.Entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserCase implements Serializable {
    private static final long serialVersionUID = 1L;

    // ケースId
    private String caseId;

    // ユーザーId
    private String petitionUserId;
}
