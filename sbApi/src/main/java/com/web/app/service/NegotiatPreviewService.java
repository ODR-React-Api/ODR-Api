package com.web.app.service;

import java.util.List;

import com.web.app.domain.Entity.CaseNegotiations;
import com.web.app.domain.NegotiatPreview.MasterTemplates;
import com.web.app.domain.NegotiatPreview.NegotiatPreview;

/**
 * 和解案プレビュー画面
 * 
 * @author DUC 李志文 馬芹
 * @since 2024/05/10
 * @version 1.0
 */
public interface NegotiatPreviewService {
    // 和解案抽出
    int NegotiatPreview(NegotiatPreview negotiatPreview);

    // 和解案新規登録
    int InsNegotiationData(NegotiatPreview negotiatPreview);

    // 和解案更新登録
    int UpdNegotiationsData(NegotiatPreview negotiatPreview, CaseNegotiations caseNegotiations);

    // 和解案テンプレート取得
    List<MasterTemplates> getNegotiationsTemplate() throws Exception;
}