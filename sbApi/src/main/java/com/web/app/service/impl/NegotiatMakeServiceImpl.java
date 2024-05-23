package com.web.app.service.impl;

import com.web.app.domain.Entity.CaseFileRelations;
import com.web.app.domain.Entity.CaseNegotiations;
import com.web.app.domain.Entity.Files;
import com.web.app.domain.NegotiatMake.NegotiationsFile;
import com.web.app.domain.NegotiatMake.UpdNegotiationsFile;
import com.web.app.domain.constants.Constants;
import com.web.app.mapper.InsNegotiationsEditMapper;
import com.web.app.mapper.UpdNegotiationsEditMapper;
import com.web.app.service.NegotiatMakeService;
import com.web.app.service.UtilService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 和解案作成画面
 * 
 * @author DUC 馬芹
 * @since 2024/05/06
 * @version 1.0
 */
@Service
public class NegotiatMakeServiceImpl implements NegotiatMakeService {

    @Autowired
    private UpdNegotiationsEditMapper updNegotiationsEditMapper;

    @Autowired
    private InsNegotiationsEditMapper insNegotiationsEditMapper;

    @Autowired
    DataSource dataSource;

    @Autowired
    private UtilService utilService;

    /**
     * 和解案編集依頼データ新規登録
     *
     * @param param1 フロントからの画面項目
     * @return int
     * @throws Exception
     */
    @Transactional
    @Override
    public int addNegotiationsEdit(NegotiationsFile negotiationsFile) throws Exception {
        // sessions取得
        System.out.println("データアクセス：" + dataSource.getConnection());

        // 「和解案」新規登録の値設定
        CaseNegotiations caseNegotiations = new CaseNegotiations();
         // ログインユーザが申立人場合、ステータス更新値：14
         if (negotiationsFile.getFlag() == Constants.POSITIONFLAG_PETITION) {
            caseNegotiations.setStatus(Constants.S3B14);
            // ログインユーザが相手方場合、ステータス更新値：1
        } else if (negotiationsFile.getFlag() == Constants.POSITIONFLAG_TRADER) {
            caseNegotiations.setStatus(Constants.S3B1);
        }else {
            return Constants.RESULT_STATE_ERROR;
        }
        caseNegotiations.setId(utilService.GetGuid());
        caseNegotiations.setPlatformId(negotiationsFile.getPlatformId());
        caseNegotiations.setCaseId(negotiationsFile.getCaseId());
     
        caseNegotiations.setExpectResloveTypeValue(negotiationsFile.getExpectResloveTypeValue());
        caseNegotiations.setOtherContext(negotiationsFile.getOtherContext());
        caseNegotiations.setHtmlContext(null);
        caseNegotiations.setHtmlContext2(null);
        caseNegotiations.setPayAmount(negotiationsFile.getPayAmount());
        caseNegotiations.setCounterClaimPayment(negotiationsFile.getCounterClaimPayment());
        caseNegotiations.setPaymentEndDate(negotiationsFile.getPaymentEndDate());
        caseNegotiations.setShipmentPayType(negotiationsFile.getShipmentPayType());
        caseNegotiations.setSpecialItem(negotiationsFile.getSpecialItem());
        caseNegotiations.setUserId(negotiationsFile.getUserId());
        caseNegotiations.setSubmitDate(null);
        caseNegotiations.setAgreementDate(null);
        caseNegotiations.setDeleteFlag(Constants.DELETE_FLAG_0);
        caseNegotiations.setLastModifiedDate(getSystemtime());
        caseNegotiations.setLastModifiedBy(negotiationsFile.getUserId());
        // 「和解案」新規登録
        insNegotiationsEditMapper.insertCaseNegotiations(caseNegotiations);
        
        // 画面からのファイルはnullではない場合、「添付ファイル」と「案件-添付ファイルリレーション」新規登録
        List<UpdNegotiationsFile> updNegotiationsFiles = negotiationsFile.getUpdNegotiationsFile();
        // // 添付ファイルがあるか判定
        if (!(updNegotiationsFiles == null || updNegotiationsFiles.isEmpty())) {
            for (UpdNegotiationsFile updNegotiationsFile : updNegotiationsFiles) {
                addFiles(updNegotiationsFile,negotiationsFile, caseNegotiations.getId());
            }
        }
        return Constants.RESULT_STATE_SUCCESS;
    }

