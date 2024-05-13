package com.web.app.domain.NegotiatMake;

import java.io.Serializable;

import lombok.Data;

@Data
public class Files implements Serializable {

    private static final long serialVersionUID = 1L;

    public String id;
    public String platformId;
    public String caseId;
    public String fileName;
    public String fileExtension;
    public String fileUrl;
    public String fileBlobStorageId;
    public int fileSize;
    public String registerUserId;
    public String registerDate;
    public String other01;
    public String other02;
    public String other03;
    public String other04;
    public String other05;
    public int deleteFlag;
    public String lastModifiedDate;
    public String lastModifiedBy;

}
