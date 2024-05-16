package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.web.app.service.MediationsMakeService;
import com.web.app.domain.Entity.CaseMediations;
import com.web.app.domain.Entity.Files;
import com.web.app.domain.mediationsMake.InsMediationsData;
import com.web.app.mapper.InsMediationsDataMapper;;

@Service
public class MediationsMakeServiceImpl implements MediationsMakeService {
    @Autowired
    private InsMediationsDataMapper mediationcaseMapper;

    @Transactional
    @Override
    public int insMediationsData(InsMediationsData insMediationsData) {
        int a;
        // 「調停案」にデータが存在するかどうかを判断する（ファイルが添付されていない場合がある）
        CaseMediations mediationsCount = mediationcaseMapper.mediationCount(insMediationsData);

        // 「調停案」データが存在する場合
        if (mediationsCount != null) {
            // 「調停案」、「案件-添付ファイル」、「添付ファイル」テーブルを関連付けてデータが存在するかどうかを判断する
            int dataSearch = mediationcaseMapper.dataSearch(insMediationsData);
            // 表関連データが存在しない場合
            if (dataSearch == 0) {
                // 調停案データ更新API
                a = 1;
            } else {
                // 「案件-添付ファイルリレーション」は、添付ファイルの数に等しいレコード新規登録(insert)で行う
                // 「添付ファイル」は、添付ファイルの数に等しいレコード新規登録(insert)で行う
                a = 2;
            }
            // 調停案データ更新API
        } else {
            // 「調停案」は、レコード新規登録（insert）で行う
            CaseMediations caseMediations = new CaseMediations();
            // id付与
            UUID uuid = UUID.randomUUID();
            String id = uuid.toString().replaceAll("-", "");
            caseMediations.setId(id);
            // 属性赋予
            caseMediations.setPlatformId(insMediationsData.getPlatformId());
            caseMediations.setCaseId(insMediationsData.getCaseId());
            caseMediations.setExpectResloveTypeValue(insMediationsData.getExpectResloveTypeValue());
            caseMediations.setPayAmount(insMediationsData.getPayAmount());

            // CounterClaimPayment（反訴の支払金額）を設定するための申請の反訴があるかどうかを判断する
            if (insMediationsData.getCounterclaim() == 1) {
                caseMediations.setCounterClaimPayment(insMediationsData.getCounterClaimPayment());
            } else {
                caseMediations.setCounterClaimPayment(null);
            }

            caseMediations.setPaymentEndDate(insMediationsData.getPaymentEndDate());
            caseMediations.setShipmentPayType(insMediationsData.getShipmentPayType());
            caseMediations.setSpecialItem(insMediationsData.getSpecialItem());
            caseMediations.setUserId(insMediationsData.getUserId());
            caseMediations.setLastModifiedDate(insMediationsData.getLastModifiedDate());
            caseMediations.setLastModifiedBy(insMediationsData.getLastModifiedBy());
            // 「調停案」は、レコード新規登録（insert）で行う
            int MediationcaseInsert = mediationcaseMapper.insMediationsData2(caseMediations);

            if (MediationcaseInsert == 1) {
                // フロントから転送されたファイルデータを保存する
                ArrayList<Files> filesData = insMediationsData.getInsertFiles();
                // ファイルデータを巡回して個別にログインする
                System.out.println("FilesLength=" + filesData.size());
                for (int i = 0; i < filesData.size(); i++) {
                    UUID filesId = UUID.randomUUID();
                    String filesid = filesId.toString().replaceAll("-", "");
                    filesData.get(i).setId(filesid);
                    filesData.get(i).setPlatformId(insMediationsData.getPlatformId());
                    filesData.get(i).setCaseId(insMediationsData.getCaseId());
                    filesData.get(i).setRegisterUserId(insMediationsData.getUid());
                    filesData.get(i).setDeleteFlag(0);
                    filesData.get(i).setRegisterDate(insMediationsData.getRegisterDate());
                    filesData.get(i).setLastModifiedDate(insMediationsData.getLastModifiedDate()); 
                    filesData.get(i).setLastModifiedBy(insMediationsData.getUid());  
                    int insertFiles = mediationcaseMapper.insertFiles(filesData.get(i));

                    int b=insertFiles;
                }
            }
            a = 3;
            // 「案件-添付ファイルリレーション」は、添付ファイルの数に等しいレコード新規登録(insert)で行う
            // 「添付ファイル」は、添付ファイルの数に等しいレコード新規登録(insert)で行う
        }
        return a;
    }

}