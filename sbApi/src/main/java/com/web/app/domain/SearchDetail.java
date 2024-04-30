package com.web.app.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class SearchDetail implements Serializable{
  private String cid;

  private Integer caseStage;

  private String caseStatus;

  private String caseTitle;

  private Date petitonDate;

  private Date replyEndDate;

  private Date conuterclaimEndDate;
  
  private Date negotiationEndDate;

  private Date mediationEndDate;

  private Integer negotiationEndDateChangeStatus;

  private Integer status;

  private Integer groupMessageFlag1;

  private Integer groupMessageFlag2;

  private Integer mediationsStatus;
}
