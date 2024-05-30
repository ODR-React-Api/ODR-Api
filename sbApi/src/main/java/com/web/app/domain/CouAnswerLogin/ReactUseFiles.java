package com.web.app.domain.CouAnswerLogin;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import lombok.Data;

/**
 * S14_反訴回答登録画面
 * 
 * @author DUC 信召艶
 * @since 2024/05/13
 * @version 1.0
 */
@ApiModel
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
    public void setRegisterUserId(String registerUserId2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setRegisterUserId'");
    }
    public void setFileSize(Integer fileSize2) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'setFileSize'");
    }
    public void setFileUrl(String fileUrl2) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'setFileUrl'");
    }
    public void setFileExtension(String fileExtension2) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'setFileExtension'");
    }
}
