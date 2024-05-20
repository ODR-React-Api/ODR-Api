package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.NegotiatMake.SettlementDraftDataCaseFileRelations;
import com.web.app.domain.NegotiatMake.SettlementDraftDataCaseNegotiations;
import com.web.app.domain.NegotiatMake.SettlementDraftDataFiles;
import com.web.app.domain.NegotiatMake.SettlementDraftDataResult;
import com.web.app.domain.NegotiatMake.SettlementDraftDataSelectedInfo;
import com.web.app.domain.NegotiatMake.AddFile;
import com.web.app.domain.NegotiatMake.FromSessionLogin;
import com.web.app.mapper.GetNegotiationsDataMapper;
import com.web.app.mapper.GetNegotiationsStatusMapper;
import com.web.app.mapper.InsNegotiationTempMapper;
import com.web.app.mapper.UpdNegotiationsTempMapper;
import com.web.app.service.NegotiatMakeService;

/**
 * 和解案作成画面ServiceImpl
 * 
 * @author DUC 朱暁芳
 * @since 2024/04/23
 * @version 1.0
 */
@Service
public class NegotiatMakeServiceImpl implements NegotiatMakeService {

    // API_和解案下書きデータ取得
    @Autowired
    private GetNegotiationsDataMapper getNegotiationsDataMapper;

    // 下書き保存処理
    @Autowired
    private GetNegotiationsStatusMapper getNegotiationsStatusMapper;

    // API_和解案下書きデータ新規登録
    @Autowired
    private InsNegotiationTempMapper insNegotiationTempMapper;

    // API_和解案下書きデータ更新
    @Autowired
    private UpdNegotiationsTempMapper updNegotiationsTempMapper;

    /**
     * 和解案下書きデータ取得API
     * 
     * @param sessionLogin セッション情報 と ログイン情報渡された
     * @return 戻り値は「 和解案作成 和解案下書きデータ取得」に返される
     */
    @Override
    public SettlementDraftDataResult settlementDraftDataInfoSearch(FromSessionLogin sessionLogin) {
        // 初期画面表示処理時、和解案下書きデータ取得
        SettlementDraftDataSelectedInfo selectedInfo = getNegotiationsDataMapper
                .settlementDraftDataInfoSearch(sessionLogin.getSessionCaseId());

        SettlementDraftDataResult settlementResult = new SettlementDraftDataResult();
        // 和解案下書きデータ取得があり場合
        if (selectedInfo != null) {
            // 和解案下書きデータ取得できる場合、下書き保存（ボタン）と保存して編集依頼（リンク）の表示方法
            settlementResult.setStatus(selectedInfo.getStatus());
            // 和解案下書きデータ取得できる場合、対応方法
            settlementResult.setCorrespondence(selectedInfo.getExpectResloveTypeValue());
            // 和解案下書きデータ取得できる場合、そのた
            String sonota = "その他";
            Pattern pattern = Pattern.compile(sonota);
            Matcher matcher = pattern.matcher(selectedInfo.getExpectResloveTypeValue());
            if (matcher.find()) {
                settlementResult.setOtherContext(selectedInfo.getExpectResloveTypeValue());
            }
        } else {
            // 和解案下書きデータ取得がなし場合
            settlementResult.setStatus(0);
            settlementResult.setCorrespondence("");
            settlementResult.setOtherContext("");
        }
        return settlementResult;
    }

