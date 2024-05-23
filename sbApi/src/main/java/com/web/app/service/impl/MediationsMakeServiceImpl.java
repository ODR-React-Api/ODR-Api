package com.web.app.service.impl;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.web.app.service.MediationsMakeService;
import com.web.app.domain.Entity.CaseFileRelations;
import com.web.app.domain.Entity.CaseMediations;
import com.web.app.domain.Entity.Files;
import com.web.app.domain.mediationsMake.InsMediationsData;
import com.web.app.domain.constants.Constants;
import com.web.app.mapper.InsMediationsDataMapper;

/**
 * 調停案データ新規登録
 * 
 * @author DUC 賈文志
 * @since 2024/05/20
 * @version 1.0
 */
@Service
public class MediationsMakeServiceImpl implements MediationsMakeService {
    @Autowired
    private InsMediationsDataMapper mediationcaseMapper;

    /**
     * 
     * 調停案データ新規登録
     * 
     * @param insMediationsData フロントから転送されたデータを受信する
     * @return 調停案データ登録または更新状態 (1：調停案データ新規登録成功2：調停案データ更新成功)
     */
    @Transactional
    @Override
    public int insMediationsData(InsMediationsData insMediationsData) {

        // 調停案データ新規登録が成功したかどうかを判断する
        int dataStatus;
        // 「調停案」にデータが存在するかどうかを判断する（CaseId検索による）
        int mediationsDataCount = mediationcaseMapper.mediationDataCount(insMediationsData);

        // 「調停案」データが存在する場合
        // 調停案データ更新API
        if (mediationsDataCount == Constants.NUM_1) {
            // 「案件-添付ファイル」、「添付ファイル」データが存在するかどうかを判断する
            int filesDataCount = mediationcaseMapper.filesDataCount(insMediationsData);

            // 「案件-添付ファイル」、「添付ファイル」データが発生しないと判断した場合
            if (filesDataCount == Constants.NUM_0) {
                // 調停案データ更新API
                dataStatus = Constants.NUM_2;
            } else {
                // 調停案データ更新API
                dataStatus = Constants.NUM_2;
            }
        } else {
            // 調停案データ新規登録API
            // 「調停案」新規登録に必要なデータを保存する
            CaseMediations caseMediations = new CaseMediations();
            UUID uuid = UUID.randomUUID();
            String caseMediationsId = uuid.toString().replaceAll("-", "");
            caseMediations.setId(caseMediationsId);
            caseMediations.setPlatformId(insMediationsData.getPlatformId());
            caseMediations.setCaseId(insMediationsData.getCaseId());
            caseMediations.setExpectResloveTypeValue(insMediationsData.getExpectResloveTypeValue());
            caseMediations.setPayAmount(insMediationsData.getPayAmount());
            // CounterClaimPayment（反訴の支払金額）を設定するための申請の反訴があるかどうかを判断する
            if (insMediationsData.getCounterclaim() == Constants.NUM_1) {
                // 反訴申請が存在する場合、フロントから転送された反訴の支払金額データを保存する。
                caseMediations.setCounterClaimPayment(insMediationsData.getCounterClaimPayment());
            } else {
                // 反訴申請が存在しない場合、反訴の支払金額は表示されず、空の値を保存します。
                caseMediations.setCounterClaimPayment(null);
            }
            caseMediations.setPaymentEndDate(insMediationsData.getPaymentEndDate());
            caseMediations.setShipmentPayType(insMediationsData.getShipmentPayType());
            caseMediations.setSpecialItem(insMediationsData.getSpecialItem());
            // ローグ・ユアサの保存
            caseMediations.setUserId(insMediationsData.getUid());
            caseMediations.setLastModifiedDate(insMediationsData.getLastModifiedDate());
            caseMediations.setLastModifiedBy(insMediationsData.getLastModifiedBy());
            // 「調停案」新規登録
            int mediationCaseInsert = mediationcaseMapper.insMediationsData(caseMediations);
            // 「調停案」データ新規登録が成功した場合
            if (mediationCaseInsert == Constants.NUM_1 && insMediationsData.getInsertFiles().getFileUrl() != null
                    && insMediationsData.getInsertFiles().getFileUrl() != "") {
                // フロントから転送されたファイルデータを保存する
                Files filesData = insMediationsData.getInsertFiles();
                UUID filesId = UUID.randomUUID();
                String filesid = filesId.toString().replaceAll("-", "");
                filesData.setId(filesid);
                filesData.setPlatformId(insMediationsData.getPlatformId());
                filesData.setCaseId(insMediationsData.getCaseId());
                // ローグ・ユアサの保存
                filesData.setRegisterUserId(insMediationsData.getUid());
                // システム日払いの保存
                filesData.setRegisterDate(insMediationsData.getRegisterDate());
                filesData.setLastModifiedDate(insMediationsData.getLastModifiedDate());
                // ローグ・ユアサの保存
                filesData.setLastModifiedBy(insMediationsData.getUid());
                // 「添付ファイル」の新規登録
                int insertFiles = mediationcaseMapper.insertFiles(filesData);
                // 「添付ファイル」新規登録が成功した場合
                if (insertFiles == Constants.NUM_1) {
                    // 「案件-添付ファイル」テーブルのデータを保存する
                    CaseFileRelations caseFileRelations = new CaseFileRelations();
                    UUID CaseFileRelationsId = UUID.randomUUID();
                    String CaseFileRelationsid = CaseFileRelationsId.toString().replaceAll("-", "");
                    caseFileRelations.setId(CaseFileRelationsid);
                    caseFileRelations.setPlatformId(insMediationsData.getPlatformId());
                    caseFileRelations.setCaseId(insMediationsData.getCaseId());
                    caseFileRelations.setRelatedId(caseMediationsId);
                    caseFileRelations.setFileId(filesid);
                    caseFileRelations.setLastModifiedDate(insMediationsData.getLastModifiedDate());
                    // ローグ・ユアサの保存
                    caseFileRelations.setLastModifiedBy(insMediationsData.getUid());
                    // 「案件-添付ファイルリレーション」新規登録
                    mediationcaseMapper.insCaseFileRelations(caseFileRelations);
                }
                dataStatus = Constants.NUM_1;
            } else {
                dataStatus = Constants.NUM_0;
            }
        }
        return dataStatus;
    }
}