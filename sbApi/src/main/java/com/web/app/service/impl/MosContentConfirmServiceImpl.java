package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.app.domain.Entity.ActionHistories;
import com.web.app.domain.MosContentConfirm.ExtensionItem;
import com.web.app.domain.MosContentConfirm.IdPetitionUserId;
import com.web.app.domain.MosContentConfirm.InsertCaseFileRelations;
import com.web.app.domain.MosContentConfirm.InsertCases;
import com.web.app.domain.MosContentConfirm.InsertFiles;
import com.web.app.domain.MosContentConfirm.S09ScreenIntelligence;
import com.web.app.domain.MosContentConfirm.UpdateCasePetitions;
import com.web.app.domain.MosContentConfirm.UpdateCaseRelations;
import com.web.app.domain.MosContentConfirm.UpdateOrInsertCaseExtensionitemValues;
import com.web.app.domain.MosContentConfirm.UserLanguageIdPlatformId;
import com.web.app.mapper.InsPetitionsDataMapper;
import com.web.app.service.MosContentConfirmService;
import com.web.app.service.CommonService;
import com.web.app.service.UtilService;

/**
 * 申立て情報登録
 * 
 * @author DUC 王魯興
 * @since 2024/05/28
 * @version 1.0
 */
@Service
public class MosContentConfirmServiceImpl implements MosContentConfirmService {

  @Autowired
  private InsPetitionsDataMapper insPetitionsDataMapper;
  @Autowired
  private UtilService utilService;
  @Autowired
  private CommonService commonService;

