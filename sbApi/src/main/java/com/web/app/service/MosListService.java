package com.web.app.service;

import com.web.app.domain.MosList.ReturnResult;
import com.web.app.domain.MosList.SelectCondition;

/**
 * 申立て一覧画面
 * 
 * @author DUC 張万超
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
    public ReturnResult searchSetailCase(SelectCondition searchCase);
}
