package com.web.app.domain.Entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel // 声明当前对象是用来封装数据的对象
@Data
public class ActionHistories implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String PlatformId;

    private String CaseId;

    private String ActionType;

    private Integer CaseStage;

    private String UserId;

    private Integer UserType;

    private Date ActionDateTime;

    private String MessageGroupId;

    private String MessageId;

    private Boolean HaveFile;

    private String Parameters;

    private String Other01;

    private String Other02;

    private String Other03;

    private String Other04;

    private String Other05;

    private Boolean DeleteFlag;

    private Date LastModifiedDate;

    private String LastModifiedBy;
}
