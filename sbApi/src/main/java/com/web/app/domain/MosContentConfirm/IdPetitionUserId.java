package com.web.app.domain.MosContentConfirm;

import java.io.Serializable;
import lombok.Data;

/**
 * TBL「案件別個人情報リレーション（case_relations）」と「申立（case_petitions）」より用意した下書き保存データを取得項目
 * 
 * @author DUC 王魯興
 * @since 2024/05/29
 * @version 1.0
 */
@Data
public class IdPetitionUserId implements Serializable {
    private static final long serialVersionUID = 1L;

    // Id
    private String id;

    // ユーザーId
    private String petitionUserId;
}
