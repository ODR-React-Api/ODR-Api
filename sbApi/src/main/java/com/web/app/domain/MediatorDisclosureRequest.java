package com.web.app.domain;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
public class MediatorDisclosureRequest implements Serializable {
    private String UserId;
    private String PlatformId;
    private String CaseId;
    private Date LastModifiedDate;
    private String LanguageId;
    private int CaseStage;
    private int UserType;
    private static final long serialVersionUID = 1L;

}
