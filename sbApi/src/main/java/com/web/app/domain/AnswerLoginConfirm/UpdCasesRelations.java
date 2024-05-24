package com.web.app.domain.AnswerLoginConfirm;

import java.io.Serializable;
import java.util.List;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * S12 回答内容確認画面
 * API_案件別個人情報リレーションデータ更新
 * Dao層
 * UpdCasesRelations
 * 
 * @author DUC 楊バイバイ
 * @since 2024/05/01
 * @version 1.0
 */
@ApiModel
@Data
public class UpdCasesRelations implements Serializable {

    // frontからmailaddressのlist
    private List<String> traderagentuserList;
    // frontからmailaddress---五つまで補足後のlist
    private List<String> traderagentuserListAll;
    // 案件ID
    private String caseId;
    // プラットフォームID
    private String platformId;

    private static final long serialVersionUID = 1L;
}
