package com.web.app.service;

import java.util.List;
import com.web.app.domain.MosDetail.ReturnResult;

/**
 * 申立て一覧画面のService
 * 
 * @author DUC 王魯興
 * @since 2024/05/28
 * @version 1.0
 */
public interface MosListService {

  // 一覧取得
  List<ReturnResult> getListInfo(String uid);
}
