package com.web.app.domain.AnswerLoginConfirm;

import java.io.Serializable;
import java.util.List;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * S12 回答内容確認画面
 * API_案件更新
 * Dao層
 * UpdCases
 * 
 * @author DUC 楊バイバイ
 * @since 2024/05/01
 * @version 1.0
 */
@ApiModel
@Data
public class UpdCases implements Serializable {

    // 案件ID
    private String cid;
    // プラットフォームID
    private String platformId;
    // 案件ステージ
    private Integer caseStage;
    // 案件ステータス
    private String caseStatus;
    // 案件ステータス
    private Boolean deleteFlag;
    // 相手方メール
    private String traderUserEmail;
    // frontから相手方代理人 mailaddressのlist
    private List<String> traderagentuserList;
    // frontからmailaddress---五つまで補足後のlist
    private List<String> traderagentuserListAll;
    // 反訴機能
    private Boolean phaseReply;
    // 交渉機能
    private Boolean phaseNegotiation;
    // 反訴の有無
    private Integer counterclaimFlag;
    // 反訴の回答開始日
    private String counterclaimStartDate;
    // 反訴の回答期限日
    private String counterclaimEndDate;
    // 交渉開始日
    private String negotiationStartDate;
    // 交渉期限日
    private String negotiationEndDate;
    // 交渉期限日変更ステータス
    private Integer negotiationEndDateChangeStatus;
    // 交渉期限日変更回数
    private Integer negotiationEndDateChangeCount;
    // 手続き中止日
    private String cancelDate;

    private String lastModifiedDate;

    private String lastModifiedBy;
    // システム時間
    private String newDate;
    // システム時間+CounterclaimLimitDays日
    private String oldDate;

    private static final long serialVersionUID = 1L;

}
