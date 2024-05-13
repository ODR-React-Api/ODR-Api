package com.web.app.domain.NegotiatMake;

import java.io.Serializable;

import lombok.Data;

@Data
public class SessionItems implements Serializable {

    private static final long serialVersionUID = 1L;

    public int flag;
    public String id;
    public String filesId;
    public String platformId;
    public String caseId;

}
