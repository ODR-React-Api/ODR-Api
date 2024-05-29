package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.web.app.domain.Nav.LanguagesData;
import com.web.app.mapper.GetLanguagesDataMapper;
import com.web.app.service.NavService;

/**
 * N1 ナビバー画面
 * Service層実現類
 * NavServiceImpl
 * 
 * @author DUC 楊バイバイ
 * @since 2024/05/01
 * @version 1.0
 */
@Service
public class NavServiceImpl implements NavService {

    @Autowired
    private GetLanguagesDataMapper languagesMapper;
    
     /**
     * API_言語データ取得
     * 検索条件によって、言語を取得する。
     *
     * @return  画面表示用の言語
     */
    @Override
    public List<LanguagesData> getLanguagesDataList() {
        return languagesMapper.selectAllLanguages();
    }

}
