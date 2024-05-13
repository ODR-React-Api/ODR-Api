package com.web.app.domain.medUserConfirm;

import java.io.Serializable;

import lombok.Data;

@Data
public class GetUserIDbyMail implements Serializable{

    private static final long serialVersionUID = 1L;

    private String MediatorUserEmail;

    private String Uid;

}
