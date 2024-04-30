package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.Entity.ExtensionItem;
import com.web.app.domain.Entity.UpdateOrInsertCaseExtensionitemValues;

/**
 * 拡張項目設定値
 */
@Mapper
public interface CaseExtensionitemValuesMapper {

  // 拡張項目設定値情報を取得する
  ExtensionItem selectExtensionitemIdExtensionitemValue(String platformId, String id, short deleteFlag0, String extensionitemId); 

  // case_extensionitem_values更新
  void updateCaseExtensionitemValues(UpdateOrInsertCaseExtensionitemValues updateOrInsertCaseExtensionitemValues, String platformId, String id, short deleteFlag0, String extensionitemId);

  // case_extensionitem_values登録
  void insertCaseExtensionitemValues(UpdateOrInsertCaseExtensionitemValues updateOrInsertCaseExtensionitemValues);

  // MaxのID
  String selectMaxId();

}