    /**
     * 下書き保存処理
     * 
     * @param sessionLogin セッション情報 と ログイン情報渡された
     * @return 戻り値は「 下書き保存処理」が返された値
     * @throws Exception エラーの説明内容
     */
    @SuppressWarnings("null")
    @Override
    public SettlementDraftDataResult settlementDraftGetCaseNegotiationsStatusInfoSearch(
            FromSessionLogin sessionLogin) {
        SettlementDraftDataResult result = new SettlementDraftDataResult();

        // 現在の和解案状態を抽出
        SettlementDraftDataCaseNegotiations caseNegotiationsStatus = getNegotiationsStatusMapper
                .settlementDraftNegotiationsInfoSearch(sessionLogin.getSessionCaseId(), sessionLogin.getPlatformId());

        try {
            // ログインユーザが当案件に対して、立場が申立人の場合
            if (sessionLogin.getFlag() == 1 && caseNegotiationsStatus == null) {
                // 取得したレコードなし場合、＜新規登録＞
                // 「和解案」,「添付ファイル」と 「案件-添付ファイルリレーション」新規登録
                Integer selectedStatus = 13;
                // 「和解案」の新規登録
                String caseNegotiationsGuid = insInsertCaseNegotiations(selectedStatus, sessionLogin).getId();

                // 「添付ファイル」の新規登録
                List<String> filesGuid = new ArrayList<>();
                filesGuid = insInsertCaseFiles(sessionLogin);
                // 「案件-添付ファイルリレーション」の新規登録
                insInsertCaseFileRelations(sessionLogin, caseNegotiationsGuid, filesGuid);

            } else if (sessionLogin.getFlag() == 1
                    && (caseNegotiationsStatus.getStatus() == 7
                            || caseNegotiationsStatus.getStatus() == 8
                            || caseNegotiationsStatus.getStatus() == 13
                            || caseNegotiationsStatus.getStatus() == 14)) {
                // Status in 7, 8, 13, 14場合、＜更新登録＞
                // 「和解案」更新
                updateCaseNegotiations(caseNegotiationsStatus, sessionLogin);
                // 「和解案」のidを取得する
                String caseNegotiationsUpGuid = updNegotiationsTempMapper
                        .settlementDraftNegotiationsInfoSearch(sessionLogin.getSessionCaseId(),
                                sessionLogin.getPlatformId())
                        .getId();

                if (sessionLogin.getSettlementDraftFromWeb().getFiles().size() != 0) {
                    // 「添付ファイル」論理削除
                    updateCaseFiles(sessionLogin);
                    // 「案件-添付ファイルリレーション」論理削除
                    updateCaseFileRelations(sessionLogin);
                    // 「添付ファイル」の新規登録
                    List<String> filesUpGuid = new ArrayList<>();
                    filesUpGuid = updInsertCaseFiles(sessionLogin);
                    // 「案件-添付ファイルリレーション」の新規登録
                    updInsertCaseFileRelations(sessionLogin, caseNegotiationsUpGuid, filesUpGuid);
                }
            } else {
                // 異常終了（メッセージ例：申立の状態が別ユーザより更新されました。申立一覧画面から確認するようにお願いします。）
                String message = "申立の状態が別ユーザより更新されました。申立一覧画面から確認するようにお願いします。";
                result.setMessage(message);
            }
            result.setMessage("");
        } catch (Exception e) {
            // 異常終了（メッセージ例：申立の状態が別ユーザより更新されました。申立一覧画面から確認するようにお願いします。）
            String message = "申立の状態が別ユーザより更新されました。申立一覧画面から確認するようにお願いします。";
            result.setMessage(message);
        }

        try {
            // ログインユーザが当案件に対して、立場が相手方のの場合
            if (sessionLogin.getFlag() == 2 && caseNegotiationsStatus == null) {
                // 取得したレコードなし場合、＜新規登録＞
                // 「和解案」,「添付ファイル」と 「案件-添付ファイルリレーション」新規登録
                Integer selectedStatus = 0;
                // 「和解案」の新規登録
                String caseNegotiationsGuid = insInsertCaseNegotiations(selectedStatus, sessionLogin).getId();

                // 「添付ファイル」の新規登録
                List<String> filesGuid = new ArrayList<>();
                filesGuid = insInsertCaseFiles(sessionLogin);
                // 「案件-添付ファイルリレーション」の新規登録
                insInsertCaseFileRelations(sessionLogin, caseNegotiationsGuid, filesGuid);

            } else if (sessionLogin.getFlag() == 2
                    && (caseNegotiationsStatus.getStatus() == 0
                            || caseNegotiationsStatus.getStatus() == 1
                            || caseNegotiationsStatus.getStatus() == 10
                            || caseNegotiationsStatus.getStatus() == 11)) {
                // Status in 0, 1, 10, 11 場合、＜更新登録＞
                // 「和解案」更新
                updateCaseNegotiations(caseNegotiationsStatus, sessionLogin);
                // 「和解案」のidを取得する
                String caseNegotiationsUpGuid = updNegotiationsTempMapper
                        .settlementDraftNegotiationsInfoSearch(sessionLogin.getSessionCaseId(),
                                sessionLogin.getPlatformId())
                        .getId();

                if (sessionLogin.getSettlementDraftFromWeb().getFiles().size() != 0) {
                    // 「添付ファイル」論理削除
                    updateCaseFiles(sessionLogin);
                    // 「案件-添付ファイルリレーション」論理削除
                    updateCaseFileRelations(sessionLogin);
                    // 「添付ファイル」の新規登録
                    List<String> filesUpGuid = new ArrayList<>();
                    filesUpGuid = updInsertCaseFiles(sessionLogin);
                    // 「案件-添付ファイルリレーション」の新規登録
                    updInsertCaseFileRelations(sessionLogin, caseNegotiationsUpGuid, filesUpGuid);
                }
            } else {
                // 異常終了（メッセージ例：申立の状態が別ユーザより更新されました。申立一覧画面から確認するようにお願いします。）
                String message = "申立の状態が別ユーザより更新されました。申立一覧画面から確認するようにお願いします。";
                result.setMessage(message);
            }
            result.setMessage("");
        } catch (Exception e) {
            // 異常終了（メッセージ例：申立の状態が別ユーザより更新されました。申立一覧画面から確認するようにお願いします。）
            String message = "申立の状態が別ユーザより更新されました。申立一覧画面から確認するようにお願いします。";
            result.setMessage(message);
        }
        return result;
    }