  /**
   * 申立て情報登録
   *
   * @param s09ScreenIntelligence パラメータの説明内容
   * @return
   * @throws Exception
   */
  @Override
  public Integer LoginIntelligence(S09ScreenIntelligence s09ScreenIntelligence) {

    // 自動採番のcid（CaseId）
    String cid = null;

    // 自動採番のid（Id）
    String id = null;

    // 案件種類
    int relationType = 0;

    // 削除Flag1
    short deleteFlag1 = 1;

    // 削除Flag0
    short deleteFlag0 = 0;

    // ログイン戻り値
    int returnFlag = 0;

    // 販売者メアド登録有無FLG
    int salesBrokerFlg = 0;

    // 1.TBL「案件別個人情報リレーション（case_relations）」と「申立（case_petitions）」より用意した下書き保存データを取得する。
    IdPetitionUserId idPetitionUserId = insPetitionsDataMapper.selectIdPetitionUserId(s09ScreenIntelligence.getUid());

    // 2.ユーザ情報の取得
    UserLanguageIdPlatformId userLanguageIdPlatformId = insPetitionsDataMapper
        .selectLanguageIdAndPlatformId(s09ScreenIntelligence.getUid());

    // 自動採番のcid（CaseId）
    cid = utilService.GetGuid();
    // 3.TBL「案件（cases）」の新規登録
    returnFlag = insertCases(returnFlag, s09ScreenIntelligence, cid, userLanguageIdPlatformId);

    // 案件別個人情報リレーション（case_relations）のMAX（id）の取得
    id = utilService.GetGuid();

    // 4.TBL「案件別個人情報リレーション（case_relations）」の更新
    returnFlag = updateCaseRelations(returnFlag, id, cid, userLanguageIdPlatformId, s09ScreenIntelligence,
        idPetitionUserId);

    // 5.TBL「申立（case_petitions）」の更新
    returnFlag = updateCasePetitions(returnFlag, idPetitionUserId, cid, s09ScreenIntelligence,
        userLanguageIdPlatformId);

    // 6.a案件-添付ファイルリレーションの取得
    List<String> fileIdList = insPetitionsDataMapper.selectFileId(relationType, idPetitionUserId.getId());

    // 6.b上記取得(案件-添付ファイルリレーションの取得)有りの場合は関連のデータを初期化する
    if (fileIdList.size() != 0) {
      for (int i = 0; i < fileIdList.size(); i++) {
        // TBL「添付ファイル（files）」を論理削除する
        insPetitionsDataMapper.updateDeleteFlag(deleteFlag1, fileIdList.get(i));

        // TBL「案件-添付ファイルリレーション（case_file_relations）」を論理削除する
        insPetitionsDataMapper.updateDeleteFlag(deleteFlag1, id);
      }
    }

    // 7.画面に添付資料が添付有りの場合、添付資料がなくなるまで以下の処理を行う。
    if (s09ScreenIntelligence.getFileSize() != 0) {

      // 自動採番のid（Id）
      String fileMaxId = utilService.GetGuid();

      // a.TBL「添付ファイル（files）」を新規登録する
      returnFlag = insertFiles(returnFlag, fileMaxId, userLanguageIdPlatformId, cid, s09ScreenIntelligence,
          s09ScreenIntelligence.getUid(), deleteFlag0);
      // b.TBL「案件-添付ファイルリレーション（case_file_relations）」を新規登録する
      returnFlag = insertCaseFileRelations(returnFlag, userLanguageIdPlatformId, relationType, cid, idPetitionUserId,
          fileMaxId, s09ScreenIntelligence, s09ScreenIntelligence.getUid(), deleteFlag0);
    }
    // 8.③～⑦の登録処理が正常終了の場合、アクション履歴登録を行う
    // TODO
    if (returnFlag == 0) {
      // ActionHistories
      ActionHistories actionHistories = new ActionHistories();
      // 共通API调用
      commonService.InsertActionHistories(actionHistories, null, false, true);
    }

    // 9.販売者メールアドレス登録有無の判定
    int numberOfPieces = insPetitionsDataMapper.selectCount(s09ScreenIntelligence.getTraderMail(),
        userLanguageIdPlatformId.getPlatformId(), deleteFlag0);
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
        ExtensionItem extensionItem = insPetitionsDataMapper.selectExtensionitemIdExtensionitemValue(
            s09ScreenIntelligence.getPlatformId(), idPetitionUserId.getId(), deleteFlag0,
            s09ScreenIntelligence.getExtensionItem().get(i).getExtensionitemId());
        // すべてのデータ
        if (extensionItem != null) {
          extensionItemList.add(extensionItem);
        }
      }
      // 取得したデータがある場合、2)へ(データを更新)
      if (extensionItemList.size() > 0) {
        for (int i = 0; i < extensionItemList.size(); i++) {
          // データを更新
          updateCaseExtensionitemValues(extensionItemList.get(i), deleteFlag0, s09ScreenIntelligence.getUid(), cid,
              s09ScreenIntelligence.getPlatformId(), id);
        }
      } else {
        for (int i = 0; i < s09ScreenIntelligence.getExtensionItem().size(); i++) {
          // データを更新
          insertCaseExtensionitemValues(s09ScreenIntelligence.getExtensionItem().get(i), deleteFlag0,
              s09ScreenIntelligence.getUid(), cid, s09ScreenIntelligence.getPlatformId(), id);
        }
      }
    }
    // 販売者メアド登録有無FLG戻り
    return salesBrokerFlg;
  }

  /**
   * TBL「案件（cases）」の新規登録
   *
   * @param returnFlag               ログイン戻り値
   * @param s09ScreenIntelligence    画面の項目
   * @param cid                      CID
   * @param userLanguageIdPlatformId ユーザ情報
   * @return ログイン戻り値
   * @throws Exception
   */
  public int insertCases(int returnFlag, S09ScreenIntelligence s09ScreenIntelligence, String cid,
      UserLanguageIdPlatformId userLanguageIdPlatformId) {
    // 登录用数据初始化
    InsertCases insertCases = new InsertCases();
    // ID
    insertCases.setCid(cid);
    // プラットフォームID
    if (userLanguageIdPlatformId != null) {
      insertCases.setPlatformId(userLanguageIdPlatformId.getPlatformId());
    }
    // 案件ステージ
    insertCases.setCaseStage("0");
    // 案件ステータス
    insertCases.setCaseStatus("0000");
    // タイトル名
    // TODO(希望解决方法应该是list，应该判断一下有几个值)
    insertCases
        .setCaseTitle(s09ScreenIntelligence.getProductName() + s09ScreenIntelligence.getExpectResloveTypeValue());
    // 申立て日
    insertCases.setPetitionDate(new Date());
    // 利用言語
    if (userLanguageIdPlatformId != null) {
      insertCases.setLanguageId(userLanguageIdPlatformId.getLanguageId());
    }
    // 回答開始日
    insertCases.setReplyStartDate(new Date());
    // 回答期限日
    // TODO(システム日付 + master_platforms.ReplyLimitDays 加到时分秒哪一个上面？)
    insertCases.setReplyEndDate(new Date());
    // TBL「案件（cases）」の新規登録
    returnFlag = insPetitionsDataMapper.insertCases(insertCases);
    return returnFlag;
  }

  /**
   * TBL「案件別個人情報リレーション（case_relations）」の更新
   *
   * @param returnFlag               ログイン戻り値
   * @param id                       自動のID
   * @param cid                      CID
   * @param userLanguageIdPlatformId ユーザ情報
   * @param s09ScreenIntelligence    画面の項目
   * @param idPetitionUserId         case_petitionsのIDとPetitionUserId
   * @return ログイン戻り値
   * @throws Exception
   */
  private int updateCaseRelations(int returnFlag, String id, String cid,
      UserLanguageIdPlatformId userLanguageIdPlatformId, S09ScreenIntelligence s09ScreenIntelligence,
      IdPetitionUserId idPetitionUserId) {
    // 「案件別個人情報リレーション（case_relations）」更新用数据初始化
    UpdateCaseRelations updateCaseRelations = new UpdateCaseRelations();
    // ID
    updateCaseRelations.setId(id);
    // 案件ID
    updateCaseRelations.setCaseId(cid);
    // プラットフォームID
    if (userLanguageIdPlatformId != null) {
      updateCaseRelations.setPlatformId(userLanguageIdPlatformId.getPlatformId());
    }
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
    // 申立Id
    updateCaseRelations.setCasePetitions(idPetitionUserId.getId());
    // 申立て人
    updateCaseRelations.setPetitionUserId(idPetitionUserId.getPetitionUserId());
    // TBL「案件別個人情報リレーション（case_relations）」の更新
    returnFlag = insPetitionsDataMapper.updateCaseRelations(updateCaseRelations);
    return returnFlag;
  }

  /**
   * TBL「申立（case_petitions）」の更新
   *
   * @param returnFlag               ログイン戻り値
   * @param idPetitionUserId         case_petitionsのIDとPetitionUserId
   * @param cid                      CID
   * @param s09ScreenIntelligence    画面の項目
   * @param userLanguageIdPlatformId ユーザ情報
   * @return ログイン戻り値
   * @throws Exception
   */
  private int updateCasePetitions(int returnFlag, IdPetitionUserId idPetitionUserId, String cid,
      S09ScreenIntelligence s09ScreenIntelligence, UserLanguageIdPlatformId userLanguageIdPlatformId) {
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
    // ID
    updateCasePetitions.setId(idPetitionUserId.getId());

    // TBL「申立（case_petitions）」の更新
    returnFlag = insPetitionsDataMapper.updateCasePetitions(updateCasePetitions);
    return returnFlag;
  }

  /**
   * TBL「添付ファイル（files）」を新規登録する
   *
   * @param returnFlag               ログイン戻り値
   * @param fileMaxId                自動採番のid
   * @param userLanguageIdPlatformId ユーザ情報
   * @param cid                      CID
   * @param s09ScreenIntelligence    画面の項目
   * @param uid                      UID
   * @param DeleteFlag0              削除Flag0
   * @return ログイン戻り値
   * @throws Exception
   */
  private int insertFiles(int returnFlag, String fileMaxId, UserLanguageIdPlatformId userLanguageIdPlatformId,
      String cid, S09ScreenIntelligence s09ScreenIntelligence, String uid, short DeleteFlag0) {

    // TBL「添付ファイル（files）」を新規登録用数据初始化
    InsertFiles insertFiles = new InsertFiles();
    // ID
    insertFiles.setId(fileMaxId);
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
    // 削除Flag
    insertFiles.setDeleteFlag(DeleteFlag0);
    // 最終変更日
    insertFiles.setLastModifiedDate(new Date());
    // 最終変更者
    insertFiles.setLastModifiedBy(uid);
    // TBL「添付ファイル（files）」を新規登録する
    returnFlag = insPetitionsDataMapper.insertFiles(insertFiles);
    return returnFlag;
  }

  /**
   * TBL「案件-添付ファイルリレーション（case_file_relations）」を新規登録する
   *
   * @param returnFlag               ログイン戻り値
   * @param userLanguageIdPlatformId ユーザ情報
   * @param relationType             案件種類
   * @param cid                      CID
   * @param idPetitionUserId         case_petitionsのIDとPetitionUserId
   * @param fileMaxId                自動採番のid
   * @param s09ScreenIntelligence    画面の項目
   * @param uid                      UID
   * @param deleteFlag0              削除Flag0
   * @return ログイン戻り値
   * @throws Exception
   */
  private int insertCaseFileRelations(int returnFlag, UserLanguageIdPlatformId userLanguageIdPlatformId,
      int relationType, String cid, IdPetitionUserId idPetitionUserId, String fileMaxId,
      S09ScreenIntelligence s09ScreenIntelligence, String uid, short deleteFlag0) {

    // 自動採番のid（Id）
    String maxId = utilService.GetGuid();

    // TBL「案件-添付ファイルリレーション（case_file_relations）」を新規登録用数据初始化
    InsertCaseFileRelations insertCaseFileRelations = new InsertCaseFileRelations();
    // ID
    insertCaseFileRelations.setId(maxId);
    // プラットフォームID
    insertCaseFileRelations.setPlatformId(userLanguageIdPlatformId.getPlatformId());
    // 案件ID
    insertCaseFileRelations.setCaseId(cid);
    // 案件種類
    insertCaseFileRelations.setRelationType(relationType);
    // 案件種類ID
    insertCaseFileRelations.setRelatedId(idPetitionUserId.getId());
    // ファイルID
    insertCaseFileRelations.setFileId(fileMaxId);
    // 削除Flag
    insertCaseFileRelations.setDeleteFlag(deleteFlag0);
    // 最終変更日
    insertCaseFileRelations.setLastModifiedDate(new Date());
    // 最終変更者
    insertCaseFileRelations.setLastModifiedBy(uid);
    // TBL「案件-添付ファイルリレーション（case_file_relations）」を新規登録する
    returnFlag = insPetitionsDataMapper.insertCaseFileRelations(insertCaseFileRelations);
    return returnFlag;
  }

  /**
   * case_extensionitem_valuesの更新
   *
   * @param extensionItem 既存の拡張項目内容
   * @param deleteFlag0   削除Flag0
   * @param uid           UID
   * @param cid           CID
   * @param platformId    ユーザ情報
   * @param id            自動採番のid
   * @return ログイン戻り値
   * @throws Exception
   */
  private void updateCaseExtensionitemValues(ExtensionItem extensionItem, short deleteFlag0, String uid, String cid,
      String platformId, String id) {

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
    insPetitionsDataMapper.updateCaseExtensionitemValues(updateOrInsertCaseExtensionitemValues, platformId, id,
        deleteFlag0, extensionItem.getExtensionitemId());
  }

  /**
   * case_extensionitem_valuesの登録
   *
   * @param extensionItem 既存の拡張項目内容
   * @param deleteFlag0   削除Flag0
   * @param uid           UID
   * @param cid           CID
   * @param platformId    ユーザ情報
   * @param id            自動採番のid
   * @return
   * @throws Exception
   */
  private void insertCaseExtensionitemValues(ExtensionItem extensionItem, short deleteFlag0, String uid, String cid,
      String platformId, String id) {

    // case_extensionitem_values更新用数据初始化
    UpdateOrInsertCaseExtensionitemValues updateOrInsertCaseExtensionitemValues = new UpdateOrInsertCaseExtensionitemValues();
    // 自動採番のID
    String maxId = utilService.GetGuid();
    // ID
    updateOrInsertCaseExtensionitemValues.setId(maxId);
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
    // 削除Flag
    updateOrInsertCaseExtensionitemValues.setDeleteFlag(deleteFlag0);
    // LastModifiedDate
    updateOrInsertCaseExtensionitemValues.setLastModifiedDate(new Date());
    // LastModifiedBy
    updateOrInsertCaseExtensionitemValues.setLastModifiedBy(uid);
    // case_extensionitem_valuesの更新
    insPetitionsDataMapper.insertCaseExtensionitemValues(updateOrInsertCaseExtensionitemValues);
  }
}