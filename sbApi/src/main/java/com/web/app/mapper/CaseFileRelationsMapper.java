package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.Entity.InsertCaseFileRelations;

/**
 * 案件-添付ファイルリレーション（case_file_relations）
 */
@Mapper
public interface CaseFileRelationsMapper {

  // 案件-添付ファイルリレーションの取得
  String selectFileId(int relationType, String RelatedId);

  // TBL「案件-添付ファイルリレーション（case_file_relations）」を論理削除する
  void updateDeleteFlag(short deleteFlag, String id);

  // 自動採番のid（Id）
  String selectMaxId();

  // TBL「案件-添付ファイルリレーション（case_file_relations）」を新規登録する
  int insertCaseFileRelations(InsertCaseFileRelations insertCaseFileRelations);

}
