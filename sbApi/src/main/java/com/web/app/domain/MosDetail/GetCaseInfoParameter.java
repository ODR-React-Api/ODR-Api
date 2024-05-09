package com.web.app.domain.MosDetail;

import java.io.Serializable;
import lombok.Data;

/**
 * 申立て概要画面
 * API_案件状態取得
 * Dao层
 * GetCaseInfoParameter
 * API_案件状態取得の引数
 * 
 * @author DUC 張明慧
 * @since 2024/04/22
 * @version 1.0
 */
@Data
public class GetCaseInfoParameter implements Serializable {
    // ID
    private String caseId;

    // プラットフォームID
    private String platformId;

    // ユーザID
    private String userId;

    // 実行Flg
    private int executeFlg;

    // Turitor1Flg
    private int turitor1Flg;

    // Turitor2Flg
    private int turitor2Flg;

    // Turitor3Flg
    private int turitor3Flg;

    private static final long serialVersionUID = 1L;
}