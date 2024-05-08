package com.web.app.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class Mediationcase implements Serializable{

    private static final long serialVersionUID = 1L;

    private String id;

    private String CaseId;

    private String PlatformId;

    public boolean DeleteFlag;

    public Integer RelationType;

    private String RelatedId;
}
