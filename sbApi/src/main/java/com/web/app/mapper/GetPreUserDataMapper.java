package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * API_仮会員登録データ取得
 * 
 * @author DUC 信召艶
 * @since 2024/04/18
 * @version 1.0
 */
@Mapper
public interface GetPreUserDataMapper {
    //仮会員登録データ取得
    String getPreUserData(String guid);
}
