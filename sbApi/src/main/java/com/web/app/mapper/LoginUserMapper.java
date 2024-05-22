package com.web.app.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.Entity.OdrUsers;

/**
 * C1_ログイン画面
 * Mapper層実現類
 * LoginUserMapper
 * API_申立データ取得
 * 
 * @author DUC 馮淑慧
 * @since 2024/05/17
 * @version 1.0
 */
@Mapper
public interface LoginUserMapper {

    // 申立データ取得
    List<OdrUsers> getLoginUser(String email, String passWord);

    // ユーザ取得
    OdrUsers FindUserByUidOrEmail(String uid, String email, String platformId);

    // TBL「ユーザ」更新
    int updateLoginDate(String email, String passWord);
}
