package com.web.app.domain;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel 
@Data
public class QuestionnaireData implements Serializable {

  // アンケートID
  private String QuestionId;

  // 送信対象ユーザEmail
  private String UserEmail;

  // ユーザ立場
  private Integer UserType;

  // 案件ID
  private String CaseId;

}