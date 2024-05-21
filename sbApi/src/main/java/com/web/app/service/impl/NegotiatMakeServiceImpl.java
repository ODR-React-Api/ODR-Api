package com.web.app.service.impl;

import com.web.app.domain.Entity.CaseFileRelations;
import com.web.app.domain.Entity.CaseNegotiations;
import com.web.app.domain.Entity.Files;
import com.web.app.domain.NegotiatMake.NegotiationsFile;
import com.web.app.domain.NegotiatMake.SessionItems;
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
        SessionItems sessionItem = getSessionItems();
        System.out.println("データアクセス：" + dataSource.getConnection());

        // 「和解案」新規登録の値設定
        CaseNegotiations caseNegotiations = new CaseNegotiations();
        caseNegotiations.setId(utilService.GetGuid());
        caseNegotiations.setPlatformId(sessionItem.getPlatformId());
        caseNegotiations.setCaseId(sessionItem.getCaseId());
        // ログインユーザが申立人場合、ステータス更新値：14
        if (sessionItem.getFlag() == Constants.POSITIONFLAG_PETITION) {
            caseNegotiations.setStatus(Constants.S3B14);
            // ログインユーザが相手方場合、ステータス更新値：1
        } else if (sessionItem.getFlag() == Constants.POSITIONFLAG_TRADER) {
            caseNegotiations.setStatus(Constants.S3B1);
        }
        caseNegotiations.setExpectResloveTypeValue(negotiationsFile.getExpectResloveTypeValue());
        caseNegotiations.setOtherContext(negotiationsFile.getOtherContext());
        caseNegotiations.setHtmlContext(null);
        caseNegotiations.setHtmlContext2(null);
        caseNegotiations.setPayAmount(negotiationsFile.getPayAmount());
        caseNegotiations.setCounterClaimPayment(negotiationsFile.getCounterClaimPayment());
        caseNegotiations.setPaymentEndDate(negotiationsFile.getPaymentEndDate());
        caseNegotiations.setShipmentPayType(negotiationsFile.getShipmentPayType());
        caseNegotiations.setSpecialItem(negotiationsFile.getSpecialItem());
        caseNegotiations.setUserId(sessionItem.getUserId());
        caseNegotiations.setSubmitDate(null);
        caseNegotiations.setAgreementDate(null);
        caseNegotiations.setDeleteFlag(Constants.DELETE_FLAG_0);
        caseNegotiations.setLastModifiedDate(getSystemtime());
        caseNegotiations.setLastModifiedBy(sessionItem.getUserId());
        // 「和解案」新規登録
        int result = insNegotiationsEditMapper.insertCaseNegotiations(caseNegotiations);
        if (result == Constants.RESULT_STATE_ERROR) {
            return Constants.RESULT_STATE_ERROR;
        }
        // 画面からのファイルはnullではない場合、「添付ファイル」と「案件-添付ファイルリレーション」新規登録
        List<UpdNegotiationsFile> updNegotiationsFiles = negotiationsFile.getUpdNegotiationsFile();
        // // 添付ファイルがあるか判定
        if (!(updNegotiationsFiles == null || updNegotiationsFiles.isEmpty())) {
            for (UpdNegotiationsFile updNegotiationsFile : updNegotiationsFiles) {
                addFiles(updNegotiationsFile, caseNegotiations.getId());
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

        SessionItems sessionItem = getSessionItems();
        System.out.println("データアクセス" + dataSource.getConnection());

        // 「和解案」
        CaseNegotiations caseNegotiations = new CaseNegotiations();
        // ログインユーザが申立人場合、ステータス更新値：14
        if (sessionItem.getFlag() == Constants.POSITIONFLAG_PETITION) {
            caseNegotiations.setStatus(Constants.S3B14);
            // ログインユーザが相手方場合、ステータス更新値：1
        } else if (sessionItem.getFlag() == Constants.POSITIONFLAG_TRADER) {
            caseNegotiations.setStatus(Constants.S3B1);
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
        caseNegotiations.setLastModifiedBy(sessionItem.getUserId());
        // セッション情報の和解案id
        caseNegotiations.setId(sessionItem.getId());
        // 「和解案」DBに更新
        int result = updNegotiationsEditMapper.updateCaseNegotiations(caseNegotiations);
        // 更新失敗の場合
        if (result == Constants.RESULT_STATE_ERROR) {
            return Constants.RESULT_STATE_ERROR;
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
                    filesDelete.setLastModifiedBy(sessionItem.getUserId());
                    filesDelete.setId(updNegotiationsFile.getId());
                    // 「添付ファイル」論理削除
                    int resultDeleteFiles = updNegotiationsEditMapper.deleteFiles(filesDelete);
                    if (resultDeleteFiles == Constants.RESULT_STATE_ERROR) {
                        return Constants.RESULT_STATE_ERROR;
                    }
                    // 「案件-添付ファイルリレーション」の値設定
                    CaseFileRelations caseFileRelationsDelete = new CaseFileRelations();
                    caseFileRelationsDelete.setDeleteFlag(Constants.DELETE_FLAG_1);
                    caseFileRelationsDelete.setLastModifiedDate(getSystemtime());
                    caseFileRelationsDelete.setLastModifiedBy(sessionItem.getUserId());
                    caseFileRelationsDelete.setFileId(updNegotiationsFile.getId());

                    // 「案件-添付ファイルリレーション」論理削除
                    int resultDeleteCaseFileRelations = updNegotiationsEditMapper
                            .deleteCaseFileRelations(caseFileRelationsDelete);
                    if (resultDeleteCaseFileRelations == Constants.RESULT_STATE_ERROR) {
                        return Constants.RESULT_STATE_ERROR;
                    }
                    // updFileFlagは2の場合、ファイル追加
                } else if (updNegotiationsFile.getUpdFileFlag() == Constants.TEMPLATE_TYPE_2) {
                    addFiles(updNegotiationsFile, caseNegotiations.getId());
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
    private int addFiles(UpdNegotiationsFile updNegotiationsFile, String negotiationsId) {
        // updFileFlagは2の場合、ファイル追加
        if (updNegotiationsFile.getUpdFileFlag() == Constants.TEMPLATE_TYPE_2) {
            // 「添付ファイル」の値設定
            Files reFiles = insFiles(updNegotiationsFile);
            // 「添付ファイル」の新規登録
            int result2 = insNegotiationsEditMapper.insertFiles(reFiles);
            if (result2 == Constants.RESULT_STATE_ERROR) {
                return Constants.RESULT_STATE_ERROR;
            }
            // 「案件-添付ファイルリレーション」の値設定
            CaseFileRelations resCaseFileRelations = insCaseFileRelations(negotiationsId,
                    reFiles.getId());
            // 「案件-添付ファイルリレーション」の新規登録
            int result3 = insNegotiationsEditMapper.insertCaseFileRelations(resCaseFileRelations);
            if (result3 == Constants.RESULT_STATE_ERROR) {
                return Constants.RESULT_STATE_ERROR;
            }
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
    private Files insFiles(UpdNegotiationsFile updNegotiationsFile) {
        SessionItems sessionItem = getSessionItems();
        Files files = new Files();
        files.setId(utilService.GetGuid());
        files.setPlatformId(sessionItem.getPlatformId());
        files.setCaseId(sessionItem.getCaseId());
        files.setFileName(updNegotiationsFile.getFileName());
        files.setFileExtension(updNegotiationsFile.getFileExtension());
        // TODO
        // 内部ロジック生成ファイルUR
        files.setFileUrl(updNegotiationsFile.getFileUrl());
        files.setFileBlobStorageId(null);
        // TODO
        // 内部ロジック生成ファイルサイ
        files.setFileSize(updNegotiationsFile.getFileSize());
        files.setRegisterUserId(sessionItem.getUserId());
        files.setRegisterDate(getSystemtime());
        files.setOther01(null);
        files.setOther02(null);
        files.setOther03(null);
        files.setOther04(null);
        files.setOther05(null);
        files.setDeleteFlag(Constants.DELETE_FLAG_0);
        files.setLastModifiedDate(getSystemtime());
        files.setLastModifiedBy(sessionItem.getUserId());

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
    private CaseFileRelations insCaseFileRelations(String caseId, String filesId) {
        // session取得
        SessionItems sessionItem = getSessionItems();
        CaseFileRelations caseFileRelations = new CaseFileRelations();
        // 自動生成GIUD
        caseFileRelations.setId(utilService.GetGuid());
        caseFileRelations.setPlatformId(sessionItem.getPlatformId());
        caseFileRelations.setCaseId(sessionItem.getCaseId());
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
        caseFileRelations.setLastModifiedBy(sessionItem.getUserId());
        return caseFileRelations;
    }

    /**
     * Sessionに値を取得仮用
     *
     * @param param1
     * @return SessionItems
     * @throws
     */
    private SessionItems getSessionItems() {
        // session設定
        SessionItems sessionItem = new SessionItems();
        sessionItem.setPlatformId("P025");
        sessionItem.setCaseId("0000000032");
        // セッション情報の和解案id
        sessionItem.setId("12D99B43CD8A4E8AA0A2131240634143");
        // ログインユーザが申立人場合：1
        // ログインユーザが相手方場合：2
        sessionItem.setFlag(1);
        // ログインユーザ
        sessionItem.setUserId("ログインユーザ");
        return sessionItem;
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
