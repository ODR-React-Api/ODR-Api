package com.web.app.mapper;

import com.web.app.domain.MediationsConCon.MediationsContentDB;

/**
 * S24_調停案内容確認画面
 * Mapper层
 * UpdMediationsContentMapper
 * 
 * @author DUC 王 エンエン
 * @since 2024/05/09
 * @version 1.0
 */
public interface UpdMediationsContentMapper {
  // API_調停案更新(mediationsContentDB数据库对象)
  int setUpdMediationsContent(MediationsContentDB mediationsContentDB);
}
