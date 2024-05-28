package com.web.app.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.MosContentConfirm.ExtensionItem;
import com.web.app.domain.MosContentConfirm.IdPetitionUserId;
import com.web.app.domain.MosContentConfirm.InsertCaseFileRelations;
import com.web.app.domain.MosContentConfirm.InsertCases;
import com.web.app.domain.MosContentConfirm.InsertFiles;
import com.web.app.domain.MosContentConfirm.UpdateCasePetitions;
import com.web.app.domain.MosContentConfirm.UpdateCaseRelations;
import com.web.app.domain.MosContentConfirm.UpdateOrInsertCaseExtensionitemValues;
import com.web.app.domain.MosContentConfirm.UserLanguageIdPlatformId;

/**
 * API_申立て情報登録
 */
@Mapper
public interface InsPetitionsDataMapper {

  //TBL「案件別個人情報リレーション（case_relations）」と「申立（case_petitions）」より用意した下書き保存データを取得する
  IdPetitionUserId selectIdPetitionUserId(String uid);

  // TBL「案件別個人情報リレーション（case_relations）」の更新
  int updateCaseRelations(UpdateCaseRelations updateCaseRelations);

  // ユーザ情報の取得
  UserLanguageIdPlatformId selectLanguageIdAndPlatformId(String uid);

  // 販売者メールアドレス登録有無の判定
  int selectCount(String email, String platformId, short deleteFlag);

  // TBL「案件（cases）」の新規登録
  int insertCases(InsertCases insertCases);

  // TBL「申立（case_petitions）」の更新
  int updateCasePetitions(UpdateCasePetitions updateCasePetitions);

  // 案件-添付ファイルリレーションの取得
  List<String> selectFileId(int relationType, String relatedId);

  // TBL「案件-添付ファイルリレーション（case_file_relations）」を論理削除する
  void updateDeleteFlag(short deleteFlag, String id);

  // TBL「案件-添付ファイルリレーション（case_file_relations）」を新規登録する
  int insertCaseFileRelations(InsertCaseFileRelations insertCaseFileRelations);

  // TBL「添付ファイル（files）」を論理削除する
  void updateDeleteFlag2(short deleteFlag, String fileId);

  // TBL「添付ファイル（files）」を新規登録する。
  int insertFiles(InsertFiles insertFiles);

  // 拡張項目設定値情報を取得する
  ExtensionItem selectExtensionitemIdExtensionitemValue(String platformId, String id, short deleteFlag0, String extensionitemId); 

  // case_extensionitem_values更新
  void updateCaseExtensionitemValues(UpdateOrInsertCaseExtensionitemValues updateOrInsertCaseExtensionitemValues, String platformId, String id, short deleteFlag0, String extensionitemId);

  // case_extensionitem_values登録
  void insertCaseExtensionitemValues(UpdateOrInsertCaseExtensionitemValues updateOrInsertCaseExtensionitemValues);

}
