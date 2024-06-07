package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.app.mapper.InsRelationsTempMapper;
import com.web.app.service.MosLoginService;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.HashMap;
import com.web.app.domain.Entity.MasterPlatforms;
import com.web.app.domain.Entity.OdrUsers;
import com.web.app.domain.Entity.CaseExtensionitemValues;
import com.web.app.domain.Entity.CaseFileRelations;
import com.web.app.domain.Entity.CasePetitions;
import com.web.app.domain.Entity.CaseRelations;
import com.web.app.domain.Entity.Files;
import com.web.app.domain.MosLogin.ExpandItems;
import com.web.app.domain.MosLogin.FileId;
import com.web.app.domain.MosLogin.GetPetitionTemp;
import com.web.app.domain.MosLogin.GetPlatform;
import com.web.app.domain.MosLogin.PetitionTemp;
import com.web.app.domain.MosLogin.Relations;
import com.web.app.domain.MosLogin.ScaleItems;
import com.web.app.domain.MosLogin.ScreenInfo;
import com.web.app.domain.MosLogin.SessionInfo;
import com.web.app.mapper.GetPetitionsTempMapper;
import com.web.app.mapper.GetPlatformMapper;
import com.web.app.mapper.InsRepliesTempMapper;
import com.web.app.service.UtilService;

/**
 * S8_申立登録画面
 * Service層実現類
 * MosLoginServiceImpl
 * 
 * @author DUC 閆文静 馮淑慧
 * @since 2024/04/23
 * @version 1.0
 */
@Service
public class MosLoginServiceImpl implements MosLoginService {
    @Autowired
    private UtilService utilService;

    @Autowired
    private GetPlatformMapper getPlatformMapper;

    @Autowired
    private GetPetitionsTempMapper getPetitionsTempMapper;

    @Autowired
    private InsRepliesTempMapper insRepliesTempMapper;

    @Autowired
    private InsRelationsTempMapper insRelationsTempMapper;
    // API_下書き用準備データ登録の戻り値設定
    Relations relations = new Relations();

    /**
     * TBL「申立（case_petitions）」の新規登録
     * TBL「案件別個人情報リレーション（case_relations）」の新規登録
     * 
     * @param uuId      自動採番
     * @param loginUser ログインユーザ
     * @param userId    セッション.ユーザID
     */
    @Override
    public int insRelationsTemp(String uuId, String loginUser, String userId) {
        // TBL「申立（case_petitions）」の新規登録
        int insCasePetitionsNum = insRelationsTempMapper.insCasePetitions(uuId, loginUser);
        // TBL「案件別個人情報リレーション（case_relations）」の新規登録
        int insCaseRelationsNum = insRelationsTempMapper.insCaseRelations(uuId, loginUser, userId);
        if(insCasePetitionsNum == 1 && insCaseRelationsNum == 1) {
            // 戻り値として設定
            // case_petitions.id
            relations.setUuId(uuId);
            // case_relations.PetitionUserId
            relations.setUserId(userId);
            return 1;
        }
        return 0;
    }

