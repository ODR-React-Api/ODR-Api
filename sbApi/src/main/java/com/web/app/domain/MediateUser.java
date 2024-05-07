package com.web.app.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class MediateUser implements Serializable{

    private static final long serialVersionUID = 1L;

    private String id;

    private String CaseId;

    private int Status;

    private String MediatorUserEmail;

    private String Uid;

    private String userId;
}
