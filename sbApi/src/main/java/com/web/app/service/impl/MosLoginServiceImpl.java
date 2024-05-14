package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.app.domain.Entity.MasterPlatforms;
import com.web.app.domain.Entity.MasterTypes;
import com.web.app.domain.Entity.OdrUsers;
import com.web.app.domain.MosLogin.ExpandItems;
import com.web.app.domain.MosLogin.FileId;
import com.web.app.domain.MosLogin.GetPetitionTemp;
import com.web.app.domain.MosLogin.GetPlatform;
import com.web.app.domain.MosLogin.PetitionTemp;
import com.web.app.domain.MosLogin.ScaleItems;
import com.web.app.domain.MosLogin.SessionInfo;
import com.web.app.mapper.GetPetitionsTempMapper;
import com.web.app.mapper.GetPlatformMapper;
import com.web.app.service.MosLoginService;

/**
 * S8_申立登録画面
 * Service層実現類
 * MosLoginServiceImpl
 * API_画面制御表示項目取得
 * 
 * @author DUC 馮淑慧
 * @since 2024/04/23
 * @version 1.0
 */
@Service
public class MosLoginServiceImpl implements MosLoginService {

    @Autowired
    private GetPlatformMapper getPlatformMapper;

    @Autowired
    private GetPetitionsTempMapper getPetitionsTempMapper;

    // 申立ての種類の表示内容の取得List
    List<String> petitionTypeList1 = new ArrayList<>();
    // 希望する解決方法の表示内容の取得List
    List<String> resloveTypeList1 = new ArrayList<>();
    // 画面制御表示項目表示状態の取得内容
    MasterPlatforms result1 = new MasterPlatforms();
    // 画面制御表示項目取得List
    GetPlatform itemsResultList1 = new GetPlatform();
    // 申立て下書き保存データ取得内容
    GetPetitionTemp petitionInfoList1 = new GetPetitionTemp();
    // TBL「案件別個人情報リレーション（case_relations）」とTBL「申立（case_petitions）」より関連ユーザの下書き保存のデータ取得内容
    PetitionTemp petitionsTemp = new PetitionTemp();
    // TBL「ユーザ（odr_users）」より申立人情報取得内容
    OdrUsers getPetitionInfo = new OdrUsers();

    /**
     * 申立て登録画面の「申立ての種類」と「希望する解決方法」の選択肢の内容を表示するために、種類マスタから対応する内容を取得する。
     * プラットフォームマスタより画面制御表示項目の表示状態を取得する。
     *
     * @param sessionId セッション.ユーザID
     * @return GetPlatform
     *         申立て登録画面の「申立ての種類」と「希望する解決方法」の選択肢の内容、画面制御表示項目の表示状態、拡張項目の内容を表示する
     */
    @Override
    public GetPlatform PlatformSearch(String sessionId) {

        // ユーザ情報取得
        OdrUsers odrUsers = getPlatformMapper.selectOdrUsers(sessionId);
        // ユーザ情報取得有りの場合、下記処理を行う）
        if (odrUsers.getPlatformId() != null) {

            // TBL「種類マスタ」より申立種類取得
            List<MasterTypes> petitionTypeList = getPlatformMapper.masterTypesSearch1(odrUsers.getPlatformId());

            // master_types.DisplayNameを配列に設定して、master_types.OrderNo.でソート（昇順）して、画面の「申立ての種類」の選択肢に表示する。
            if (petitionTypeList.size() > 0) {
                for (int i = 0; i < petitionTypeList.size(); i++) {
                    petitionTypeList1.add(petitionTypeList.get(i).getDisplayName());
                }
                itemsResultList1.setPetitionTypeDisplayName(petitionTypeList1);
            }

            // TBL「種類マスタ」より希望する解決方法取得
            List<MasterTypes> resloveTypeList = getPlatformMapper.masterTypesSearch2(odrUsers.getPlatformId());

            // master_types.DisplayNameを配列に設定して、master_types.OrderNo.でソート（昇順）して、画面の「希望する解決方法」の選択肢に表示する。
            if (resloveTypeList.size() > 0) {
                for (int i = 0; i < resloveTypeList.size(); i++) {
                    resloveTypeList1.add(resloveTypeList.get(i).getDisplayName());
                }
                itemsResultList1.setResloveTypeDisplayName(resloveTypeList1);
            }

            // 画面制御表示項目表示状態取得
            result1 = getPlatformMapper.masterPlatformsSearch(odrUsers.getPlatformId());
            if (result1 != null) {
                // 取得した項目を戻す
                // 商品ＩＤ表示状態
                itemsResultList1.setUserProductId(result1.getUserProductId());
                // 販売者名表示状態
                itemsResultList1.setUseTraderName(result1.getUseTraderName());
                // 販売者ＵＲＬ表示状態
                itemsResultList1.setUseProductUrl(result1.getUseProductUrl());
                // 拡張項目の表示状態
                itemsResultList1.setUseOther(result1.getUseOther());
            }
            if (result1.getUseOther() == 1) {
                // 拡張項目の取得
                List<ExpandItems> expandProjectList = getPlatformMapper.expandProjectSearch(odrUsers.getPlatformId());
                if (expandProjectList.size() > 0) {
                    // 取得した項目を戻す
                    // 画面表示項目.拡張項目
                    itemsResultList1.setExpandItems(expandProjectList);
                }

            }
        }

        return itemsResultList1;
    }