    /**
     * API_画面制御表示項目取得
     * 申立て登録画面の「申立ての種類」と「希望する解決方法」の選択肢の内容を表示するために、種類マスタから対応する内容を取得する。
     * プラットフォームマスタより画面制御表示項目の表示状態を取得する。
     *
     * @param sessionId セッション.ユーザID
     * @return GetPlatform
     *         申立て登録画面の「申立ての種類」と「希望する解決方法」の選択肢の内容、画面制御表示項目の表示状態、拡張項目の内容を表示する
     */
    @Override
    public GetPlatform getPlatform(SessionInfo sessionInfo) {

        // 申立ての種類の表示内容の取得List
        List<String> petitionTypeList = new ArrayList<>();
        // 希望する解決方法の表示内容の取得List
        List<String> resloveTypeList = new ArrayList<>();
        // 画面制御表示項目取得
        GetPlatform itemsResult = new GetPlatform();

        // ユーザ情報取得
        OdrUsers odrUsers = getPlatformMapper.selectOdrUsers(sessionInfo.getSessionId());
        // ユーザ情報取得有りの場合、下記処理を行う）
        if (odrUsers.getPlatformId() != null) {

            String PetitionType = "PetitionType";
            String ResloveType = "ResloveType";

            // TBL「種類マスタ」より申立種類取得
            petitionTypeList = getPlatformMapper.masterTypesSearch(odrUsers.getPlatformId(), PetitionType);

            // master_types.DisplayNameを配列に設定して、master_types.OrderNo.でソート（昇順）して、画面の「申立ての種類」の選択肢に表示する。
            itemsResult.setPetitionTypeDisplayName(petitionTypeList);

            // TBL「種類マスタ」より希望する解決方法取得
            resloveTypeList = getPlatformMapper.masterTypesSearch(odrUsers.getPlatformId(), ResloveType);

            // master_types.DisplayNameを配列に設定して、master_types.OrderNo.でソート（昇順）して、画面の「希望する解決方法」の選択肢に表示する。
            itemsResult.setResloveTypeDisplayName(resloveTypeList);

            // 画面制御表示項目表示状態取得
            MasterPlatforms result1 = getPlatformMapper.masterPlatformsSearch(odrUsers.getPlatformId());
            if (result1 != null) {
                // 取得した項目を戻す
                // 商品ＩＤ表示状態
                itemsResult.setUserProductId(result1.getUserProductId());
                // 販売者名表示状態
                itemsResult.setUseTraderName(result1.getUseTraderName());
                // 販売者ＵＲＬ表示状態
                itemsResult.setUseProductUrl(result1.getUseProductUrl());
                // 拡張項目の表示状態
                itemsResult.setUseOther(result1.getUseOther());

                if (result1.getUseOther() == 1) {
                    // 拡張項目の取得
                    List<ExpandItems> expandProjectList = getPlatformMapper
                            .expandProjectSearch(sessionInfo.getPlatformId());
                    if (expandProjectList.size() > 0) {
                        // 取得した項目を戻す
                        // 画面表示項目.拡張項目
                        itemsResult.setExpandItems(expandProjectList);
                    }

                }
            }
        }

        return itemsResult;
    }

