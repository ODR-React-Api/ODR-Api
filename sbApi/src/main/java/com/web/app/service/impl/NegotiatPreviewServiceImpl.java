package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.Entity.CaseFileRelations;
import com.web.app.domain.Entity.CaseNegotiations;
import com.web.app.domain.Entity.File;
import com.web.app.domain.NegotiatPreview.NegotiatPreview;
import com.web.app.domain.constants.Constants;
import com.web.app.domain.constants.Num;
import com.web.app.mapper.InsNegotiationDataMapper;
import com.web.app.mapper.UpdNegotiationsDataMapper;
import com.web.app.service.NegotiatPreviewService;

/**
 * 和解案データ登録
 * 
 * @author DUC 李志文
 * @since 2024/05/10
 * @version 1.0
 */
@Service
public class NegotiatPreviewServiceImpl implements NegotiatPreviewService {
    @Autowired
    private UpdNegotiationsDataMapper updNegotiationsDataMapper;

    @Autowired
    private InsNegotiationDataMapper insNegotiationDataMapper;

    @Autowired
    private UtilServiceImpl utilServiceImpl;

    /**
     * 和解案が存在するかどうかを判断する
     *
     * @param NegotiatPreview セッション値
     * @return 存在->和解案データ更新、存在しない->和解案データ新規登録
     * @throws Exception エラーの説明内容
     */
    @Transactional
    @Override
    public int NegotiatPreview(NegotiatPreview negotiatPreview) {
        //和解案抽出
        CaseNegotiations cNegotiations = updNegotiationsDataMapper.SearchCaseNegotiations(negotiatPreview.getCaseId());
        if (cNegotiations != null) {
            // Num未マージ
            if (cNegotiations.getStatus().equals(Num.NUM0) ||
                    cNegotiations.getStatus().equals(Num.NUM1) ||
                    cNegotiations.getStatus().equals(Num.NUM2)) {
                negotiatPreview.setStatus(Num.NUM2);
            } else if (cNegotiations.getStatus().equals(Num.NUM7) ||
                    cNegotiations.getStatus().equals(Num.NUM8) ||
                    cNegotiations.getStatus().equals(Num.NUM9)) {
                negotiatPreview.setStatus(Num.NUM9);
            } else if (cNegotiations.getStatus().equals(Num.NUM10) ||
                    cNegotiations.getStatus().equals(Num.NUM11) ||
                    cNegotiations.getStatus().equals(Num.NUM12)) {
                negotiatPreview.setStatus(Num.NUM12);
            } else if (cNegotiations.getStatus().equals(Num.NUM13) ||
                    cNegotiations.getStatus().equals(Num.NUM14) ||
                    cNegotiations.getStatus().equals(Num.NUM15)) {
                negotiatPreview.setStatus(Num.NUM15);
            }else{
                return 1;
            }
            UpdNegotiationsData(negotiatPreview);
        } else {
            InsNegotiationData(negotiatPreview);
        }
        return 1;
    }