    /**
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
    public GetPetitionTemp petitionsTempSearch(SessionInfo sessionInfo) {

        // TBL「案件別個人情報リレーション（case_relations）」とTBL「申立（case_petitions）」より関連ユーザの下書き保存のデータを取得する。
        petitionsTemp = getPetitionsTempMapper.selectPetitionsTemp(sessionInfo.getSessionId());
        if (petitionsTemp != null) {
            // 申立て人
            petitionInfoList1.setPetitionUserId(petitionsTemp.getPetitionUserId());
            // 申立Id
            petitionInfoList1.setCasePetition(petitionsTemp.getCasePetition());
            // 画面表示項目.メールアドレス
            petitionInfoList1.setPetitionUserInfo_Email(petitionsTemp.getPetitionUserInfo_Email());
            // 画面表示項目.代理人1メールアドレス
            petitionInfoList1.setAgent1_Email(petitionsTemp.getAgent1_Email());
            // 画面表示項目.代理人2メールアドレス
            petitionInfoList1.setAgent2_Email(petitionsTemp.getAgent2_Email());
            // 画面表示項目.代理人3メールアドレス
            petitionInfoList1.setAgent3_Email(petitionsTemp.getAgent3_Email());
            // 画面表示項目.代理人4メールアドレス
            petitionInfoList1.setAgent4_Email(petitionsTemp.getAgent4_Email());
            // 画面表示項目.代理人5メールアドレス
            petitionInfoList1.setAgent5_Email(petitionsTemp.getAgent5_Email());
            // 画面表示項目.販売者メールアドレス
            petitionInfoList1.setTraderUserEmail(petitionsTemp.getTraderUserEmail());
            // 画面表示項目.購入商品
            petitionInfoList1.setProductName(petitionsTemp.getProductName());
            // 画面表示項目.商品ID
            petitionInfoList1.setProductId(petitionsTemp.getProductId());
            // 画面表示項目.販売者
            petitionInfoList1.setTraderName(petitionsTemp.getTraderName());
            // 画面表示項目.販売者ＵＲＬ
            petitionInfoList1.setTraderUrl(petitionsTemp.getTraderUrl());
            // 画面表示項目.購入日
            petitionInfoList1.setBoughtDate(petitionsTemp.getBoughtDate());
            // 画面表示項目.購入金額
            petitionInfoList1.setPrice(petitionsTemp.getPrice());
            // 画面表示項目.申立ての種類
            petitionInfoList1.setPetitionTypeValue(petitionsTemp.getPetitionTypeValue());
            // 画面表示項目.申立て内容
            petitionInfoList1.setPetitionContext(petitionsTemp.getPetitionContext());
            // 画面表示項目.希望する解決方法
            petitionInfoList1.setExpectResloveTypeValue(petitionsTemp.getExpectResloveTypeValue());
            // 画面表示項目.その他
            petitionInfoList1.setOther(petitionsTemp.getOther());
        }

        // TBL「ユーザ（odr_users）」より申立人情報を取得する
        if (petitionsTemp.getPetitionUserInfo_Email() != null) {
            // 申立人情報を取得する
            getPetitionInfo = getPetitionsTempMapper.selectOdrUsers(sessionInfo.getSessionId(),
                    petitionsTemp.getPetitionUserInfo_Email());
            // 画面表示項目.お問い合わせをされる方についての情報の氏名の名
            petitionInfoList1.setFirstName(getPetitionInfo.getFirstName());
            // 画面表示項目.お問い合わせをされる方についての情報の氏名の姓
            petitionInfoList1.setLastName(getPetitionInfo.getLastName());
            // 画面表示項目.お問い合わせをされる方についての情報の氏名（カナ）の名
            petitionInfoList1.setFirstName_kana(getPetitionInfo.getFirstName_kana());
            // 画面表示項目.お問い合わせをされる方についての情報の氏名（カナ）の姓
            petitionInfoList1.setLastName_kana(getPetitionInfo.getLastName_kana());
            // 画面表示項目.お問い合わせをされる方についての情報の所属会社
            petitionInfoList1.setCompanyName(getPetitionInfo.getCompanyName());

        }

        // 上記画面表示項目（画面上で必須となっている項目）取得有り（Nullでない）場合、下記の下書き案件のファイルIDと拡張項目内容取得を行う。
        if (petitionsTemp != null && getPetitionInfo != null) {
            // TBL「案件-添付ファイルリレーション（case_file_relations）」より関連下書き案件のファイルIDを取得する。
            List<FileId> getFileId = getPetitionsTempMapper.selectFileId(petitionsTemp.getCasePetition());
            if (getFileId.size() > 0) {
                // 画面表示項目.添付資料
                petitionInfoList1.setFileName(getFileId);
            }
            // 拡張項目内容取得
            List<ScaleItems> scaleItemsList = getPetitionsTempMapper.scaleItemsSearch(sessionInfo.getPlatformId());
            if (scaleItemsList.size() > 0) {
                // 画面表示項目.拡張項目
                petitionInfoList1.setPetitionTypeDisplayName(scaleItemsList);
            }
        }
        return petitionInfoList1;
    }

}
