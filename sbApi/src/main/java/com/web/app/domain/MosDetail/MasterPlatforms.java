package com.web.app.domain.MosDetail;

import java.io.Serializable;
import lombok.Data;

/**
 * 申立て概要画面
 * API_案件状態取得
 * Dao层
 * MasterPlatforms
 * テーブル名：master_platformsプラットフォームマスタ
 * 利用モジュール状況取得
 * 
 * @author DUC 張明慧
 * @since 2024/04/23
 * @version 1.0
 */
@Data
public class MasterPlatforms implements Serializable {
    // 交渉機能利用有無
    private int phaseNegotiation;

    // 調停機能利用有無
    private int phaseMediation;

    // 仲裁機能利用有無
    private int phaseArbitration;

    // 反訴機能利用有無
    private int phaseReply;

    private static final long serialVersionUID = 1L;
}