    // 新規登録API「添付ファイル」新規登録
    // TODO 内部ロジック生成ファイルURL 再調査
    // TODO 内部ロジック生成ファイルサイ 再調査
    private List<String> insInsertCaseFiles(FromSessionLogin sessionLogin) {
        SettlementDraftDataFiles files = new SettlementDraftDataFiles();

        files.setPlatformId(sessionLogin.getPlatformId());
        files.setCaseId(sessionLogin.getSessionCaseId());
        files.setFileBlobStorageId(null);
        // ログインユーザ
        files.setRegisterUserId(sessionLogin.getUserId());
        // システム日付
        files.setRegisterDate(new Date());
        files.setOther01(null);
        files.setOther02(null);
        files.setOther03(null);
        files.setOther04(null);
        files.setOther05(null);
        files.setDeleteFlag(0);
        // システム日付
        files.setLastModifiedDate(new Date());
        // ログインユーザ
        files.setLastModifiedBy(sessionLogin.getUserId());

        // フロントに添付ファイル
        List<AddFile> addfiles = sessionLogin.getSettlementDraftFromWeb().getFiles();
        // 「添付ファイル」のidを保存し
        List<String> fileid = new ArrayList<>();
        // フロントから添付ファイルを取得しが順番トラバース
        for (AddFile file : addfiles) {
            // 自動生成GIUD
            String giud = GetGuid();
            files.setId(giud);
            files.setFileName(file.getFileName());
            files.setFileExtension(file.getFileExtension());
            // 内部ロジック生成ファイルURL
            files.setFileUrl(file.getFileUrl() + giud
                    + "." + file.getFileExtension());
            // 内部ロジック生成ファイルサイ
            files.setFileSize(file.getFileSize());
            // 「添付ファイル」のidを保存した
            fileid.add(giud);
            // テーブル「添付ファイル」新規登録
            insNegotiationTempMapper.insertFilesInfo(files);
        }
        return fileid;
    }

    // 新規登録API「案件-添付ファイルリレーション」新規登録
    private void insInsertCaseFileRelations(FromSessionLogin sessionLogin, String caseNegotiationsGuid,
            List<String> filesGuid) {
        SettlementDraftDataCaseFileRelations caseFileRelations = new SettlementDraftDataCaseFileRelations();

        caseFileRelations.setPlatformId(sessionLogin.getPlatformId());
        caseFileRelations.setCaseId(sessionLogin.getSessionCaseId());
        caseFileRelations.setRelatedId(caseNegotiationsGuid);
        caseFileRelations.setOther01(null);
        caseFileRelations.setOther02(null);
        caseFileRelations.setOther03(null);
        caseFileRelations.setOther04(null);
        caseFileRelations.setOther05(null);
        caseFileRelations.setDeleteFlag(0);
        // システム日付
        caseFileRelations.setLastModifiedDate(new Date());
        // ログインユーザ
        caseFileRelations.setLastModifiedBy(sessionLogin.getUserId());

        for (String gui : filesGuid) {
            // 自動生成GIUD
            String giud = GetGuid();
            // ID
            caseFileRelations.setId(giud);
            // ファイルID
            caseFileRelations.setFileId(gui);

            // テーブル「案件-添付ファイルリレーション」新規登録
            insNegotiationTempMapper.insertCaseFileRelationsInfo(caseFileRelations);
        }
        return;
    }

