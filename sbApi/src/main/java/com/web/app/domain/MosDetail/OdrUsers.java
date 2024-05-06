package com.web.app.domain.MosDetail;
import java.io.Serializable;
import lombok.Data;

/**
 * 申立て概要画面
 * API_案件状態取得
 * Dao层
 * OdrUsers 
 * テーブル名：odr_usersユーザ
 * チュートリアル表示制御取得
 */
@Data
public class OdrUsers implements Serializable {
    // ID
    private String uid;

    // プラットフォームID
    private String platformId;

    // チュートリアル表示（申立）
    private int showTuritor1;

    // チュートリアル表示（回答）
    private int showTuritor2;

    // チュートリアル表示（調停）
    private int showTuritor3;

    private static final long serialVersionUID = 1L;
}
