package com.web.app.domain.MedUserConfirm;

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
public class MedUserConfirmSession implements Serializable {

    private static final long serialVersionUID = 1L;
    // ユーザ情報取得API:セッション.案件ID
    private String caseId;
    //ユーザ情報取得API:セッション.言語ID
    private String languageId;
    //調停人の経験取得API用 :セッション.MediatorUserEmail
    private String mediatorUserEmail;
    // 解決済み件数:11
    private int caseStage;
}
