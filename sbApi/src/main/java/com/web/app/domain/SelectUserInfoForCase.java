package com.web.app.domain;

import lombok.Data;

@Data
public class SelectUserInfoForCase {
  private String caseId;
  private String petitionUserId;
}