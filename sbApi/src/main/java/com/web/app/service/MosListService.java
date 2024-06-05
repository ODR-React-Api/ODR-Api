package com.web.app.service;

import java.util.List;

import com.web.app.domain.getSelectListInfo.CaseRelations;
import com.web.app.domain.getSelectListInfo.MosList;
import com.web.app.domain.getSelectListInfo.ReturnData;

/**
 * S3_申立て一覧画面
 * 
 * @author DUC 郝建润
 * @since 2024/06/04
 * @version 1.0
 */
public interface MosListService {
    // 検索用一覧取得
    List<ReturnData> findCasePetitionUserId(MosList mosList);

    // ユーザが申立人(相手方/調停人)となるケースの取得
    List<ReturnData> getDetailedContent(List<CaseRelations> flg, int statusFlag, MosList mosList);

    // 引数.メッセージ有無Flgと引数.要対応有無Flgによるデータ抽出結合
    List<ReturnData> combine(List<ReturnData> flg, MosList mosList);
}
