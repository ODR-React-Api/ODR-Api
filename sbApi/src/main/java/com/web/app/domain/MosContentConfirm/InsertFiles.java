package com.web.app.domain.MosContentConfirm;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * filesを新規登録項目
 * 
 * @author DUC 王魯興
 * @since 2024/05/29
 * @version 1.0
 */
@Data
public class InsertFiles implements Serializable {
    private static final long serialVersionUID = 1L;

    // ID
    private String id;

    // プラットフォームID
    private String platformId;

    // 案件ID
    private String caseId;

    // ファイル名
    private String fileName;

    // 拡張子
    private String fileExtension;

    // URL
    private String fileUrl;

    // ファイルサイズ
    private int fileSize;

    // ユーザーID
    private String registerUserId;

    // 登録日
    private Date registerDate;

    // 删除Flag
    private short deleteFlag;

    // 上次修改日期
    private Date lastModifiedDate;

    // 上次修改者
    private String lastModifiedBy;

}
