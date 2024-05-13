package com.web.app.domain.medUserConfirm;

import java.io.Serializable;

import lombok.Data;

@Data
public class GetMediatorInfo implements Serializable{

    private static final long serialVersionUID = 1L;

    private int Status;

    private String userId;
}
