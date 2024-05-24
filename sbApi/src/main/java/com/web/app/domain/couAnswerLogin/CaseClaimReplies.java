package com.web.app.domain.couAnswerLogin;

import java.io.Serializable;
import lombok.Data;

@Data
public class CaseClaimReplies implements Serializable {
      // バッファリング
      private static final long serialVersionUID = 1L;
      // ID
      private String id;
      // プラットフォームID
      private String PlatformId;
      // 案件ID
      private String CaseId;
      // 反訴への回答
      private String replyContext;
      // LastModifiedBy
      private String LastModifiedBy;
}


