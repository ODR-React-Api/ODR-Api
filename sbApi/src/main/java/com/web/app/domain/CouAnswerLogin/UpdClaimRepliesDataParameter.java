package com.web.app.domain.CouAnswerLogin;

import java.io.Serializable;
import lombok.Data;

/**
 * S14_反訴回答登録画面
 * API_反訴への回答データ更新
 * Dao层
 * UpdClaimRepliesDataParameter
 * API_反訴への回答データ更新の引数
 * 
 * @author DUC 張明慧
 * @since 2024/05/06
 * @version 1.0
 */
@Data
public class UpdClaimRepliesDataParameter implements Serializable {
    // セッションのプラットフォームID
    private String platformId;

    // セッション情報のCaseId
    private String caseId;

    // ファイル名
    private String fileName;

    // 拡張子
    private String fileExtension;

    // ログインユーザ
    private String loginUser;

    // 反訴への回答
    private String replyContext;

    // セッション情報の削除対象ファイルid
    private String delFileId;

    private static final long serialVersionUID = 1L;
}
