package com.web.app.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.ScreenInfo;
import com.web.app.domain.UserInfo;
import com.web.app.domain.InsRepliesTemp.CaseExtensionitemValues;
import com.web.app.domain.InsRepliesTemp.CaseFileRelations;
import com.web.app.domain.InsRepliesTemp.CasePetitions;
import com.web.app.domain.InsRepliesTemp.CaseRelations;
import com.web.app.domain.InsRepliesTemp.Files;
import com.web.app.domain.MosLogin.ScaleItems;
import com.web.app.mapper.InsRepliesTempMapper;
import com.web.app.service.InsRepliesTempService;
import com.web.app.service.UtilService;

@Service
public class InsRepliesTempServiceImpl implements InsRepliesTempService {


    @Autowired
    private InsRepliesTempMapper insRepliesTempMapper;

    @Autowired
    private UtilService utilService;


    //⓵ユーザ情報の取得項目
    UserInfo userInfo = new UserInfo();
    //システム日付
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String sysDate = sdf.format(date);

    //⓶TBL「申立（case_petitions）」の更新戻り値
    int updateNum1 = 0;
    //⓷TBL「案件別個人情報リレーション（case_relations）」の更新戻り値
    int updateNum2 = 0;
    //⓹TBL「添付ファイル（files）」新規登録戻り値
    int insertNum1 = 0;
    //⓺TBL「案件-添付ファイルリレーション（case_file_relations）」新規登録戻り値
    int insertNum2 = 0;
    //⓻TBL「拡張項目設定値（case_extensionitem_values）」の更新戻り値
    int updateNum3 = 0;
    //⓻TBL「拡張項目設定値（case_extensionitem_values）」の登録戻り値
    int insertNum3 = 0;
    

