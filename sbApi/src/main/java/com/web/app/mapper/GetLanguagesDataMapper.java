package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.Nav.LanguagesData;
import java.util.List;

/**
 * N1 ナビバー画面
 * API_言語データ取得
 * Mapper層
 * GetLanguagesDataMapper
 * 
 * @author DUC 楊バイバイ
 * @since 2024/05/01
 * @version 1.0
 */
@Mapper
public interface GetLanguagesDataMapper {

    //全ての言語を取得する
    List<LanguagesData> selectAllLanguages();
}
