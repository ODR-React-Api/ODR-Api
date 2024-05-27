package com.web.app.domain.MedUserConfirm;

import java.io.Serializable;
import lombok.Data;

/**
 * 調停者メールとユザーIDを取得
 * 
 * @author DUC 賈文志
 * @since 2024/05/13
 * @version 1.0
 */
@Data
public class GetUserIDbyMail implements Serializable {
    // 缓冲
    private static final long serialVersionUID = 1L;
    // 調停人
    private String MediatorUserEmail;
    // ID
    private String Uid;

}
