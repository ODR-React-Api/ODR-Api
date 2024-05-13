package com.web.app.domain.MosList;

import java.io.Serializable;

import lombok.Data;

/**
 * 相手方、申立人、調停人のケース内容
 * 
 * @author DUC 馮淑慧
 * @since 2024/04/18
 * @version 1.0
 */
@Data
public class TestMos implements Serializable {
    private static final long serialVersionUID = 1L;

    // 案件ID
    private String CaseId;

    // 申立て人
    private String PetitionUserId;
}
