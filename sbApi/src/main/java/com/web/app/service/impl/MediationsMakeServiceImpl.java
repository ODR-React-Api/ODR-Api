package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.web.app.service.MediationsMakeService;
import com.web.app.domain.Entity.CaseFileRelations;
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

        // データの存在状態を判断するために使用する
        int dataStatus;
        // 「調停案」にデータが存在するかどうかを判断する（CaseId検索による）
        CaseMediations mediationsCount = mediationcaseMapper.mediationCount(insMediationsData);

        // 「調停案」データが存在する場合
        if (mediationsCount != null) {

            // 「案件-添付ファイル」、「添付ファイル」データが存在するかどうかを判断する
            int dataSearch = mediationcaseMapper.dataSearch(insMediationsData);

            // 「案件-添付ファイル」、「添付ファイル」データが発生しないと判断した場合
            if (dataSearch == 0) {
                // 調停案データ更新API
                dataStatus = 1;
            } else {
                // 調停案データ更新API
                dataStatus = 2;
            }
            // 調停案データ更新API
        } else {
            // 「調停案」は、レコード新規登録（insert）で行う
            CaseMediations caseMediations = new CaseMediations();
            // id付与
            UUID uuid = UUID.randomUUID();
            String caseMediationsId = uuid.toString().replaceAll("-", "");
            caseMediations.setId(caseMediationsId);
            // 「調停案」テーブルのフィールドにデータを保存する
            caseMediations.setPlatformId(insMediationsData.getPlatformId());
            caseMediations.setCaseId(insMediationsData.getCaseId());
            caseMediations.setExpectResloveTypeValue(insMediationsData.getExpectResloveTypeValue());
            caseMediations.setPayAmount(insMediationsData.getPayAmount());

            // CounterClaimPayment（反訴の支払金額）を設定するための申請の反訴があるかどうかを判断する
            if (insMediationsData.getCounterclaim() == 1) {
                // 反訴申請が存在する場合、フロントから転送された反訴の支払金額データを保存する。
                caseMediations.setCounterClaimPayment(insMediationsData.getCounterClaimPayment());
            } else {
                //反訴申請が存在しない場合、反訴の支払金額は表示されず、空の値を保存します。
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
                    // List内のデータを巡回して1つずつログインする
                    int insertFiles = mediationcaseMapper.insertFiles(filesData.get(i));

                    if (insertFiles == 1) {
                        CaseFileRelations caseFileRelations = new CaseFileRelations();

                        UUID CaseFileRelationsId = UUID.randomUUID();
                        String CaseFileRelationsid = CaseFileRelationsId.toString().replaceAll("-", "");
                        caseFileRelations.setId(CaseFileRelationsid);
                        caseFileRelations.setPlatformId(insMediationsData.getPlatformId());
                        caseFileRelations.setCaseId(insMediationsData.getCaseId());
                        caseFileRelations.setRelatedId(caseMediationsId);
                        caseFileRelations.setFileId(filesid);
                        caseFileRelations.setLastModifiedDate(insMediationsData.getLastModifiedDate());
                        caseFileRelations.setLastModifiedBy(insMediationsData.getUid());

                        int insCaseFileRelations = mediationcaseMapper.insCaseFileRelations(caseFileRelations);

                        if (insCaseFileRelations == 1) {
                            dataStatus = 111;
                        } else {
                            dataStatus = 222;
                        }
                    }
                }
            }
            dataStatus = 3;
            // 「案件-添付ファイルリレーション」は、添付ファイルの数に等しいレコード新規登録(insert)で行う
            // 「添付ファイル」は、添付ファイルの数に等しいレコード新規登録(insert)で行う
        }
        return dataStatus;
    }

}