    /**
     * API_申立て下書き保存データ取得
     * TBL「案件別個人情報リレーション（case_relations）」とTBL「申立（case_petitions）」より関連ユーザの下書き保存のデータを取得する。
     * TBL「ユーザ（odr_users）」より申立人情報を取得する
     * TBL「案件-添付ファイルリレーション（case_file_relations）」より関連下書き案件のファイルIDを取得する。
     * TBL「拡張項目設定値（case_extensionitem_values）」とTBL「案件-拡張項目-関連表（case_extensionitem_relations）」より拡張項目内容を取得する。
     *
     * @param sessionInfo セッション.ユーザID セッション.PlatformId
     * @return GetPetitionTemp
     *         取得した下書き保存データを画面に表示する。
     */
    @Override
    public GetPetitionTemp getPetitionsTemp(SessionInfo sessionInfo) {
        // 申立て下書き保存データ取得内容
        GetPetitionTemp petitionInfo = new GetPetitionTemp();
        // 申立人情報取得内容
        OdrUsers getPetitionInfo = new OdrUsers();

        // TBL「案件別個人情報リレーション（case_relations）」とTBL「申立（case_petitions）」より関連ユーザの下書き保存のデータを取得する。
        PetitionTemp petitionsTemp = getPetitionsTempMapper.selectPetitionsTemp(sessionInfo.getSessionId());
        if (petitionsTemp == null) {
            return null;
        }
        // 申立て人
        petitionInfo.setPetitionUserId(petitionsTemp.getPetitionUserId());
        // 申立Id
        petitionInfo.setCasePetition(petitionsTemp.getCasePetition());
        // 画面表示項目.メールアドレス
        petitionInfo.setPetitionUserInfo_Email(petitionsTemp.getPetitionUserInfo_Email());
        // 画面表示項目.代理人1メールアドレス
        petitionInfo.setAgent1_Email(petitionsTemp.getAgent1_Email());
        // 画面表示項目.代理人2メールアドレス
        petitionInfo.setAgent2_Email(petitionsTemp.getAgent2_Email());
        // 画面表示項目.代理人3メールアドレス
        petitionInfo.setAgent3_Email(petitionsTemp.getAgent3_Email());
        // 画面表示項目.代理人4メールアドレス
        petitionInfo.setAgent4_Email(petitionsTemp.getAgent4_Email());
        // 画面表示項目.代理人5メールアドレス
        petitionInfo.setAgent5_Email(petitionsTemp.getAgent5_Email());
        // 画面表示項目.販売者メールアドレス
        petitionInfo.setTraderUserEmail(petitionsTemp.getTraderUserEmail());
        // 画面表示項目.購入商品
        petitionInfo.setProductName(petitionsTemp.getProductName());
        // 画面表示項目.商品ID
        petitionInfo.setProductId(petitionsTemp.getProductId());
        // 画面表示項目.販売者
        petitionInfo.setTraderName(petitionsTemp.getTraderName());
        // 画面表示項目.販売者ＵＲＬ
        petitionInfo.setTraderUrl(petitionsTemp.getTraderUrl());
        // 画面表示項目.購入日
        petitionInfo.setBoughtDate(petitionsTemp.getBoughtDate());
        // 画面表示項目.購入金額
        petitionInfo.setPrice(petitionsTemp.getPrice());
        // 画面表示項目.申立ての種類
        petitionInfo.setPetitionTypeValue(petitionsTemp.getPetitionTypeValue());
        // 画面表示項目.申立て内容
        petitionInfo.setPetitionContext(petitionsTemp.getPetitionContext());
        // 画面表示項目.希望する解決方法
        petitionInfo.setExpectResloveTypeValue(petitionsTemp.getExpectResloveTypeValue());
        // 画面表示項目.その他
        petitionInfo.setOther(petitionsTemp.getOther());

        // TBL「ユーザ（odr_users）」より申立人情報を取得する
        if (petitionsTemp.getPetitionUserInfo_Email() != null) {
            // 申立人情報を取得する
            getPetitionInfo = getPetitionsTempMapper.selectOdrUsers(sessionInfo.getSessionId(),
                    petitionsTemp.getPetitionUserInfo_Email());
            if (getPetitionInfo == null) {
                return petitionInfo;
            }
            // 画面表示項目.お問い合わせをされる方についての情報の氏名の名
            petitionInfo.setFirstName(getPetitionInfo.getFirstName());
            // 画面表示項目.お問い合わせをされる方についての情報の氏名の姓
            petitionInfo.setLastName(getPetitionInfo.getLastName());
            // 画面表示項目.お問い合わせをされる方についての情報の氏名（カナ）の名
            petitionInfo.setFirstName_kana(getPetitionInfo.getFirstName_kana());
            // 画面表示項目.お問い合わせをされる方についての情報の氏名（カナ）の姓
            petitionInfo.setLastName_kana(getPetitionInfo.getLastName_kana());
            // 画面表示項目.お問い合わせをされる方についての情報の所属会社
            petitionInfo.setCompanyName(getPetitionInfo.getCompanyName());

            // 上記画面表示項目（画面上で必須となっている項目）取得有り（Nullでない）場合、下記の下書き案件のファイルIDと拡張項目内容取得を行う。
            if (petitionsTemp.getProductName() != null && getPetitionInfo.getFirstName() != null) {
                // TBL「案件-添付ファイルリレーション（case_file_relations）」より関連下書き案件のファイルIDを取得する。
                List<FileId> getFileId = getPetitionsTempMapper.selectFileId(petitionsTemp.getCasePetition());
                if (getFileId.size() > 0) {
                    // 画面表示項目.添付資料
                    petitionInfo.setFileName(getFileId);
                }
                // 拡張項目内容取得
                List<ScaleItems> scaleItemsList = getPetitionsTempMapper.scaleItemsSearch(sessionInfo.getPlatformId());
                if (scaleItemsList.size() > 0) {
                    // 画面表示項目.拡張項目
                    petitionInfo.setPetitionTypeDisplayName(scaleItemsList);
                }
            }
        }
        return petitionInfo;
    }

