package com.web.app.domain;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel
@Data
public class Casecase implements Serializable {

    // 案件ID
    private String cid;
    // プラットフォームID
    private String platformId;
    // 案件ステージ
    public Integer caseStage;
    // 案件ステータス
    public String caseStatus;
    // 案件ステータス
    private Boolean deleteFlag;
    // 相手方メール
    private String traderUserEmail;
    // frontから相手方代理人 mailaddressのlist
    public List<String> traderagentuserList;
    // frontからmailaddress---五つまで補足後のlist
    public List<String> traderagentuserListAll;
    // 反訴機能
    public Boolean phaseReply;
    // 交渉機能
    public Boolean phaseNegotiation;
    // 反訴の有無
    public Integer counterclaimFlag;
    // 反訴の回答開始日
    private String counterclaimStartDate;
    // 反訴の回答期限日
    private String counterclaimEndDate;
    // 交渉開始日
    private String negotiationStartDate;
    // 交渉期限日
    private String negotiationEndDate;
    // 交渉期限日変更ステータス
    private String negotiationEndDateChangeStatus;
    // 交渉期限日変更回数
    private String negotiationEndDateChangeCount;
    // 手続き中止日
    private String cancelDate;

    private String lastModifiedDate;

    private String lastModifiedBy;
    // システム時間
    public String newDate;

}
