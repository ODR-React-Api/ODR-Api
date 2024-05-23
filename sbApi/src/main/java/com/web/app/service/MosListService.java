package com.web.app.service;

import java.util.List;

import com.web.app.domain.MosList.CaseIdListInfo;
import com.web.app.domain.MosList.Position;
import com.web.app.domain.MosList.ReturnResult;
import com.web.app.domain.MosList.SelectCondition;
import com.web.app.domain.MosList.SelectListInfoResult;

/**
 * 申立て一覧画面
 * 
 * @author DUC 張万超 朱暁芳 馮淑慧
 * @since 2024/4/22
 * @version 1.0
 */
public interface MosListService {
    /**
     * API「 検索用一覧取得」より渡された引数によって、DBからケース詳細を取得する
     *
     * @param searchCase API「 検索用一覧取得」より渡された引数
     * @return case詳細
     */
    public ReturnResult searchDetailCase(SelectCondition searchCase);

    /**
     * 検索Boxに入力した文字列で申立て番号と件名の一部検索条件として、ユーザに関連するすべてのケースをDBから検索する。
     *
     * @param uid         画面.ユーザID
     * @param queryString 画面.検索Box入力文字列
     * @return 取得されたケース情報リスト
     */
    public List<ReturnResult> getFuzzyQueryListInfo(String uid, String queryString);

    /**
     * API「 曖昧検索用一覧取得」より渡された引数で、DBからケース詳細を取得する。
     *
     * @param caseId         CaseId
     * @param petitionUserId case申立て人
     * @param positionFlag   立場フラグ
     * @param queryString    画面.検索Box入力文字列
     * @return 取得されたケース情報リスト
     */
    public ReturnResult getFuzzyQueryDetailCase(String caseId, String petitionUserId, Integer positionFlag,
            String queryString);

    /**
     * テーブルより下書き保存のデータを取得する。
     *
     * @param uid ユーザID
     * @return 申立て登録下書き保存データ有無
     */
    public Integer getSaveDataInfo(String uid);

    // API_ケース詳細取得
    ReturnResult caseDetailCasesInfoSearch(CaseIdListInfo caseIdListInfo);

    // 検索用一覧取得
    List<SelectListInfoResult> getSelectListInfo(Position position);
}