    /**
     * API_申立て下書きデータ登録
     * 「下書き保存」ボタンを押下するたびに、画面に入力した内容を下書き保存のデータとしてDBへ反映する。
     * 「下書き保存」ボタン押下のたびに、テーブルに該当する下書き保存のデータを上書きする。
     *
     * @param screenInfo 画面に入力した内容
     * @return int 申立て下書きデータ登録成功の件数
     */
    @Override
    public int insRepliesTemp(ScreenInfo screenInfo) {
        // システム日付(現時刻のUTC時間を取得)
        Instant now = Instant.now();
        // 日付書式「yyyy/MM/dd HH:mm:ss」設定
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        // 日付書式「yyyy/MM/dd HH:mm:ss」後UTC時間
        String sysDate = now.atOffset(ZoneOffset.UTC).format(formatter);
        System.out.println("システム日付" + sysDate);

        // ⓵ユーザ情報の取得
        OdrUsers userInfo = insRepliesTempMapper.selectOdrUsers(screenInfo.getSessionId());

        // API「下書き用準備データ登録」の戻り値
        String case_petitions_id = relations.getUuId();
        String case_relations_PetitionUserId = relations.getUserId();

        if (case_petitions_id != null && case_relations_PetitionUserId != null) {
            // ⓶共通関数「TBL「申立（case_petitions）」の更新」
            int updateCasePetitionsNum = updateCasePetitions(screenInfo, case_petitions_id, userInfo);
            if (updateCasePetitionsNum == 0) {
                return 0;
            }
            // ⓷共通関数「TBL「案件別個人情報リレーション（case_relations）」の更新」
            int updateCaseRelationsNum = updateCaseRelations(case_petitions_id, case_relations_PetitionUserId, userInfo,
                    screenInfo);
            if (updateCaseRelationsNum == 0) {
                return 0;
            }

            // ⓸添付ファイル関連情報の初期化
            // a.案件-添付ファイルリレーションの取得
            String fildId = insRepliesTempMapper.caseFileRelationsSelect(case_petitions_id);
            // ｂ.上記取得有りの場合は関連のデータを初期化する
            if (fildId != null) {
                // TBL「添付ファイル（files）」を論理削除する
                insRepliesTempMapper.filesDelete(fildId);

                // TBL「案件-添付ファイルリレーション（case_file_relations）」を論理削除する
                insRepliesTempMapper.caseFileRelationsDelete(case_petitions_id);
            }

            if (screenInfo != null && screenInfo.getFileSize() != 0) {
                // ⓹画面上添付ファイルがなくなるまで、TBL「添付ファイル（files）」を新規登録する。
                // 自動採番のid（Guid取得）
                String fileMaxId1 = utilService.GetGuid();

                // 共通関数「TBL「添付ファイル（files）」の登録」
                int insertNum1 = insertFiles(fileMaxId1, userInfo, screenInfo, sysDate);
                if (insertNum1 == 0) {
                    return 0;
                }

                // ⓺画面上添付ファイルがなくなるまで、TBL「案件-添付ファイルリレーション（case_file_relations）」を新規登録する。
                // 共通関数「TBL「案件-添付ファイルリレーション（case_file_relations）」の登録」
                int insertNum2 = insertCaseFileRelations(userInfo, case_petitions_id, fileMaxId1, sysDate, screenInfo);
                if (insertNum2 == 0) {
                    return 0;
                }
            }
            // ⓻画面上拡張項目がある場合、TBLの内容を追加・または更新します。
            if (screenInfo.getPetitionTypeDisplayName().size() > 0) {
                // 画面上拡張項目遍历
                for (int i = 0; i < screenInfo.getPetitionTypeDisplayName().size(); i++) {
                    // 検索条件用数据初期化
                    CaseExtensionitemValues CaseExtensionitemValues1 = new CaseExtensionitemValues();
                    // プラットフォームID
                    CaseExtensionitemValues1.setPlatformId(screenInfo.getPlatformId());
                    // 申立てID
                    CaseExtensionitemValues1.setCase_petitionId(case_petitions_id);
                    // 拡張項目ID
                    CaseExtensionitemValues1
                            .setExtensionitemId(screenInfo.getPetitionTypeDisplayName().get(i).getExtensionitemId());

                    // 既存の拡張項目内容を取得
                    ScaleItems scaleItems = insRepliesTempMapper.selectScaleItemIdValue(CaseExtensionitemValues1);

                    // 取得したデータがある場合、データを更新
                    if (scaleItems != null) {
                        // 更新用数据初期化
                        CaseExtensionitemValues CaseExtensionitemValues2 = new CaseExtensionitemValues();
                        // プラットフォームID
                        CaseExtensionitemValues2.setPlatformId(screenInfo.getPlatformId());
                        // 申立てID
                        CaseExtensionitemValues2.setCase_petitionId(case_petitions_id);
                        // 拡張項目ID
                        CaseExtensionitemValues2
                                .setExtensionitemId(
                                        screenInfo.getPetitionTypeDisplayName().get(i).getExtensionitemId());
                        // 拡張項目値
                        CaseExtensionitemValues2.setExtensionitemValue(
                                screenInfo.getPetitionTypeDisplayName().get(i).getExtensionitemValue());
                        // 上次修改日期
                        CaseExtensionitemValues2.setLastModifiedDate(sysDate);
                        // 上次修改者
                        CaseExtensionitemValues2.setLastModifiedBy(screenInfo.getSessionId());

                        // TBL「拡張項目設定値（case_extensionitem_values）」の更新
                        int updateNum3 = insRepliesTempMapper.updateCaseExtensionitemValues(CaseExtensionitemValues2);
                        if (updateNum3 == 0) {
                            return 0;
                        }
                        // 取得したデータがないの場合、データを登録
                    } else {
                        // 自動採番のid（Guid取得）
                        String caseExtensionitemValuesMaxId1 = utilService.GetGuid();
                        // 更新用数据初期化
                        CaseExtensionitemValues CaseExtensionitemValues3 = new CaseExtensionitemValues();
                        // ID
                        CaseExtensionitemValues3.setId(caseExtensionitemValuesMaxId1);
                        // プラットフォームID
                        CaseExtensionitemValues3.setPlatformId(screenInfo.getPlatformId());
                        // 申立てID
                        CaseExtensionitemValues3.setCase_petitionId(case_petitions_id);
                        // 拡張項目ID
                        CaseExtensionitemValues3.setExtensionitemId(
                                screenInfo.getPetitionTypeDisplayName().get(i).getExtensionitemId());
                        // 拡張項目値
                        CaseExtensionitemValues3.setExtensionitemValue(
                                screenInfo.getPetitionTypeDisplayName().get(i).getExtensionitemValue());
                        // 上次修改日期
                        CaseExtensionitemValues3.setLastModifiedDate(sysDate);
                        // 上次修改者
                        CaseExtensionitemValues3.setLastModifiedBy(screenInfo.getSessionId());

                        // TBL「拡張項目設定値（case_extensionitem_values）」の登録
                        int insertNum3 = insRepliesTempMapper.insertCaseExtensionitemValues(CaseExtensionitemValues3);
                        if (insertNum3 == 0) {
                            return 0;
                        }
                    }
                }
            }
            return 1;
        }
        return 0;
    }

