package com.web.app.domain;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;

import lombok.Data;

@ApiModel 
@Data
public class CasesByCid implements Serializable{
    private static final long serialVersionUID = 1L;

    private String CaseTitle;
}
