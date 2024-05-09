package com.web.app.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class DraftSavingReturn implements Serializable {
    private static final long serialVersionUID = 1L;

    private DraftSavingDate draftSavingDate;
    private int draftSavingFlag;
}