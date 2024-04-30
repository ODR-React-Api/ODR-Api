package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.app.domain.Entity.InsertCases;
import com.web.app.domain.Entity.InsertFiles;
import com.web.app.domain.Entity.S09ScreenIntelligence;
import com.web.app.domain.Entity.UpdateCasePetitions;
import com.web.app.domain.Entity.UpdateCaseRelations;
import com.web.app.domain.Entity.UpdateOrInsertCaseExtensionitemValues;
import com.web.app.domain.Entity.InsertCaseFileRelations;
import com.web.app.domain.Entity.ExtensionItem;
import com.web.app.domain.Entity.IdPetitionUserId;
import com.web.app.domain.Entity.UserLanguageIdPlatformId;
import com.web.app.mapper.CaseExtensionitemValuesMapper;
import com.web.app.mapper.CaseFileRelationsMapper;
import com.web.app.mapper.CasePetitionsMapper;
import com.web.app.mapper.CaseRelationsMapper;
import com.web.app.mapper.CasesMapper;
import com.web.app.mapper.FilesMapper;
import com.web.app.mapper.OdrUsersMapper;
import com.web.app.service.RegistrationInformationRegistrationService;

@Service
public class RegistrationInformationRegistrationServiceImpl implements RegistrationInformationRegistrationService {

  // 引入Dao层
  // 案件別個人情報リレーション
  @Autowired
  private CaseRelationsMapper caseRelationsMapper;
  // ユーザ
  @Autowired
  private OdrUsersMapper odrUsersMapper;
   // ユーザ
  @Autowired
  private CasesMapper casesMapper;
  // 申立
  @Autowired
  private CasePetitionsMapper casePetitionsMapper;
  // 案件-添付ファイルリレーション
  @Autowired
  private CaseFileRelationsMapper caseFileRelationsMapper;
  // 添付ファイル（files）
  @Autowired
  private FilesMapper filesMapper;
  // 拡張項目設定値（case_extensionitem_values）
  @Autowired
  private CaseExtensionitemValuesMapper caseExtensionitemValuesMapper;

