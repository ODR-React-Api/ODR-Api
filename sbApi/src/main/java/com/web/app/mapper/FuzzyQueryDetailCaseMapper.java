package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.MosList.SearchDetail;

/**
 * 曖昧検索用ケース詳細取得Mapper
 * 
 * @author DUC 張万超
 * @since 2024/04/23
 * @version 1.0
 */

@Mapper
public interface FuzzyQueryDetailCaseMapper {

    /**
     * 申立て番号、件名、登録日付、対応期日、状態、要対応有無の取得
     *
     * @param caseId CaseId
     * @param queryString 画面.検索Box入力文字列
     * @return 取得されたケース情報リスト
     */
    SearchDetail getQueryDetailCase(String caseId, String queryString);

    /**
     * 調停人情報開示フラグを取得
     *
     * @param caseId 引数.CaseId
     * @return 調停人情報開示フラグ
     */
    int getMediatorDisclosureFlag(String caseId);

    /**
     * 未読メッセージ件数取得 （調停人＋受理後＋未開示）
     *
     * @param caseId         引数.CaseId
     * @param petitionUserId case申立て人
     * @return 未読メッセージ件数
     */
    int getMsgCountByFlag(String caseId, String petitionUserId);

    /**
     * 未読メッセージ取得（申立人・相手方・調停人受理開示後）
     *
     * @param caseId         引数.CaseId
     * @param petitionUserId case申立て人
     * @return 未読メッセージ件数
     */
    int getMsgCountByFlagNo(String caseId, String petitionUserId);

}
