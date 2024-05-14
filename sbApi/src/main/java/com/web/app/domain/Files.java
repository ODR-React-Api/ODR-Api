package com.web.app.domain;
import java.io.Serializable;
import lombok.Data;
@Data
public class Files implements Serializable {
  private static final long serialVersionUID = 1L;
      private String PlatformId;
      private String CaseId;
      private String FileName;
      private String FileExtension;
      private String FileUrl;
      private Integer FileSize;
      private String RegisterUserId;
}