    /**
     * 和解案編集依頼データ更新
     *
     * @param param1 フロントからの画面項目
     * @return int
     * @throws Exception
     */
    @Transactional
    @Override
    public int updateNegotiationsEdit(NegotiationsFile negotiationsFile) throws Exception {
        System.out.println("データアクセス" + dataSource.getConnection());
        // 「和解案」
        CaseNegotiations caseNegotiations = new CaseNegotiations();
        // ログインユーザが申立人場合、ステータス更新値：14
        if (negotiationsFile.getFlag() == Constants.POSITIONFLAG_PETITION) {
            caseNegotiations.setStatus(Constants.S3B14);
            // ログインユーザが相手方場合、ステータス更新値：1
        } else if (negotiationsFile.getFlag() == Constants.POSITIONFLAG_TRADER) {
            caseNegotiations.setStatus(Constants.S3B1);
        }else {
            return Constants.RESULT_STATE_ERROR;
        }
        // 「和解案」更新値設定
        caseNegotiations.setExpectResloveTypeValue(negotiationsFile.getExpectResloveTypeValue());
        caseNegotiations.setOtherContext(negotiationsFile.getOtherContext());
        caseNegotiations.setPayAmount(negotiationsFile.getPayAmount());
        caseNegotiations.setCounterClaimPayment(negotiationsFile.getCounterClaimPayment());
        caseNegotiations.setPaymentEndDate(negotiationsFile.getPaymentEndDate());
        caseNegotiations.setShipmentPayType(negotiationsFile.getShipmentPayType());
        caseNegotiations.setSpecialItem(negotiationsFile.getSpecialItem());
        caseNegotiations.setLastModifiedDate(getSystemtime());
        caseNegotiations.setLastModifiedBy(negotiationsFile.getUserId());
        // セッション情報の和解案id
        caseNegotiations.setId(negotiationsFile.getId());
        // 「和解案」DBに更新
        int result = updNegotiationsEditMapper.updateCaseNegotiations(caseNegotiations);
        // 更新失敗の場合
        if (result == Constants.RESULT_STATE_ERROR) {
            throw new RuntimeException();
        }
        // 添付ファイルの添削によりファイルの増減が発生しますので、レコード論理削除＋新規登録で行う
        List<UpdNegotiationsFile> updNegotiationsFiles = negotiationsFile.getUpdNegotiationsFile();
        // 添付ファイルnull判定
        if (!(updNegotiationsFiles == null || updNegotiationsFiles.isEmpty())) {
            for (UpdNegotiationsFile updNegotiationsFile : updNegotiationsFiles) {
                // updFileFlagは0の場合、ファイル削除
                if (updNegotiationsFile.getUpdFileFlag() == Constants.TEMPLATE_TYPE_0) {
                    // 「添付ファイル」の値設定
                    Files filesDelete = new Files();
                    filesDelete.setDeleteFlag(Constants.DELETE_FLAG_1);
                    filesDelete.setLastModifiedDate(getSystemtime());
                    filesDelete.setLastModifiedBy(negotiationsFile.getUserId());
                    filesDelete.setId(updNegotiationsFile.getId());
                    // 「添付ファイル」論理削除
                    int resultDeleteFiles = updNegotiationsEditMapper.deleteFiles(filesDelete);
                    if (resultDeleteFiles == Constants.RESULT_STATE_ERROR) {
                        throw new RuntimeException();
                    }
                    // 「案件-添付ファイルリレーション」の値設定
                    CaseFileRelations caseFileRelationsDelete = new CaseFileRelations();
                    caseFileRelationsDelete.setDeleteFlag(Constants.DELETE_FLAG_1);
                    caseFileRelationsDelete.setLastModifiedDate(getSystemtime());
                    caseFileRelationsDelete.setLastModifiedBy(negotiationsFile.getUserId());
                    caseFileRelationsDelete.setFileId(updNegotiationsFile.getId());

                    // 「案件-添付ファイルリレーション」論理削除
                    int resultDeleteCaseFileRelations = updNegotiationsEditMapper
                            .deleteCaseFileRelations(caseFileRelationsDelete);
                    if (resultDeleteCaseFileRelations == Constants.RESULT_STATE_ERROR) {
                        throw new RuntimeException();
                    }
                    // updFileFlagは2の場合、ファイル追加
                } else if (updNegotiationsFile.getUpdFileFlag() == Constants.TEMPLATE_TYPE_2) {
                    addFiles(updNegotiationsFile,negotiationsFile, caseNegotiations.getId());
                }

            }
        }
        return Constants.RESULT_STATE_SUCCESS;
    }
    
