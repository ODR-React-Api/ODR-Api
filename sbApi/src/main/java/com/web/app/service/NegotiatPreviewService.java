package com.web.app.service;

import java.util.List;
import com.web.app.domain.NegotiatPreview.MasterTemplates;
/**
 * 和解案プレビュー画面
 * 
 * @author DUC 馬芹
 * @since 2024/05/14
 * @version 1.0
 */
public interface NegotiatPreviewService {
    List<MasterTemplates> getNegotiationsTemplate() throws Exception;
}
