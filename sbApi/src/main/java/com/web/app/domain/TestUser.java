package com.web.app.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class TestUser implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userId;

    private String password;

    private String userName;

    private String userAge;

    private int Status = 0;
}