  @Override
  public Integer LoginIntelligence(S09ScreenIntelligence s09ScreenIntelligence, String uid, String platformId) {

    // 自動採番のcid（CaseId）
    String cid = null;

    // 自動採番のid（Id）
    String id = null;

    // ファイルID
    String fileId = null;

    // 案件種類
    int relationType = 0;

    // 删除Flag1
    short deleteFlag1 = 1;

    // 删除Flag0
    short deleteFlag0 = 0;

    // 登录返回值
    int returnFlag = 0;

    // 販売者メアド登録有無FLG
    int salesBrokerFlg = 0;

    // 1.TBL「案件別個人情報リレーション（case_relations）」と「申立（case_petitions）」より用意した下書き保存データを取得する。
    IdPetitionUserId idPetitionUserId = caseRelationsMapper.selectIdPetitionUserId(uid);

    // 2.ユーザ情報の取得
    UserLanguageIdPlatformId userLanguageIdPlatformId = odrUsersMapper.selectLanguageIdAndPlatformId(uid);

    // 案件（cases）のMAX（cid）の取得
    // TODO(自动采番的最大ID)
    String maxCid = casesMapper.selectMaxCid();
    // 自動採番のcid（CaseId）
    cid = maxCid + 1;
    // 3.TBL「案件（cases）」の新規登録
    returnFlag = insertCases(returnFlag, s09ScreenIntelligence, cid , userLanguageIdPlatformId);

    // 案件別個人情報リレーション（case_relations）のMAX（id）の取得
    // TODO(自动采番的最大ID)
    String maxId = caseRelationsMapper.selectMaxId();
    // MAXのID
    id = maxId + 1;
    // 4.TBL「案件別個人情報リレーション（case_relations）」の更新
    returnFlag = updateCaseRelations(returnFlag, id, maxCid, userLanguageIdPlatformId, s09ScreenIntelligence, idPetitionUserId);

    // 5.TBL「申立（case_petitions）」の更新
    returnFlag = updateCasePetitions(returnFlag, idPetitionUserId, cid, s09ScreenIntelligence, userLanguageIdPlatformId);

    // 6.a案件-添付ファイルリレーションの取得
    fileId = caseFileRelationsMapper.selectFileId(relationType, idPetitionUserId.getId());

    // 6.b上記取得(案件-添付ファイルリレーションの取得)有りの場合は関連のデータを初期化する
    if (fileId != null) {
      // TBL「添付ファイル（files）」を論理削除する
      filesMapper.updateDeleteFlag(deleteFlag1, fileId);

      // TBL「案件-添付ファイルリレーション（case_file_relations）」を論理削除する
      caseFileRelationsMapper.updateDeleteFlag(deleteFlag1, id);
    }

    // 7.画面に添付資料が添付有りの場合、添付資料がなくなるまで以下の処理を行う。
    if (s09ScreenIntelligence.getFileSize() != "0") {
      
      // 自動採番のid（Id）
      // TODO(自动采番的最大ID)
      String fileMaxId = filesMapper.selectMaxId();
      String fileMaxId2 = fileMaxId + 1;
      // a.TBL「添付ファイル（files）」を新規登録する
      returnFlag = insertFiles(returnFlag, fileMaxId2, userLanguageIdPlatformId, cid, s09ScreenIntelligence, uid, deleteFlag0);
      // b.TBL「案件-添付ファイルリレーション（case_file_relations）」を新規登録する
      returnFlag = insertCaseFileRelations(returnFlag, userLanguageIdPlatformId, relationType, cid, idPetitionUserId, fileMaxId2, s09ScreenIntelligence, uid, deleteFlag0);
    }
    // 8.③～⑦の登録処理が正常終了の場合、アクション履歴登録を行う
    // TODO(メール・アクション一覧_v1.04.xlsx在哪里？)
    if (returnFlag == 0) {
      // 共通API调用
      
    }

    // 9.販売者メールアドレス登録有無の判定
    int numberOfPieces = odrUsersMapper.selectCount(s09ScreenIntelligence.getTraderMail(), userLanguageIdPlatformId.getPlatformId(), deleteFlag0);
    // cnt > 0の場合
    if (numberOfPieces > 0) {
      // 販売者メアド登録有無FLGに1（登録あり）を設定
      salesBrokerFlg = 1;
    }

    // 10.画面上拡張項目がある場合、TBLの内容を追加・または更新します。
    if (s09ScreenIntelligence.getExtensionItem().size() > 0) {
      List<ExtensionItem> extensionItemList = new ArrayList<>();
      // 既存の拡張項目内容を取得
      for (int i = 0; i < s09ScreenIntelligence.getExtensionItem().size(); i++) {
        // 既存の拡張項目内容を取得
        ExtensionItem extensionItem = caseExtensionitemValuesMapper.selectExtensionitemIdExtensionitemValue(platformId, idPetitionUserId.getId(), deleteFlag0, s09ScreenIntelligence.getExtensionItem().get(i).getExtensionitemId());
        // 全部数据
        extensionItemList.add(extensionItem);
      }
      // 取得したデータがある場合、2)へ(データを更新)
      if (extensionItemList.size() > 0) {
        for (int i = 0; i < extensionItemList.size(); i++) {
          // データを更新
          updateCaseExtensionitemValues(extensionItemList.get(i),  deleteFlag0, uid, cid, platformId, id);
        }
      } else {
        for (int i = 0; i < extensionItemList.size(); i++) {
          // データを更新
          insertCaseExtensionitemValues(extensionItemList.get(i), deleteFlag0, uid, cid, platformId, id);
        }
      }
    }
    // 販売者メアド登録有無FLG戻り
    return salesBrokerFlg;
  }