    /**
     * API_販売者・商品情報仮取得
     * 画面項目の「商品ＩＤ」に入力した値をキーに、本APIに固定値で設定している購入商品、販売者、販売者メールアドレスを取得する。
     * 取得無しの場合は空値を返す。
     *
     * @param goodsId 画面.商品ID
     * @return goodsInfo 販売者・商品情報仮取得内容
     */
    @Override
    public HashMap<String, String> getGoodsInfo(String goodsId) {
        // 販売者・商品情報仮取得内容初期化
        HashMap<String, String> goodsInfo = new HashMap<>();
        // 商品辞書の内容初期化
        HashMap<String, String> goodsName = new HashMap<>();
        // 販売者メアド辞書の内容初期化
        HashMap<String, String> sellerEmail = new HashMap<>();
        // 販売者辞書の内容初期化
        HashMap<String, String> sellerName = new HashMap<>();

        // 取得した商品名
        String goodsname;
        // 取得した販売者メールアドレス
        String email;
        // 取得した販売者名
        String name;

        // ①商品辞書の定義
        goodsName.put("SH0001", "ツヤ肌サプリメント");
        goodsName.put("SH0002", "ダウンコード");
        goodsName.put("SH0003", "パンホームベーカリー");
        goodsName.put("SH0004", "美白肌ファンテーション");
        goodsName.put("SH0005", "書きやすいボールペン");

        // ②販売者メアド辞書の定義
        // sellerEmail.put("SH0001", "aaa.111@yahoo.co.jp");
        sellerEmail.put("SH0002", "bbb.222@gmail.com");
        sellerEmail.put("SH0003", "ccc.333@qq.com");
        sellerEmail.put("SH0004", "ddd.444@gmail.com");
        sellerEmail.put("SH0005", "eee.555@icloud.com");

        // ③販売者辞書の定義
        sellerName.put("aaa.111@yahoo.co.jp", "渡部　順");
        sellerName.put("bbb.222@gmail.com", "大野　花子");
        sellerName.put("ccc.333@qq.com", "さくら　商店");
        sellerName.put("ddd.444@gmail.com", "ABCマート");
        sellerName.put("eee.555@icloud.com", "ドン・キホーテ");

        // ④画面.商品IDをキーに商品辞書から対応する商品名を取得する
        goodsname = goodsName.get(goodsId);
        // ⑤画面.商品IDをキーに販売者メアド辞書から対応する販売者メールアドレスを取得する
        email = sellerEmail.get(goodsId);
        // ⑤で取得した販売者メアド辞書.販売者メールアドレスをキーに販売者辞書から販売者名を取得する。
        name = sellerName.get(email);

        // 販売者・商品情報仮取得内容
        goodsInfo.put("goodsName", goodsname);
        goodsInfo.put("sellerEmail", email);
        goodsInfo.put("sellerName", name);

        return goodsInfo;
    }