   /**
     * ファイル追加
     *
     * @param param1 フロントからの画面項目
     * @param param2 和解案Id
     * @return int
     * @throws
     */
    private int addFiles(UpdNegotiationsFile updNegotiationsFile, NegotiationsFile negotiationsFile, String negotiationsId) {
        // updFileFlagは2の場合、ファイル追加
        if (updNegotiationsFile.getUpdFileFlag() == Constants.TEMPLATE_TYPE_2) {
            // 「添付ファイル」の値設定
            Files reFiles = insFiles(updNegotiationsFile,negotiationsFile);
            // 「添付ファイル」の新規登録
            insNegotiationsEditMapper.insertFiles(reFiles);
           
            // 「案件-添付ファイルリレーション」の値設定
            CaseFileRelations resCaseFileRelations = insCaseFileRelations(negotiationsFile,negotiationsId,
                    reFiles.getId());
            // 「案件-添付ファイルリレーション」の新規登録
            insNegotiationsEditMapper.insertCaseFileRelations(resCaseFileRelations);
            
        }
        return Constants.RESULT_STATE_SUCCESS;
    }

    /**
     * 「添付ファイル」の画面項目値設定
     *
     * @param param1 添付ファイル
     * @return Files 添付ファイル
     * @throws
     */
    private Files insFiles(UpdNegotiationsFile updNegotiationsFile,NegotiationsFile negotiationsFile) {
        Files files = new Files();
        files.setId(utilService.GetGuid());
        files.setPlatformId(negotiationsFile.getPlatformId());
        files.setCaseId(negotiationsFile.getCaseId());
        files.setFileName(updNegotiationsFile.getFileName());
        files.setFileExtension(updNegotiationsFile.getFileExtension());
        // TODO
        // 内部ロジック生成ファイルUR
        files.setFileUrl(updNegotiationsFile.getFileUrl());
        files.setFileBlobStorageId(null);
        // TODO
        // 内部ロジック生成ファイルサイ
        files.setFileSize(updNegotiationsFile.getFileSize());
        files.setRegisterUserId(negotiationsFile.getUserId());
        files.setRegisterDate(getSystemtime());
        files.setOther01(null);
        files.setOther02(null);
        files.setOther03(null);
        files.setOther04(null);
        files.setOther05(null);
        files.setDeleteFlag(Constants.DELETE_FLAG_0);
        files.setLastModifiedDate(getSystemtime());
        files.setLastModifiedBy(negotiationsFile.getUserId());

        return files;
    }

    /**
     * 「案件-添付ファイルリレーション」の画面項目値設定
     *
     * @param param1 和解案.id
     * @param param2 添付ファイル.id
     * @return CaseFileRelations-添付ファイルリレーション
     * @throws
     */
    private CaseFileRelations insCaseFileRelations(NegotiationsFile negotiationsFile, String caseId, String filesId) {
        // session取得
        CaseFileRelations caseFileRelations = new CaseFileRelations();
        // 自動生成GIUD
        caseFileRelations.setId(utilService.GetGuid());
        caseFileRelations.setPlatformId(negotiationsFile.getPlatformId());
        caseFileRelations.setCaseId(negotiationsFile.getCaseId());
        caseFileRelations.setRelationType(Constants.CASE_NEGOTIATIONS);
        // 和解案.id
        caseFileRelations.setRelatedId(caseId);
        // 添付ファイル.id
        caseFileRelations.setFileId(filesId);
        caseFileRelations.setOther01(null);
        caseFileRelations.setOther02(null);
        caseFileRelations.setOther03(null);
        caseFileRelations.setOther04(null);
        caseFileRelations.setOther05(null);
        caseFileRelations.setDeleteFlag(Constants.DELETE_FLAG_0);
        caseFileRelations.setLastModifiedDate(getSystemtime());
        caseFileRelations.setLastModifiedBy(negotiationsFile.getUserId());
        return caseFileRelations;
    }

    /**
     * システム時間取得
     *
     * @param param1
     * @return String システム時間
     * @throws
     */
    private String getSystemtime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.FORMAT);
        Date date = new Date();
        String lastModifiedDate = dateFormat.format(date);
        return lastModifiedDate;
    }
}
