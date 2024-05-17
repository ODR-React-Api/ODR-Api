package com.web.app.domain.NegotiatMake;

import java.io.Serializable;
import lombok.Data;
/**
 * セッション情報
 * 
 * @author DUC 馬芹
 * @since 2024/05/06
 * @version 1.0
 */
@Data
public class SessionItems implements Serializable {

    private static final long serialVersionUID = 1L;
    //ログインユーザが申立人場合：1
    //ログインユーザが相手方場合：2
    private int flag;
    //更新時用セッション情報の和解案id
    private String id;
    //ファイルID
    private String filesId;
    //プラットフォームID
    private String platformId;
    //'セッション情報のCaseId
    private String caseId;
    //ログインユーザID
    private String userId;
}
