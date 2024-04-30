package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.Entity.InsertCases;

/**
 * 检索odr_users表
 */
@Mapper
public interface CasesMapper {

  // 案件（cases）情報を取得する
  String selectMaxCid(); 

  // TBL「案件（cases）」の新規登録
  int insertCases(InsertCases insertCases);
}