    /**
     * 共通関数「TBL「申立（case_petitions）」の更新」
     *
     * @param ScreenInfo        画面に入力した内容
     * @param OdrUsers          取得したユーザ情報内容
     * @param case_petitions_id API「下書き用準備データ登録」戻ったcase_petitions.id
     * @return int TBL「申立（case_petitions）更新成功の件数
     */
    private int updateCasePetitions(ScreenInfo screenInfo, String case_petitions_id, OdrUsers userInfo) {
        // 共通関数「購入日のString日付をUTC時間に変換する」
        String dateString = stringToUtc(screenInfo.getCommoditydate());

        // 更新用数据初期化
        CasePetitions casePetitions = new CasePetitions();
        // ID
        casePetitions.setId(case_petitions_id);
        // プラットフォームID
        if (userInfo != null) {
            casePetitions.setPlatformId(userInfo.getPlatformId());
        }
        // 商品名
        casePetitions.setProductName(screenInfo.getCommodity());
        // 商品ID
        casePetitions.setProductId(screenInfo.getUserproductId());
        // 販売元名称
        casePetitions.setTraderName(screenInfo.getUsetraderName());
        // 販売元メールアドレス
        casePetitions.setTraderMail(screenInfo.getSellingelementemail());
        // 販売元ＵＲＬ
        casePetitions.setTraderUrl(screenInfo.getUseproductUrl());
        // 購入日
        casePetitions.setBoughtDate(dateString);
        // 購入金額
        casePetitions.setPrice(screenInfo.getPurchaseamount());
        // 申立ての種類
        casePetitions.setPetitionTypeValue(screenInfo.getPetitionKind());
        // 申立て内容
        casePetitions.setPetitionContext(screenInfo.getPetitioncontent());
        // 希望する解決方法
        casePetitions.setExpectResloveTypeValue(screenInfo.getDesiredsolutions());
        // その他
        casePetitions.setOther(screenInfo.getOther());

        // TBL「申立（case_petitions）」の更新
        int updateNum1 = insRepliesTempMapper.casePetitionsUpd(casePetitions);
        return updateNum1;
    }

    /**
     * 共通関数「TBL「案件別個人情報リレーション（case_relations）」の更新」
     *
     * @param case_petitions_id             API「下書き用準備データ登録」戻ったcase_petitions.id
     * @param case_relations_PetitionUserId API「下書き用準備データ登録」戻ったcase_relations_PetitionUserId
     * @param userInfo                      取得したユーザ情報内容
     * @param screenInfo                    画面に入力した内容
     * @return int TBL「案件別個人情報リレーション（case_relations）」の更新成功の件数
     */
    private int updateCaseRelations(String case_petitions_id, String case_relations_PetitionUserId, OdrUsers userInfo,
            ScreenInfo screenInfo) {
        // 自動採番のid（Guid取得）
        String id = utilService.GetGuid();
        // 更新用数据初期化
        CaseRelations caseRelations = new CaseRelations();
        // ID
        caseRelations.setId(id);
        // 申立Id
        caseRelations.setCasePetition(case_petitions_id);
        // 申立て人
        caseRelations.setPetitionUserId(case_relations_PetitionUserId);
        // プラットフォームID
        if (userInfo != null) {
            caseRelations.setPlatformId(userInfo.getPlatformId());
        }
        // 申立て人入力情報
        caseRelations.setPetitionUserInfo_Email(screenInfo.getUseremail());
        // 代理人1
        caseRelations.setAgent1_Email(screenInfo.getAgentemail1());
        // 代理人2
        caseRelations.setAgent2_Email(screenInfo.getAgentemail2());
        // 代理人3
        caseRelations.setAgent3_Email(screenInfo.getAgentemail3());
        // 代理人4
        caseRelations.setAgent4_Email(screenInfo.getAgentemail4());
        // 代理人5
        caseRelations.setAgent5_Email(screenInfo.getAgentemail5());
        // 相手方メール
        caseRelations.setTraderUserEmail(screenInfo.getSellingelementemail());

        // TBL「案件別個人情報リレーション（case_relations）」の更新
        int updateNum2 = insRepliesTempMapper.caseRelationsUpd(caseRelations);
        return updateNum2;
    }

