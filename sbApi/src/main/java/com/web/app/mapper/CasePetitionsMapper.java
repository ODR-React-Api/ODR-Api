package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.Entity.UpdateCasePetitions;

/**
 * 申立（case_petitions）
 */
@Mapper
public interface CasePetitionsMapper {

  // TBL「申立（case_petitions）」の更新
  int updateCasePetitions(UpdateCasePetitions updateCasePetitions, String id);

}
