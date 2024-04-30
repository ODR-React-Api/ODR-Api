package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.Entity.UserLanguageIdPlatformId;

/**
 * 检索odr_users表
 */
@Mapper
public interface OdrUsersMapper {

  // ユーザ情報を取得する
  String selectEmailOdrUsers(String uid);

  // ユーザ情報の取得
  UserLanguageIdPlatformId selectLanguageIdAndPlatformId(String uid);

  // 販売者メールアドレス登録有無の判定
  int selectCount(String email, String platformId, short deleteFlag);
}
