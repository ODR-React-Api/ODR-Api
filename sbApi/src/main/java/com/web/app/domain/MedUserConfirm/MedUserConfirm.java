package com.web.app.domain.MedUserConfirm;

import java.io.Serializable;

import lombok.Data;

@Data
public class MedUserConfirm implements Serializable{
    private static final long serialVersionUID = 1L;
    
    //添付ファイルid
    private String FileId;

    //案件ID
    private String CaseId;
}