  // TBL「案件（cases）」の新規登録
  public int insertCases(int returnFlag, S09ScreenIntelligence s09ScreenIntelligence, String cid, UserLanguageIdPlatformId userLanguageIdPlatformId) {
    // 登录用数据初始化
    InsertCases insertCases = new InsertCases();
    // ID
    insertCases.setCid(cid);
    // プラットフォームID
    insertCases.setPlatformId(userLanguageIdPlatformId.getPlatformId());
    // 案件ステージ
    insertCases.setCaseStage("0");
    // 案件ステータス
    insertCases.setCaseStatus("0000");
    // タイトル名
    // TODO(希望解决方法应该是list，应该判断一下有几个值)
    insertCases.setCaseTitle(s09ScreenIntelligence.getProductName() + s09ScreenIntelligence.getExpectResloveTypeValue());
    // 申立て日
    insertCases.setPetitionDate(new Date());
    // 利用言語
    insertCases.setLanguageId(userLanguageIdPlatformId.getLanguageId());
    // 回答開始日
    insertCases.setReplyStartDate(new Date());
    // 回答期限日
    // TODO(システム日付 + master_platforms.ReplyLimitDays 加到时分秒哪一个上面？)
    insertCases.setReplyEndDate(new Date());
    // TBL「案件（cases）」の新規登録
    returnFlag = casesMapper.insertCases(insertCases);
    return returnFlag;
  }

  // TBL「案件別個人情報リレーション（case_relations）」の更新
  private int updateCaseRelations(int returnFlag, String id, String maxCid, UserLanguageIdPlatformId userLanguageIdPlatformId, S09ScreenIntelligence s09ScreenIntelligence, IdPetitionUserId idPetitionUserId) {
    // 「案件別個人情報リレーション（case_relations）」更新用数据初始化
    UpdateCaseRelations updateCaseRelations = new UpdateCaseRelations();
    // ID
    updateCaseRelations.setId(id);
    // 案件ID
    updateCaseRelations.setCaseId(maxCid);
    // プラットフォームID
    updateCaseRelations.setPlatformId(userLanguageIdPlatformId.getPlatformId());
    // 申立て人入力情報
    updateCaseRelations.setPetitionUserInfoEmail(s09ScreenIntelligence.getEtitionUserInfoEmail());
    // 代理人1
    updateCaseRelations.setAgent1Email(s09ScreenIntelligence.getAgent1Email());
    // 代理人2
    updateCaseRelations.setAgent2Email(s09ScreenIntelligence.getAgent2Email());
    // 代理人3
    updateCaseRelations.setAgent3Email(s09ScreenIntelligence.getAgent3Email());
    // 代理人4
    updateCaseRelations.setAgent4Email(s09ScreenIntelligence.getAgent4Email());
    // 代理人5
    updateCaseRelations.setAgent5Email(s09ScreenIntelligence.getAgent5Email());
    // 相手方メール
    updateCaseRelations.setTraderUserEmail(s09ScreenIntelligence.getTraderMail());
    // TBL「案件別個人情報リレーション（case_relations）」の更新
    returnFlag = caseRelationsMapper.updateCaseRelations(updateCaseRelations, idPetitionUserId.getId(), idPetitionUserId.getPetitionUserId());
    return returnFlag;
  }

