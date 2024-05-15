package com.web.app.domain.MosDetail;

import java.io.Serializable;
import lombok.Data;

/**
 * S04_申立て概要画面
 * API_チュートリアル表示制御変更
 * Dao層
 * UpdShowTuritorParameter
 * API_チュートリアル表示制御変更の引数
 * 
 * @author DUC 張明慧
 * @since 2024/04/22
 * @version 1.0
 */
@Data
public class UpdShowTuritorParameter implements Serializable {
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