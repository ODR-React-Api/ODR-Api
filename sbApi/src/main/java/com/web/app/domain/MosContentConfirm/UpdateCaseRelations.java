package com.web.app.domain.MosContentConfirm;
import java.io.Serializable;
import lombok.Data;

@Data
public class UpdateCaseRelations implements Serializable {
    private static final long serialVersionUID = 1L;

    // ID
    private String id;

    // 案件ID
    private String caseId;

    // プラットフォームID
    private String platformId;

    // 申立て人入力情報
    private String petitionUserInfoEmail;

    // 代理人1
    private String agent1Email;

    // 代理人2
    private String agent2Email;

    // 代理人3
    private String agent3Email;

    // 代理人4
    private String agent4Email;

    // 代理人5
    private String agent5Email;

    // 相手方メール
    private String traderUserEmail;

    // 申立Id
    private  String casePetitions;

    // 申立て人
    private String petitionUserId;
}