    /**
     * 共通関数「TBL「添付ファイル（files）」の登録」
     *
     * @param fileMaxId1 自動採番のID
     * @param userInfo   取得したユーザ情報内容
     * @param screenInfo 画面に入力した内容
     * @param sysDate    システム日付
     * @return int TBL「添付ファイル（files）」の登録成功の件数
     */
    private int insertFiles(String fileMaxId1, OdrUsers userInfo, ScreenInfo screenInfo, String sysDate) {
        // 登録用数据初期化
        Files files = new Files();
        // ID
        files.setId(fileMaxId1);
        // プラットフォームID
        if (userInfo != null) {
            files.setPlatformId(userInfo.getPlatformId());
        }
        // ファイル名
        files.setFileName(screenInfo.getFileName());
        // 拡張子
        files.setFileExtension(screenInfo.getFileExtension());
        // URL
        files.setFileUrl(screenInfo.getFileUrl());
        // ファイルサイズ
        files.setFileSize(screenInfo.getFileSize());
        // ユーザーID
        files.setRegisterUserId(screenInfo.getSessionId());
        // 登録日
        files.setRegisterDate(sysDate);
        // 上次修改日期
        files.setLastModifiedDate(sysDate);
        // 上次修改者
        files.setLastModifiedBy(screenInfo.getSessionId());

        int insertNum1 = insRepliesTempMapper.insertFiles(files);
        return insertNum1;
    }

    /**
     * 共通関数「TBL「案件-添付ファイルリレーション（case_file_relations）」の登録」
     *
     * @param userInfo          取得したユーザ情報内容
     * @param case_petitions_id API「下書き用準備データ登録」戻ったcase_petitions.id
     * @param fileMaxId1        自動採番のID
     * @param sysDate           システム日付
     * @param screenInfo        画面に入力した内容
     * @return int TBL「案件-添付ファイルリレーション（case_file_relations）」の登録成功の件数
     */
    private int insertCaseFileRelations(OdrUsers userInfo, String case_petitions_id, String fileMaxId1, String sysDate,
            ScreenInfo screenInfo) {

        // 自動採番のid（Guid取得）
        String caseFileRelationsMaxId1 = utilService.GetGuid();
        // 登録用数据初期化
        CaseFileRelations caseFileRelations = new CaseFileRelations();
        // ID
        caseFileRelations.setId(caseFileRelationsMaxId1);
        // プラットフォームID
        if (userInfo != null) {
            caseFileRelations.setPlatformId(userInfo.getPlatformId());
        }
        // 案件種類ID
        caseFileRelations.setRelatedId(case_petitions_id);
        // ファイルID
        caseFileRelations.setFileId(fileMaxId1);
        // 上次修改日期
        caseFileRelations.setLastModifiedDate(sysDate);
        // 上次修改者
        caseFileRelations.setLastModifiedBy(screenInfo.getSessionId());

        int insertNum2 = insRepliesTempMapper.insertCaseFileRelations(caseFileRelations);
        return insertNum2;
    }

    /**
     * 共通関数「購入日のString日付をUTC時間に変換する」
     *
     * @param Commoditydate 変換前のString日付
     * @return String 変換後のUTC時間
     */
    private String stringToUtc(String Commoditydate) {
        try {
            // String日付をDate日付に変換
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = dateFormat.parse(Commoditydate);

            // Date日付をUTC時間に変換
            SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            String utcDateString = utcFormat.format(date);

            System.out.println(utcDateString);
            return utcDateString;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