    // 新規登録API「和解案」新規登録
    private SettlementDraftDataCaseNegotiations insInsertCaseNegotiations(Integer selectedStatus,
            FromSessionLogin sessionLogin) {

        // 「和解案」の新規登録
        SettlementDraftDataCaseNegotiations caseNegotiations = new SettlementDraftDataCaseNegotiations();
        // 自動生成GIUD
        String giud1 = GetGuid();
        caseNegotiations.setId(giud1);
        caseNegotiations.setPlatformId(sessionLogin.getPlatformId());
        caseNegotiations.setCaseId(sessionLogin.getSessionCaseId());
        caseNegotiations.setStatus(selectedStatus);
        caseNegotiations
                .setExpectResloveTypeValue(sessionLogin.getSettlementDraftFromWeb().getExpectResloveTypeValue());
        caseNegotiations.setOtherContext(sessionLogin.getSettlementDraftFromWeb().getOtherContext());
        caseNegotiations.setHtmlContext(null);
        caseNegotiations.setHtmlContext2(null);
        caseNegotiations.setPayAmount(sessionLogin.getSettlementDraftFromWeb().getPayAmount());
        caseNegotiations.setCounterClaimPayment(sessionLogin.getSettlementDraftFromWeb().getCounterClaimPayment());
        caseNegotiations.setPaymentEndDate(sessionLogin.getSettlementDraftFromWeb().getPaymentEndDate());
        caseNegotiations.setShipmentPayType(sessionLogin.getSettlementDraftFromWeb().getShipmentPayType());
        caseNegotiations.setSpecialItem(sessionLogin.getSettlementDraftFromWeb().getSpecialItem());
        // ログインユーザ
        caseNegotiations.setUserId(sessionLogin.getUserId());
        caseNegotiations.setSubmitDate(null);
        caseNegotiations.setAgreementDate(null);
        caseNegotiations.setDeleteFlag(0);
        // システム日付
        caseNegotiations.setLastModifiedDate(new Date());
        // ログインユーザ
        caseNegotiations.setLastModifiedBy(sessionLogin.getUserId());
        // テーブル「和解案」新規登録
        insNegotiationTempMapper.insertCaseNegotiationsInfo(caseNegotiations);
        return caseNegotiations;
    }

    // 更新登録API「案件-添付ファイルリレーション」論理削除
    private void updateCaseFileRelations(FromSessionLogin sessionLogin) {
        SettlementDraftDataCaseFileRelations caseFileRelations = new SettlementDraftDataCaseFileRelations();

        caseFileRelations.setDeleteFlag(1);
        // システム日付
        caseFileRelations.setLastModifiedDate(new Date());
        // ログインユーザ
        caseFileRelations.setLastModifiedBy(sessionLogin.getUserId());
        // テーブル「案件-添付ファイルリレーション」論理削除
        updNegotiationsTempMapper.updateCaseFileRelationsInfo(caseFileRelations,
                sessionLogin.getSessionObjCaseFileRelationsId());
        return;
    }

    // 更新登録API「添付ファイル」論理削除
    private void updateCaseFiles(FromSessionLogin sessionLogin) {

        SettlementDraftDataFiles files = new SettlementDraftDataFiles();
        // 「添付ファイル」更新
        files.setDeleteFlag(1);
        // システム日付
        files.setLastModifiedDate(new Date());
        // ログインユーザ
        files.setLastModifiedBy(sessionLogin.getUserId());

        // 「添付ファイル」論理削除
        updNegotiationsTempMapper.updateFilesInfo(files, sessionLogin.getSessionObjFileId());
    }

    // 更新登録API「和解案」更新
    private SettlementDraftDataCaseNegotiations updateCaseNegotiations(
            SettlementDraftDataCaseNegotiations caseNegotiationsData,
            FromSessionLogin sessionLogin) {

        Integer selectedStatus = caseNegotiationsData.getStatus();
        // 「和解案」
        SettlementDraftDataCaseNegotiations caseNegotiations = new SettlementDraftDataCaseNegotiations();

        // 「和解案」更新、画面項目から
        caseNegotiations.setExpectResloveTypeValue(
                sessionLogin.getSettlementDraftFromWeb().getExpectResloveTypeValue());
        caseNegotiations.setOtherContext(sessionLogin.getSettlementDraftFromWeb().getOtherContext());
        caseNegotiations.setPayAmount(sessionLogin.getSettlementDraftFromWeb().getPayAmount());
        caseNegotiations.setCounterClaimPayment(sessionLogin.getSettlementDraftFromWeb().getCounterClaimPayment());
        caseNegotiations.setPaymentEndDate(sessionLogin.getSettlementDraftFromWeb().getPaymentEndDate());
        caseNegotiations.setShipmentPayType(sessionLogin.getSettlementDraftFromWeb().getShipmentPayType());
        caseNegotiations.setSpecialItem(sessionLogin.getSettlementDraftFromWeb().getSpecialItem());
        // システム日付
        caseNegotiations.setLastModifiedDate(new Date());
        // ログインユーザ
        caseNegotiations.setLastModifiedBy(sessionLogin.getUserId());
        // ステータス
        if (selectedStatus == 7) {
            caseNegotiations.setStatus(8);
        } else if (selectedStatus == 10) {
            caseNegotiations.setStatus(11);
        } else {
            caseNegotiations.setStatus(selectedStatus);
        }
        // 更新前Status=0,1,2,13,14,15の場合、変更なし
        if (selectedStatus == 0 || selectedStatus == 1 || selectedStatus == 2 || selectedStatus == 13
                || selectedStatus == 14 || selectedStatus == 15) {
            caseNegotiations.setUserId(caseNegotiationsData.getUserId());
        } else {
            // 更新前Status=０，１，２, 13, 14, 15の以外場合、ログインユーザで更新する
            // ログインユーザ
            caseNegotiations.setUserId(sessionLogin.getUserId());
        }
        // テーブル「和解案」更新
        updNegotiationsTempMapper.updateCaseNegotiationsInfo(caseNegotiations,
                sessionLogin.getSessionCaseNegCotiationsId());
        return caseNegotiations;
    }

