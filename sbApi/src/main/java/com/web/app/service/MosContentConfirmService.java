package com.web.app.service;

import com.web.app.domain.MosContentConfirm.S09ScreenIntelligence;

/**
 * 申立て内容確認画面
 * 
 * @author DUC 王魯興
 * @since 2024/05/28
 * @version 1.0
 */
public interface MosContentConfirmService {
  // 申立て情報登録
  void InsPetitionsData(S09ScreenIntelligence s09ScreenIntelligence);
}