package com.web.app.domain.NegotiatPreview;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class MasterTemplates implements Serializable {

    private static final long serialVersionUID = 1L;

    public int deleteFlag;
    public int templateType;
    public String languageId;
    public String id;
    public String platformId;
    public String templateId;
    public String startDate;
    public String templateName;
    public String context;
    public int usageCount;
    public int isActive;
    public String other01;
    public String other02;
    public String other03;
    public String other04;
    public String other05;
    public String lastModifiedDate;
    public String lastModifiedBy;
    private List<Integer> templateTypes;
    
}
