package com.web.app.service;

import com.web.app.domain.NegotiatMake.NegotiationsFile;

/**
 * 和解案作成画面
 * 
 * @author DUC 馬芹
 * @since 2024/05/06
 * @version 1.0
 */
public interface NegotiatMakeService {
    // 和解案編集依頼データ新規登録
    int addNegotiationsEdit(NegotiationsFile negotiationsFile)throws Exception;
    // 和解案編集依頼データ更新
    int updateNegotiationsEdit(NegotiationsFile negotiationsFile)throws Exception;

}
