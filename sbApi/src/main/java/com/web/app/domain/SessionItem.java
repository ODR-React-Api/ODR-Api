package com.web.app.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class SessionItem implements Serializable {

    private static final long serialVersionUID = 1L;

    public int flag;
    public String id;
    public String filesId;
    public String platformId;
    public String caseId;

}
