package com.web.app.domain;

import java.sql.Date;

import lombok.Data;


@Data
public class UpdNegotiatCon {
    //缓冲
    private static final long serialVersionUID = 1L;

    private String negotiationId;

    private int status;

    private String lastModifiedDate;
    
    private String loginUser;
}
