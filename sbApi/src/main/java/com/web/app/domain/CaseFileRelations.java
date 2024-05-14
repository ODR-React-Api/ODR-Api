package com.web.app.domain;
import java.io.Serializable;
import lombok.Data;
@Data
public class CaseFileRelations implements Serializable {
  private static final long serialVersionUID = 1L;
      private String PlatformId;
      private String CaseId;
      private String RelatedId;
      private String fileId;
      private String LastModifiedBy;
}