    /**
     * 和解案データ新規登録
     *
     * @param NegotiatPreview セッション値
     * @return メソッドが正常に実行されたかどうか
     * @throws Exception エラーの説明内容
     */
    @Transactional
    @Override
    public int InsNegotiationData(NegotiatPreview negotiatPreview) {
        // 「和解案」新規登録
        CaseNegotiations caseNegotiations = new CaseNegotiations();
        caseNegotiations.setId(utilServiceImpl.GetGuid());
        caseNegotiations.setPlatformId(negotiatPreview.getPlatformId());
        caseNegotiations.setCaseId(negotiatPreview.getCaseId());
        caseNegotiations.setStatus(negotiatPreview.getStatus());
        caseNegotiations.setExpectResloveTypeValue(negotiatPreview.getExpectResloveTypeValue());
        caseNegotiations.setOtherContext(negotiatPreview.getOtherContext());
        caseNegotiations.setHtmlContext(negotiatPreview.getHtmlContext());
        caseNegotiations.setHtmlContext2(negotiatPreview.getHtmlContext2());
        caseNegotiations.setPayAmount(negotiatPreview.getPayAmount());
        caseNegotiations.setCounterClaimPayment(negotiatPreview.getCounterClaimPayment());
        caseNegotiations.setPaymentEndDate(negotiatPreview.getPaymentEndDate());
        caseNegotiations.setShipmentPayType(negotiatPreview.getShipmentPayType());
        caseNegotiations.setSpecialItem(negotiatPreview.getSpecialItem());
        caseNegotiations.setUserId(negotiatPreview.getUserId());
        caseNegotiations.setSubmitDate(negotiatPreview.getSubmitDate());
        caseNegotiations.setAgreementDate(negotiatPreview.getAgreementDate());
        caseNegotiations.setLastModifiedDate(negotiatPreview.getLastModifiedDate());
        caseNegotiations.setLastModifiedBy(negotiatPreview.getLastModifiedBy());
        int addCaseNegotiationsStatus = insNegotiationDataMapper.AddCaseNegotiations(caseNegotiations);
        if (addCaseNegotiationsStatus == Constants.RESULT_STATE_ERROR) {
            return Constants.RESULT_STATE_ERROR;
        }

        // 「添付ファイル」の新規登録
        File file = new File();
        file.setId(utilServiceImpl.GetGuid());
        file.setPlatformId(negotiatPreview.getPlatformId());
        file.setCaseId(negotiatPreview.getCaseId());
        file.setFileName(negotiatPreview.getFileName());
        file.setFileExtension(negotiatPreview.getFileExtension());
        file.setFileUrl(negotiatPreview.getFileUrl());
        file.setFileBlobStorageId(negotiatPreview.getFileBlobStorageId());
        file.setFileSize(negotiatPreview.getFileSize());
        file.setRegisterUserId(negotiatPreview.getRegisterUserId());
        file.setRegisterDate(negotiatPreview.getRegisterDate());
        file.setLastModifiedDate(negotiatPreview.getLastModifiedDate());
        file.setLastModifiedBy(negotiatPreview.getLastModifiedBy());
        int addFileStatus = insNegotiationDataMapper.AddFile(file);
        if (addFileStatus == Constants.RESULT_STATE_ERROR) {
            return Constants.RESULT_STATE_ERROR;
        }

        // 「案件-添付ファイルリレーション」新規登録
        CaseFileRelations caseFileRelations = new CaseFileRelations();
        caseFileRelations.setId(utilServiceImpl.GetGuid());
        caseFileRelations.setPlatformId(negotiatPreview.getPlatformId());
        caseFileRelations.setCaseId(negotiatPreview.getCaseId());
        caseFileRelations.setRelatedId(caseNegotiations.getId());
        caseFileRelations.setFileId(file.getId());
        int addCaseFileRelationsStatus = insNegotiationDataMapper.AddCaseFileRelations(caseFileRelations);
        if (addCaseFileRelationsStatus == Constants.RESULT_STATE_ERROR) {
            return Constants.RESULT_STATE_ERROR;
        }
        return Constants.RESULT_STATE_SUCCESS;
    }