    @Override
    public int repliesTempIns(ScreenInfo screenInfo){

        //⓵ユーザ情報の取得
        userInfo = insRepliesTempMapper.selectOdrUsers(screenInfo.getSessionId());
       
        //TODO API「下書き用準備データ登録」
        String case_petitions_id = "FFFF87C7B9C5425BB7D15DFCA7A59AE8";
        String case_relations_PetitionUserId = "U00250";

        //⓶TBL「申立（case_petitions）」の更新
        updateNum1 = updateCasePetitions(screenInfo, case_petitions_id, userInfo);

        //⓷TBL「案件別個人情報リレーション（case_relations）」の更新
        updateNum2 = updateCaseRelations(case_petitions_id,case_relations_PetitionUserId,userInfo,screenInfo);

        //⓸添付ファイル関連情報の初期化
        //a.案件-添付ファイルリレーションの取得
        String fildId = insRepliesTempMapper.caseFileRelationsSelect(case_petitions_id);
        //ｂ.上記取得有りの場合は関連のデータを初期化する
        if (fildId != null) {
            //TBL「添付ファイル（files）」を論理削除する
            insRepliesTempMapper.filesDelete(fildId);

            //TBL「案件-添付ファイルリレーション（case_file_relations）」を論理削除する
            insRepliesTempMapper.caseFileRelationsDelete(case_petitions_id);
        }
        
        if (screenInfo.getFileSize() != 0) {
            // ⓹画面上添付ファイルがなくなるまで、TBL「添付ファイル（files）」を新規登録する。
            // 自動採番のid（Guid取得）
            String fileMaxId1 = utilService.GetGuid();

            insertNum1 = insertFiles(fileMaxId1,userInfo,screenInfo,sysDate);

            // ⓺画面上添付ファイルがなくなるまで、TBL「案件-添付ファイルリレーション（case_file_relations）」を新規登録する。
            insertNum2 = insertCaseFileRelations(userInfo,case_petitions_id,fileMaxId1,sysDate,screenInfo);
        }
        // ⓻画面上拡張項目がある場合、TBLの内容を追加・または更新します。
        if (screenInfo.getPetitionTypeDisplayName().size() > 0) {
            // 画面上拡張項目遍历
            for (int i = 0; i < screenInfo.getPetitionTypeDisplayName().size(); i++) {    
                // 検索条件用数据初始化
                CaseExtensionitemValues CaseExtensionitemValues1 = new CaseExtensionitemValues();
                //プラットフォームID
                CaseExtensionitemValues1.setPlatformId(userInfo.getPlatformId());
                //申立てID
                CaseExtensionitemValues1.setCase_petitionId(case_petitions_id);
                //拡張項目ID
                CaseExtensionitemValues1.setExtensionitemId(screenInfo.getPetitionTypeDisplayName().get(i).getExtensionitemId());

                // 既存の拡張項目内容を取得
                ScaleItems scaleItems = insRepliesTempMapper.selectScaleItemIdValue(CaseExtensionitemValues1);

                // 取得したデータがある場合、2)へ(データを更新)
                if (scaleItems != null) {
                    // 更新用数据初始化
                    CaseExtensionitemValues CaseExtensionitemValues2 = new CaseExtensionitemValues();
                    //プラットフォームID
                    CaseExtensionitemValues2.setPlatformId(userInfo.getPlatformId());
                    //申立てID
                    CaseExtensionitemValues2.setCase_petitionId(case_petitions_id);
                    //拡張項目ID
                    CaseExtensionitemValues2.setExtensionitemId(screenInfo.getPetitionTypeDisplayName().get(i).getExtensionitemId());
                    //拡張項目値
                    CaseExtensionitemValues2.setExtensionitemValue(screenInfo.getPetitionTypeDisplayName().get(i).getExtensionitemValue());
                    //上次修改日期
                    CaseExtensionitemValues2.setLastModifiedDate(sysDate);
                    //上次修改者
                    CaseExtensionitemValues2.setLastModifiedBy(screenInfo.getSessionId());

                    //TBL「拡張項目設定値（case_extensionitem_values）」の更新
                    int updateNum3 = insRepliesTempMapper.updateCaseExtensionitemValues(CaseExtensionitemValues2);
                    if (updateNum3 == 0) {
                        return 0;
                    }
                // 取得したデータがないの場合、3)へ(データを登録)
                }else{
                    //自動採番のid（Guid取得）
                    String caseExtensionitemValuesMaxId1 = utilService.GetGuid();
                    // 更新用数据初始化
                    CaseExtensionitemValues CaseExtensionitemValues3 = new CaseExtensionitemValues();
                    //ID
                    CaseExtensionitemValues3.setId(caseExtensionitemValuesMaxId1);
                    //プラットフォームID
                    CaseExtensionitemValues3.setPlatformId(userInfo.getPlatformId());
                    //申立てID
                    CaseExtensionitemValues3.setCase_petitionId(case_petitions_id);
                    //拡張項目ID
                    CaseExtensionitemValues3.setExtensionitemId(screenInfo.getPetitionTypeDisplayName().get(i).getExtensionitemId());
                    //拡張項目値
                    CaseExtensionitemValues3.setExtensionitemValue(screenInfo.getPetitionTypeDisplayName().get(i).getExtensionitemValue());
                    //上次修改日期
                    CaseExtensionitemValues3.setLastModifiedDate(sysDate);
                    //上次修改者
                    CaseExtensionitemValues3.setLastModifiedBy(screenInfo.getSessionId());

                    //TBL「拡張項目設定値（case_extensionitem_values）」の登録
                    int insertNum3 = insRepliesTempMapper.insertCaseExtensionitemValues(CaseExtensionitemValues3);
                    if (insertNum3 == 0) {
                        return 0;
                    }
                }
            }
        }
        return 1;
    }

    // TBL「申立（case_petitions）」の更新
    private int updateCasePetitions(ScreenInfo screenInfo, String case_petitions_id, UserInfo userInfo) {
        // 更新用数据初始化
        CasePetitions casePetitions = new CasePetitions();
        //ID
        casePetitions.setId(case_petitions_id);
        //プラットフォームID
        casePetitions.setPlatformId(userInfo.getPlatformId());
        //商品名
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
        casePetitions.setBoughtDate(screenInfo.getCommoditydate());
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

        //TBL「申立（case_petitions）」の更新
        updateNum1 = insRepliesTempMapper.casePetitionsUpd(casePetitions);
        return updateNum1;
    }

