package com.web.app.domain.MedUserChange;

import java.util.Date;

import lombok.Data;

@Data
public class InsertFileInfo {

    private String fileId;

    private String caseId;

    private String fileName;

    private String fileExtension;

    private String fileUrl;

    private int fileSize;

    private String registerUserId;

    private Date registerDate;

    private String caseFileRelationsId;

    private int relationType;

    private String relatedId;

}