  // TBL「申立（case_petitions）」の更新
  private int updateCasePetitions(int returnFlag, IdPetitionUserId idPetitionUserId, String cid, S09ScreenIntelligence s09ScreenIntelligence, UserLanguageIdPlatformId userLanguageIdPlatformId) {
    // TBL「申立（case_petitions）」更新用数据初始化
    UpdateCasePetitions updateCasePetitions = new UpdateCasePetitions();
    // プラットフォームID
    updateCasePetitions.setPlatformId(idPetitionUserId.getPetitionUserId());
    // 案件ID
    updateCasePetitions.setCaseId(cid);
    // 商品名
    updateCasePetitions.setProductName(s09ScreenIntelligence.getProductName());
    // 商品ID
    updateCasePetitions.setPlatformId(s09ScreenIntelligence.getProductId());
    // 販売元名称
    updateCasePetitions.setTraderName(s09ScreenIntelligence.getTraderName());
    // 販売元メールアドレス
    updateCasePetitions.setTraderMail(s09ScreenIntelligence.getTraderMail());
    // 販売元ＵＲＬ
    updateCasePetitions.setTraderUrl(s09ScreenIntelligence.getTraderUrl());
    // 購入日
    updateCasePetitions.setBoughtDate(s09ScreenIntelligence.getBoughtDate());
    // 購入金額
    updateCasePetitions.setPrice(s09ScreenIntelligence.getPrice());
    // 申立ての種類
    // TODO(有可能是List)
    updateCasePetitions.setPetitionTypeValue(s09ScreenIntelligence.getPetitionTypeValue());
    // 申立て内容
    updateCasePetitions.setPetitionContext(s09ScreenIntelligence.getPetitionContext());
    // 希望する解決方法
    // TODO(有可能是List)
    updateCasePetitions.setExpectResloveTypeValue(s09ScreenIntelligence.getExpectResloveTypeValue());
    // その他
    updateCasePetitions.setOther(s09ScreenIntelligence.getOther());
    // 言語ID
    updateCasePetitions.setLanguageId(userLanguageIdPlatformId.getLanguageId());

    // TBL「申立（case_petitions）」の更新
    returnFlag = casePetitionsMapper.updateCasePetitions(updateCasePetitions, idPetitionUserId.getId());
    return returnFlag;
  }

  // TBL「添付ファイル（files）」を新規登録する
  private int insertFiles(int returnFlag, String fileMaxId2, UserLanguageIdPlatformId userLanguageIdPlatformId, String cid, S09ScreenIntelligence s09ScreenIntelligence, String uid, short DeleteFlag0) {

    // TBL「添付ファイル（files）」を新規登録用数据初始化
    InsertFiles insertFiles = new InsertFiles();
    // ID
    insertFiles.setId(fileMaxId2);
    // プラットフォームID
    insertFiles.setPlatformId(userLanguageIdPlatformId.getPlatformId());
    // 案件ID
    insertFiles.setCaseId(cid);
    // ファイル名
    insertFiles.setFileName(s09ScreenIntelligence.getFileName());
    // 拡張子
    insertFiles.setFileExtension(s09ScreenIntelligence.getFileExtension());
    // URL
    insertFiles.setFileUrl(s09ScreenIntelligence.getFileUrl());
    // ファイルサイズ
    insertFiles.setFileSize(s09ScreenIntelligence.getFileSize());
    // ユーザーID
    insertFiles.setRegisterUserId(uid);
    // 登録日
    insertFiles.setRegisterDate(new Date());
    // 删除Flag
    insertFiles.setDeleteFlag(DeleteFlag0);
    // 上次修改日期
    insertFiles.setLastModifiedDate(new Date());
    // 上次修改者
    insertFiles.setLastModifiedBy(uid);
    // TBL「添付ファイル（files）」を新規登録する
    returnFlag = filesMapper.insertFiles(insertFiles);
    return returnFlag;
  }

