package com.web.app.domain.MosList;

import java.sql.Date;

import lombok.Data;

@Data
public class CaseInfoUtil {

    private static final long serialVersionUID = 1L;

    String cid;

    String caseTitle;

    Date petitionDate;

    String caseStatus;

    Date backDate;

    //未读消息条数
    int notReadedCnt;
    
    // 是否需要处理
    int flag;    
}
