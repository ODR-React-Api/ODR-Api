package com.web.app.domain;

import lombok.Data;

@Data
public class DraftSavingReturn {
  private DraftSavingDate draftSavingDate;
  private int draftSavingFlag;
}
