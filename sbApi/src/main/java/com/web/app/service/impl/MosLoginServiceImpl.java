package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.Entity.MasterPlatforms;
import com.web.app.domain.Entity.MasterTypes;
import com.web.app.domain.Entity.OdrUsers;
import com.web.app.domain.MosLogin.ExpandItems;
import com.web.app.domain.MosLogin.GetPlatform;
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

    // 申立ての種類の表示内容の取得List
    List<String> petitionTypeList1 = new ArrayList<>();
    // 希望する解決方法の表示内容の取得List
    List<String> resloveTypeList1 = new ArrayList<>();
    // 画面制御表示項目表示状態の取得内容
    MasterPlatforms result1 = new MasterPlatforms();
    // 画面制御表示項目取得List
    GetPlatform itemsResultList1 = new GetPlatform();

    /**
     * 申立て登録画面の「申立ての種類」と「希望する解決方法」の選択肢の内容を表示するために、種類マスタから対応する内容を取得する。
     * プラットフォームマスタより画面制御表示項目の表示状態を取得する。
     *
     * @param sessionId セッション.ユーザID
     * @return GetPlatform
     *         申立て登録画面の「申立ての種類」と「希望する解決方法」の選択肢の内容、画面制御表示項目の表示状態、拡張項目の内容を表示する
     */
    @Override
    public GetPlatform odrUsersSearch(String sessionId) {

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
                itemsResultList1.setUserProductId(result1.getUserProductId());
                itemsResultList1.setUseTraderName(result1.getUseTraderName());
                itemsResultList1.setUseProductUrl(result1.getUseProductUrl());
                itemsResultList1.setUseOther(result1.getUseOther());

                // 取得したUseOther（拡張項目の表示状態）が1の場合、拡張項目を取得する。UseOtherが0の場合、API「申立て下書き保存データ取得」を呼び出す。
                // 拡張項目の取得
                if (result1.getUseOther() == 1) {
                    // 拡張項目の取得
                    List<ExpandItems> expandProjectList = getPlatformMapper
                            .expandProjectSearch(odrUsers.getPlatformId());
                    if (expandProjectList.size() > 0) {
                        itemsResultList1.setExpandItems(expandProjectList);
                    }
                } else {
                    // TOdo API「申立て下書き保存データ取得」
                    // GetPetitionTemp getPetitionTemp = petitionsTempSearch(sessionInfo)
                    return null;
                }
            }
        }

        return itemsResultList1;
    }

}
