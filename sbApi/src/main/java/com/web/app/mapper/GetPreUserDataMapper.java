package com.web.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.userLogin.GetPreUserData;

/**
 * API_仮会員登録データ取得Mapper
 * 
 * @author DUC 信召艶
 * @since 2024/04/18
 * @version 1.0
 */
@Mapper
public interface GetPreUserDataMapper {
  GetPreUserData getPreUserDataSearch(String guid, String registerDate);

  List<GetPreUserData> getUserPre(String guid);
}
