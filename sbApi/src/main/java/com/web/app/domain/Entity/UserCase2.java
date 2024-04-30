package com.web.app.domain.Entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserCase2 implements Serializable {
    private static final long serialVersionUID = 1L;

    // ケースId
    private String languageId;

    // ユーザーId
    private String petitionUserId;
}
