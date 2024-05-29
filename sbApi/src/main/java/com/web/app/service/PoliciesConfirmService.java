package com.web.app.service;

import com.web.app.domain.PoliciesConfirm.PoliciesInfo;

/**
 * S1_利用規約確認画面
 * Service層
 * PoliciesConfirmService
 * 
 * @author DUC 閆文静
 * @since 2024/04/19
 * @version 1.0
 */
public interface PoliciesConfirmService {
    // 利用規約情報取得
    PoliciesInfo getPoliciesInfo();

}