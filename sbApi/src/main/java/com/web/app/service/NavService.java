package com.web.app.service;

import java.util.List;
import com.web.app.domain.Nav.LanguagesData;

/**
 * N1 ナビバー画面
 * Service層
 * NavService
 * 
 * @author DUC 楊バイバイ
 * @since 2024/05/01
 * @version 1.0
 */
public interface NavService {

    //全ての言語を検索する
    List<LanguagesData> getLanguagesDataList();

}


