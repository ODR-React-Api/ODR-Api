package com.web.app.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class InsertFilesData implements Serializable{
    private static final long serialVersionUID = 1L;

    private String id;

    private String PlatformId;

    private String CaseId;

    private String FileName;

    private String FileExtension;

    private String FileUrl;

    private String FileBlobStorageId;

    private int FileSize;

    private String RegisterUserId;

    private String RegisterDate;

    private String Other01;

    private String Other02;

    private String Other03;

    private String Other04;

    private String Other05;

    private String DeleteFlag;

    private String LastModifiedDate;

    private String LastModifiedBy;
}
