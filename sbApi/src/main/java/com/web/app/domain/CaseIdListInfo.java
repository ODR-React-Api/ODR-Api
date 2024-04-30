package com.web.app.domain;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class CaseIdListInfo implements Serializable {
    private String CaseId;
    private String PetitionUserId;
    private Integer flag;
    private String userId;
}
