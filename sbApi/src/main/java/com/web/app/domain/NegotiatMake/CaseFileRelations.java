package com.web.app.domain.NegotiatMake;


import java.io.Serializable;

import lombok.Data;

@Data
public class CaseFileRelations implements Serializable {

    private static final long serialVersionUID = 1L;

    public String id;
    public String platformId;
    public String caseId;
    
    public String other01;
    public String other02;
    public String other03;
    public String other04;
    public String other05;
    public int deleteFlag;
    public String lastModifiedDate;
    public String lastModifiedBy;

    public int relationType;
    public String relatedId;
    public String fileId;
    
}
