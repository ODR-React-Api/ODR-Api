package com.web.app.domain.AnswerLogin;

import java.io.Serializable;
import lombok.Data;

/**
 * S11_回答登録画面
 * API_反訴・回答データ新規登録/更新
 * Dao層
 * UpdRepliesDataParameter
 * API_反訴・回答データ新規登録/更新の引数
 * 
 * @author DUC 張明慧
 * @since 2024/05/14
 * @version 1.0
 */
@Data
public class UpdRepliesDataParameter implements Serializable {
    // セッションのプラットフォームID
    private String platformId;

    // セッション情報のCaseId
    private String caseId;

    // 画面項目 申立種類、希望する解決方法種類、回答種類、対応方法の種類など
    private String replyType;

    // 画面項目 「その他」入力箱テキスト
    public String replyTypeOther;

    // 画面項目 内容
    public String replyContext;

    // 画面項目 反訴有無
    public int haveCounterClaim;

    // 画面項目 反訴内容
    public String counterClaimContext;

    // 画面項目 相手方代理人1
    public String traderAgent1_UserEmail;

    // 画面項目 相手方代理人2
    public String traderAgent2_UserEmail;

    // 画面項目 相手方代理人3
    public String traderAgent3_UserEmail;

    // 画面項目 相手方代理人4
    public String traderAgent4_UserEmail;

    // 画面項目 相手方代理人5
    public String traderAgent5_UserEmail;

    // ログインユーザ
    private String loginUser;

    // 添付資料
    // ファイル名
    private String fileName;

    // 拡張子
    private String fileExtension;

    // 反訴の添付資料
    // ファイル名
    private String fileNameCounterClaim;

    // 拡張子
    private String fileExtensionCounterClaim;

    // セッション情報の削除対象ファイルid
    private String delFileId;

    private static final long serialVersionUID = 1L;
}
