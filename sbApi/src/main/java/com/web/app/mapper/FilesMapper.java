package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.MosContentConfirm.InsertFiles;

/**
 * 检索odr_users表
 */
@Mapper
public interface FilesMapper {

  // TBL「添付ファイル（files）」を論理削除する
  void updateDeleteFlag(short deleteFlag, String fileId);

  // TBL「添付ファイル（files）」を新規登録する。
  int insertFiles(InsertFiles insertFiles);

  // 自動採番ID
  String selectMaxId();
}
