package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.MosList.SearchDetail;
import com.web.app.domain.MosList.SelectCondition;

/**
 * API「 検索用一覧取得」より渡された引数によって、DBからケース詳細を取得するMapper
 * 
 * @author DUC 張万超
 * @since 2024/4/22
 * @version 1.0
 */

@Mapper
public interface SearchDetailCaseMapper {

    /**
     * 申立て番号、件名、登録日付、対応期日、状態、要対応有無の取得
     *
     * @param searchCase API「 検索用一覧取得」より渡された引数
     * @return case详细情报
     */

    public SearchDetail searchDetail(SelectCondition searchCase);

    /**
     * 調停人情報開示フラグを取得
     *
     * @param caseId 引数.CaseId
     * @return 調停人情報開示フラグ
     */

    public Integer getMediatorDisclosureFlag(String caseId);

    /**
     * 未読メッセージ件数取得 （調停人＋受理後＋未開示）
     *
     * @param caseId         引数.CaseId
     * @param petitionUserId 引数.ユーザId
     * @return 未読メッセージ件数
     */

    public Integer getMsgCountByFlag(String caseId, String petitionUserId);

    /**
     * 未読メッセージ取得（申立人・相手方・調停人受理開示後）
     *
     * @param caseId         引数.CaseId
     * @param petitionUserId 引数.ユーザId
     * @return 未読メッセージ件数
     */

    public Integer getMsgCountByFlagNo(String caseId, String petitionUserId);
}