    // 更新登録「添付ファイル」新規登録
    // TODO 内部ロジック生成ファイルURL 再調査
    // TODO 内部ロジック生成ファイルサイ 再調査
    private List<String> updInsertCaseFiles(FromSessionLogin sessionLogin) {
        SettlementDraftDataFiles files = new SettlementDraftDataFiles();

        files.setPlatformId(sessionLogin.getPlatformId());
        files.setCaseId(sessionLogin.getSessionCaseId());
        files.setFileBlobStorageId(null);
        // ログインユーザ
        files.setRegisterUserId(sessionLogin.getUserId());
        // システム日付
        files.setRegisterDate(new Date());
        files.setOther01(null);
        files.setOther02(null);
        files.setOther03(null);
        files.setOther04(null);
        files.setOther05(null);
        files.setDeleteFlag(0);
        // システム日付
        files.setLastModifiedDate(new Date());
        // ログインユーザ
        files.setLastModifiedBy(sessionLogin.getUserId());
        // フロントに添付ファイル
        List<AddFile> addfiles = sessionLogin.getSettlementDraftFromWeb().getFiles();
        // 「添付ファイル」のidを保存し
        List<String> fileid = new ArrayList<>();
        // フロントから添付ファイルを取得しが順番トラバース
        for (AddFile file : addfiles) {
            // 自動生成GIUD
            String giud = GetGuid();
            files.setId(giud);
            files.setFileName(file.getFileName());
            files.setFileExtension(file.getFileExtension());
            // 内部ロジック生成ファイルURL
            files.setFileUrl(file.getFileUrl() + giud
                    + "." + file.getFileExtension());
            // 内部ロジック生成ファイルサイ
            files.setFileSize(file.getFileSize());
            // 「添付ファイル」のidを保存した
            fileid.add(giud);

            // テーブル「添付ファイル」新規登録
            updNegotiationsTempMapper.insertFilesInfo(files);
        }
        return fileid;
    }

    // 更新登録API「案件-添付ファイルリレーション」新規登録
    private void updInsertCaseFileRelations(FromSessionLogin sessionLogin, String caseNegotiationsGuid,
            List<String> filesGuid) {
        SettlementDraftDataCaseFileRelations caseFileRelations = new SettlementDraftDataCaseFileRelations();

        caseFileRelations.setPlatformId(sessionLogin.getPlatformId());
        caseFileRelations.setCaseId(sessionLogin.getSessionCaseId());
        caseFileRelations.setRelatedId(caseNegotiationsGuid);
        caseFileRelations.setOther01(null);
        caseFileRelations.setOther02(null);
        caseFileRelations.setOther03(null);
        caseFileRelations.setOther04(null);
        caseFileRelations.setOther05(null);
        caseFileRelations.setDeleteFlag(0);
        // システム日付
        caseFileRelations.setLastModifiedDate(new Date());
        // ログインユーザ
        caseFileRelations.setLastModifiedBy(sessionLogin.getUserId());

        for (String gui : filesGuid) {
            // 自動生成GIUD
            String giud = GetGuid();
            // ID
            caseFileRelations.setId(giud);
            // ファイルID
            caseFileRelations.setFileId(gui);

            // テーブル「案件-添付ファイルリレーション」新規登録
            updNegotiationsTempMapper.insertCaseFileRelationsInfo(caseFileRelations);
        }
        return;
    }

    // 自動生成GIUD
    public String GetGuid() {
        String strGuid = UUID.randomUUID().toString();
        strGuid = strGuid.replace("-", "").toUpperCase();
        return strGuid;
    }

}