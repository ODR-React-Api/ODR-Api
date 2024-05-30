package com.web.app.domain.Entity;

import java.io.Serializable;
import lombok.Data;

/**
 * CaseClaimReplies
 * テーブル名：case_claimreplies反訴への回答
 * 
 * @author DUC 張明慧
 * @since 2024/05/29
 * @version 1.0
 */
@Data
public class CaseClaimReplies implements Serializable {
    // ID
    private String id;

    // プラットフォームID
    private String PlatformId;

    // 案件ID
    private String CaseId;

    // ステータス
    private Integer Status;

    // 反訴への回答
    private String replyContext;

    // その他1
    public String Other01;

    // その他2
    public String Other02;

    // その他3
    public String Other03;

    // その他4
    public String Other04;

    // その他5
    public String Other05;

    // 削除Flag
    public Integer DeleteFlag;

    // 最終変更日
    public String LastModifiedDate;

    // 最終変更者
    public String LastModifiedBy;

    private static final long serialVersionUID = 1L;

    public void setCaseId(String caseId2) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'setCaseId'");
    }

    public void setPlatformId(String platformId2) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'setPlatformId'");
    }

    public void setReplyContext(String replyContext2) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'setReplyContext'");
    }

    public void setLastModifiedBy(String lastModifiedBy2) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'setLastModifiedBy'");
    }
}