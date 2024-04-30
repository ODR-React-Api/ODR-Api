package com.web.app.domain.Entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class InsertCaseFileRelations implements Serializable {
    private static final long serialVersionUID = 1L;

    // ID
    private String id;

    // プラットフォームID
    private String platformId;

    // 案件ID
    private String caseId;

    // 案件種類
    private int relationType;

    // 案件種類ID
    private String relatedId;

    // ファイルID
    private String fileId;

    // 删除Flag
    private short deleteFlag;

    // 上次修改日期
    private Date lastModifiedDate;

    // 上次修改者
    private String lastModifiedBy;
}
