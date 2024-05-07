package com.web.app.domain;

import lombok.Data;

@Data
public class WithdrawalReturn {
  private Integer updateFlag;

  private CaseRelations caseRelations;
}
