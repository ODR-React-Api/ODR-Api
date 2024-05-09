package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.ReconciliationUser;
import com.web.app.domain.ActionHistories;
import com.web.app.domain.Entity.Cases;

/**
 * 和解案合意更新API
 * 「アクロン履歴」新規登録
 * メール送信
 * 
 * @author DUC jiawenzhi
 * @since 2024/05/09
 * @version 1.0
 */

@Mapper
public interface UpdNegotiatAgreeMapper {

    // 和解案合意更新API
    int reconciliationUpdate(ReconciliationUser reconciliationUser);

    // 「アクロン履歴」新規登録
    int ActionHistoriesInsert(ActionHistories ActionHistories);

    // CaseIdに基づいてcase_relationsからデータを取得する
    ActionHistories UserSearch(ActionHistories ActionHistories);

    // casesテーブルのCaseTitleを検索し、メール送信に設定する
    String CaseTitleSearch(Cases cases);
}
