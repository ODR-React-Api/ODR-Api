package com.web.app.domain;

import java.io.Serializable;

import lombok.Data;


@Data
public class Negotiation implements Serializable {
    //缓冲
    private static final long serialVersionUID = 1L;

    private String negotiationId;

    private int status;

    private String lastModifiedDate;
    
    private String loginUser;
}
