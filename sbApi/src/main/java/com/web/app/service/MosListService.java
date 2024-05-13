package com.web.app.service;

import java.util.List;

import com.web.app.domain.MosList.Position;
import com.web.app.domain.MosList.ReturnResult;

/**
 * S3_申立て一覧画面
 * Service層
 * MosListService
 * API_検索用一覧取得
 * 
 * @author DUC 馮淑慧
 * @since 2024/04/18
 * @version 1.0
 */
public interface MosListService {

    // 検索用一覧取得
    List<ReturnResult> selectMosList(Position position);

}
