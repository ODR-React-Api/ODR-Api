package com.web.app.domain.couAnswerLogin;
import java.io.Serializable;
import lombok.Data;
@Data
public class ReactUseFiles implements Serializable {
    // バッファリング
    private static final long serialVersionUID = 1L;
    // ID
    private String id;
    // プラットフォームID
    private String PlatformId;
    // 案件ID
    private String CaseId;
    // ファイル名
    private String FileName;
    // 拡張子
    private String FileExtension;
    // URL
    private String FileUrl;
    // ファイルサイズ
    private Integer FileSize;
    // ユーザーID
    private String RegisterUserId;
}

