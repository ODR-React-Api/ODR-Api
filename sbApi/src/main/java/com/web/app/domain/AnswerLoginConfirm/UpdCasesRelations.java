package com.web.app.domain.AnswerLoginConfirm;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * S12 回答内容確認画面
 * API_案件別個人情報リレーションデータ更新
 * Dao層
 * UpdCasesRelations
 * 
 * @author 李文涛
 * @since 2024/05/21
 * @version 1.0
 */
@Data
public class UpdCasesRelations implements Serializable{
    
    @SuppressWarnings("unused")
    private static final long SerialVersionUID=1L;
    //代理人mailaddress的list
    private List<String> traderagentuserList;  
    // 案件ID
    private String caseId;
    // プラットフォームID
    private String platformId;
}
