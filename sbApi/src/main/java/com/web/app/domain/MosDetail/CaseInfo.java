package com.web.app.domain.MosDetail;

import java.io.Serializable;
import lombok.Data;

/**
 * S04_申立て概要画面
 * API_案件状態取得
 * Dao層
 * CaseInfo
 * API「案件状態取得」を呼び出すData
 * 
 * @author DUC 張明慧
 * @since 2024/04/22
 * @version 1.0
 */
@Data
public class CaseInfo implements Serializable {
    // タイトル名
    private String caseTitle;

    // 案件ステータス
    private int stage;

    private String caseStatus;

    private int dateRequestStatus;

    private int messageStatus;

    // 期日用項目
    // 回答期限日
    private String replyEndDate;

    // 反訴の回答期限日
    private String counterclaimEndDate;

    // 手続き中止日
    private String cancelDate;

    // 解決日時
    private String resolutionDate;

    // 交渉期限日
    private String negotiationEndDate;

    // 調停期限日
    private String mediationEndDate;

    // モジュール利用状況Flgを返す
    private int moudleFlg;

    // チュートリアル表示制御取得
    // チュートリアル表示（申立）
    private int showTuritor1;

    // チュートリアル表示（回答）
    private int showTuritor2;

    // チュートリアル表示（調停）
    private int showTuritor3;

    private static final long serialVersionUID = 1L;
}
