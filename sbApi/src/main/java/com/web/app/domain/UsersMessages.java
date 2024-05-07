package com.web.app.domain;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
public class UsersMessages implements Serializable {

  private String id;

  private String platformId;

  private String caseId;

  private String messageGroupId;

  private String messageId;

  private String userId;

  private Integer readFlag;

  private String other01;

  private String other02;

  private String other03;

  private String other04;

  private String other05;

  private Integer deleteFlag;

  private Data lastModifiedDate;

  private String lastModifiedBy;

}