    //TBL「案件別個人情報リレーション（case_relations）」の更新
    private int updateCaseRelations(String case_petitions_id,String case_relations_PetitionUserId,UserInfo userInfo,ScreenInfo screenInfo) {
        // 自動採番のid（Guid取得）
        String id = utilService.GetGuid();
        // 更新用数据初始化
        CaseRelations caseRelations = new CaseRelations();
        //ID
        caseRelations.setId(id);
        //申立Id
        caseRelations.setCasePetition(case_petitions_id);
        //申立て人
        caseRelations.setPetitionUserId(case_relations_PetitionUserId);
        //プラットフォームID
        caseRelations.setPlatformId(userInfo.getPlatformId());
        //申立て人入力情報
        caseRelations.setPetitionUserInfo_Email(screenInfo.getUseremail());
        //代理人1
        caseRelations.setAgent1_Email(screenInfo.getAgentemail1());
        //代理人2
        caseRelations.setAgent2_Email(screenInfo.getAgentemail2());
        //代理人3
        caseRelations.setAgent3_Email(screenInfo.getAgentemail3());
        //代理人4
        caseRelations.setAgent4_Email(screenInfo.getAgentemail4());
        //代理人5
        caseRelations.setAgent5_Email(screenInfo.getAgentemail5());
        //相手方メール
        caseRelations.setTraderUserEmail(screenInfo.getSellingelementemail());
       
        //TBL「案件別個人情報リレーション（case_relations）」の更新
        updateNum2 = insRepliesTempMapper.caseRelationsUpd(caseRelations);
        return updateNum2;
    }

    //TBL「添付ファイル（files）」を新規登録する
    private int  insertFiles(String fileMaxId1,UserInfo userInfo,ScreenInfo screenInfo,String sysDate) {
        // 登録用数据初始化
        Files files = new Files();
        //ID
        files.setId(fileMaxId1);
        //プラットフォームID
        files.setPlatformId(userInfo.getPlatformId());
        //ファイル名
        files.setFileName(screenInfo.getFileName());
        //拡張子
        files.setFileExtension(screenInfo.getFileExtension());
        //URL
        files.setFileUrl(screenInfo.getFileUrl());
        //ファイルサイズ
        files.setFileSize(screenInfo.getFileSize());
        //ユーザーID
        files.setRegisterUserId(screenInfo.getSessionId());
        //登録日
        files.setRegisterDate(sysDate);
        //上次修改日期
        files.setLastModifiedDate(sysDate);
        //上次修改者
        files.setLastModifiedBy(screenInfo.getSessionId());

        insertNum1 = insRepliesTempMapper.insertFiles(files);
        return insertNum1;
    }

    //TBL「案件-添付ファイルリレーション（case_file_relations）」を新規登録する
    private int  insertCaseFileRelations(UserInfo userInfo,String case_petitions_id, String fileMaxId1,String sysDate,ScreenInfo screenInfo) {
        // 自動採番のid（Guid取得）
        String caseFileRelationsMaxId1 = utilService.GetGuid();
        //登録用数据初始化
        CaseFileRelations caseFileRelations= new CaseFileRelations();
        //ID
        caseFileRelations.setId(caseFileRelationsMaxId1);
        //プラットフォームID
        caseFileRelations.setPlatformId(userInfo.getPlatformId());
        //案件種類ID
        caseFileRelations.setRelatedId(case_petitions_id);
        //ファイルID
        caseFileRelations.setFileId(fileMaxId1);
        //上次修改日期
        caseFileRelations.setLastModifiedDate(sysDate);
        //上次修改者
        caseFileRelations.setLastModifiedBy(screenInfo.getSessionId());
   
        insertNum2 = insRepliesTempMapper.insertCaseFileRelations(caseFileRelations);
        return insertNum2;
    }
    
}
