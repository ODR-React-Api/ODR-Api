package com.web.app.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.Entity.MasterPlatforms;
import com.web.app.domain.Entity.MasterTypes;
import com.web.app.domain.Entity.OdrUsers;
import com.web.app.domain.MosLogin.ExpandItems;

/**
 * S8_申立登録画面
 * Mapper層
 * GetPlatformMapper
 * API_画面制御表示項目取得
 * 
 * @author DUC 馮淑慧
 * @since 2024/04/23
 * @version 1.0
 */
@Mapper
public interface GetPlatformMapper {

     // ユーザ情報取得
     OdrUsers selectOdrUsers(String sessionId);

     // TBL「種類マスタ」より申立種類取得
     List<MasterTypes> masterTypesSearch1(String getPlatformId);

     // TBL「種類マスタ」より希望する解決方法取得
     List<MasterTypes> masterTypesSearch2(String getPlatformId);

     // 画面制御表示項目表示状態取得
     MasterPlatforms masterPlatformsSearch(String getPlatformId);

     // 拡張項目の取得
     List<ExpandItems> expandProjectSearch(String getPlatformId);
}