    /**
     * 和解案データ更新
     *
     * @param NegotiatPreview セッション値
     * @return メソッドが正常に実行されたかどうか
     * @throws Exception エラーの説明内容
     */
    @Transactional
    @Override
    public int UpdNegotiationsData(NegotiatPreview negotiatPreview) {
        // 「和解案」更新
        CaseNegotiations upCaseNegotiations = new CaseNegotiations();
        upCaseNegotiations.setId(negotiatPreview.getId());
        upCaseNegotiations.setExpectResloveTypeValue(negotiatPreview.getExpectResloveTypeValue());
        upCaseNegotiations.setOtherContext(negotiatPreview.getOtherContext());
        upCaseNegotiations.setPayAmount(negotiatPreview.getPayAmount());
        upCaseNegotiations.setCounterClaimPayment(negotiatPreview.getCounterClaimPayment());
        upCaseNegotiations.setPaymentEndDate(negotiatPreview.getPaymentEndDate());
        upCaseNegotiations.setShipmentPayType(negotiatPreview.getShipmentPayType());
        upCaseNegotiations.setSpecialItem(negotiatPreview.getSpecialItem());
        upCaseNegotiations.setHtmlContext(negotiatPreview.getHtmlContext());
        upCaseNegotiations.setHtmlContext2(negotiatPreview.getHtmlContext2());
        upCaseNegotiations.setSubmitDate(negotiatPreview.getSubmitDate());
        upCaseNegotiations.setStatus(negotiatPreview.getStatus());
        upCaseNegotiations.setLastModifiedDate(negotiatPreview.getLastModifiedDate());
        upCaseNegotiations.setLastModifiedBy(negotiatPreview.getLastModifiedBy());
        int upCaseNegotiationsStatus = updNegotiationsDataMapper.UpCaseNegotiations(upCaseNegotiations);
        if (upCaseNegotiationsStatus == Constants.RESULT_STATE_ERROR) {
            return Constants.RESULT_STATE_ERROR;
        }

        // 「添付ファイル」論理削除
        File upFile = new File();
        upFile.setId(negotiatPreview.getFileId());
        int upFileStatus = updNegotiationsDataMapper.UpFile(upFile);
        if (upFileStatus == Constants.RESULT_STATE_ERROR) {
            return Constants.RESULT_STATE_ERROR;
        }

        // 「案件-添付ファイルリレーション」論理削除
        CaseFileRelations upCaseFileRelations = new CaseFileRelations();
        upCaseFileRelations.setFileId(negotiatPreview.getFileId());
        int upCaseFileRelationsStatus = updNegotiationsDataMapper.UpCaseFileRelations(upCaseFileRelations);
        if (upCaseFileRelationsStatus == Constants.RESULT_STATE_ERROR) {
            return Constants.RESULT_STATE_ERROR;
        }

        // 「添付ファイル」の新規登録
        File addFile = new File();
        addFile.setId(utilServiceImpl.GetGuid());
        addFile.setPlatformId(negotiatPreview.getPlatformId());
        addFile.setCaseId(negotiatPreview.getCaseId());
        addFile.setFileName(negotiatPreview.getFileName());
        addFile.setFileExtension(negotiatPreview.getFileExtension());
        addFile.setFileUrl(negotiatPreview.getFileUrl());
        addFile.setFileBlobStorageId(negotiatPreview.getFileBlobStorageId());
        addFile.setFileSize(negotiatPreview.getFileSize());
        addFile.setRegisterUserId(negotiatPreview.getRegisterUserId());
        addFile.setRegisterDate(negotiatPreview.getRegisterDate());
        addFile.setLastModifiedDate(negotiatPreview.getLastModifiedDate());
        addFile.setLastModifiedBy(negotiatPreview.getLastModifiedBy());
        int addFileStatus = insNegotiationDataMapper.AddFile(addFile);
        if (addFileStatus == Constants.RESULT_STATE_ERROR) {
            return Constants.RESULT_STATE_ERROR;
        }

        // 「案件-添付ファイルリレーション」新規登録
        CaseFileRelations addCaseFileRelations = new CaseFileRelations();
        addCaseFileRelations.setId(utilServiceImpl.GetGuid());
        addCaseFileRelations.setPlatformId(negotiatPreview.getPlatformId());
        addCaseFileRelations.setCaseId(negotiatPreview.getCaseId());
        addCaseFileRelations.setRelatedId(upCaseNegotiations.getId());
        addCaseFileRelations.setFileId(addFile.getId());
        int addCaseFileRelationsStatus = insNegotiationDataMapper.AddCaseFileRelations(addCaseFileRelations);
        if (addCaseFileRelationsStatus == Constants.RESULT_STATE_ERROR) {
            return Constants.RESULT_STATE_ERROR;
        }
        return Constants.RESULT_STATE_SUCCESS;
    }
}
