package com.web.app.domain.mediationsMake;

import java.io.Serializable;

import lombok.Data;

@Data
public class InsMediationsData implements Serializable{

    private static final long serialVersionUID = 1L;

    private String id;

    private String CaseId;

    private String PlatformId;

    public boolean DeleteFlag;

    public Integer RelationType;

    private String RelatedId;
}