  // TBL「案件-添付ファイルリレーション（case_file_relations）」を新規登録する。
  private int insertCaseFileRelations(int returnFlag, UserLanguageIdPlatformId userLanguageIdPlatformId, int relationType, String cid, IdPetitionUserId idPetitionUserId, String fileMaxId2, S09ScreenIntelligence s09ScreenIntelligence, String uid, short deleteFlag0) {

    // 自動採番のid（Id）
    String maxId = caseFileRelationsMapper.selectMaxId();

    // TBL「案件-添付ファイルリレーション（case_file_relations）」を新規登録用数据初始化
    InsertCaseFileRelations insertCaseFileRelations = new InsertCaseFileRelations();
    // ID
    insertCaseFileRelations.setId(maxId + 1);
    // プラットフォームID
    insertCaseFileRelations.setPlatformId(userLanguageIdPlatformId.getPlatformId());
    // 案件ID
    insertCaseFileRelations.setCaseId(cid);
    // 案件種類
    insertCaseFileRelations.setRelationType(relationType);
    // 案件種類ID
    insertCaseFileRelations.setRelatedId(idPetitionUserId.getId());
    // ファイルID
    insertCaseFileRelations.setFileId(fileMaxId2);
    // 删除Flag
    insertCaseFileRelations.setDeleteFlag(deleteFlag0);
    // 上次修改日期
    insertCaseFileRelations.setLastModifiedDate(new Date());
    // 上次修改者
    insertCaseFileRelations.setLastModifiedBy(uid);
    // TBL「案件-添付ファイルリレーション（case_file_relations）」を新規登録する
    returnFlag = caseFileRelationsMapper.insertCaseFileRelations(insertCaseFileRelations);
    return returnFlag;
  }

  // case_extensionitem_valuesの更新
  private void updateCaseExtensionitemValues(ExtensionItem extensionItem, short deleteFlag0, String uid, String cid, String platformId, String id) {

    // case_extensionitem_values更新用数据初始化
    UpdateOrInsertCaseExtensionitemValues updateOrInsertCaseExtensionitemValues = new UpdateOrInsertCaseExtensionitemValues();
    // 拡張項目値
    updateOrInsertCaseExtensionitemValues.setExtensionitemValue(extensionItem.getExtensionitemValue());
    // 删除Flag
    updateOrInsertCaseExtensionitemValues.setDeleteFlag(deleteFlag0);
    // LastModifiedDate
    updateOrInsertCaseExtensionitemValues.setLastModifiedDate(new Date());
    // LastModifiedBy
    updateOrInsertCaseExtensionitemValues.setLastModifiedBy(uid);
    // 案件ID
    updateOrInsertCaseExtensionitemValues.setCaseId(cid);
    // case_extensionitem_valuesの更新
    caseExtensionitemValuesMapper.updateCaseExtensionitemValues(updateOrInsertCaseExtensionitemValues, platformId, id, deleteFlag0, extensionItem.getExtensionitemId());
  }

  // case_extensionitem_valuesの登録
  private void insertCaseExtensionitemValues(ExtensionItem extensionItem, short deleteFlag0, String uid, String cid, String platformId, String id) {

    // case_extensionitem_values更新用数据初始化
    UpdateOrInsertCaseExtensionitemValues updateOrInsertCaseExtensionitemValues = new UpdateOrInsertCaseExtensionitemValues();
    // 自動採番のID
    // TODO(自动采番的最大ID)
    String maxId = caseExtensionitemValuesMapper.selectMaxId();
    // ID
    updateOrInsertCaseExtensionitemValues.setId(maxId + 1);
    // プラットフォームID
    updateOrInsertCaseExtensionitemValues.setPlatformId(platformId);
    // 案件ID
    updateOrInsertCaseExtensionitemValues.setCaseId(cid);
    // 申立てID
    updateOrInsertCaseExtensionitemValues.setCaseId(id);
    // 拡張項目ID
    updateOrInsertCaseExtensionitemValues.setExtensionitemId(extensionItem.getExtensionitemId());
    // 拡張項目値
    updateOrInsertCaseExtensionitemValues.setExtensionitemValue(extensionItem.getExtensionitemValue());
    // 删除Flag
    updateOrInsertCaseExtensionitemValues.setDeleteFlag(deleteFlag0);
    // LastModifiedDate
    updateOrInsertCaseExtensionitemValues.setLastModifiedDate(new Date());
    // LastModifiedBy
    updateOrInsertCaseExtensionitemValues.setLastModifiedBy(uid);
    // case_extensionitem_valuesの更新
    caseExtensionitemValuesMapper.insertCaseExtensionitemValues(updateOrInsertCaseExtensionitemValues);
  }
}
