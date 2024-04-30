package com.web.app.domain.Entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class IdPetitionUserId implements Serializable {
    private static final long serialVersionUID = 1L;

    // Id
    private String id;

    // ユーザーId
    private String petitionUserId;
}
