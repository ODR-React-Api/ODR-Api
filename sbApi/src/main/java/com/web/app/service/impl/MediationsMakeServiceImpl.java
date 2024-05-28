package com.web.app.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Date;
import java.util.Iterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.web.app.service.MediationsMakeService;
import com.web.app.service.UtilService;
import org.thymeleaf.util.StringUtils;
import com.web.app.domain.Entity.CaseFileRelations;
import com.web.app.domain.Entity.CaseMediations;
import com.web.app.domain.Entity.Files;
import com.web.app.domain.mediationsMake.Mediation;
import com.web.app.domain.mediationsMake.ResultMediation;
import com.web.app.domain.mediationsMake.SubsidiaryFile;
import com.web.app.domain.constants.Constants;
import com.web.app.mapper.GetMediationsDataMapper;
import com.web.app.mapper.InsMediationsDataMapper;
import com.web.app.mapper.SaveMeditonMapper;

/**
 * 調停案作成画面
 * 
 * @author DUC 賈文志
 * @since 2024/05/24
 * @version 1.0
 */
@Service
public class MediationsMakeServiceImpl implements MediationsMakeService {

    // 調停案データ新規登録
    @Autowired
    private InsMediationsDataMapper insMediationsDataMapper;

    // 調停案データ取得マッパーオブジェクト
    @Autowired
    private GetMediationsDataMapper getMediationsDataMapper;

    // 調停案データ更新マッパーオブジェクト
    @Autowired
    private SaveMeditonMapper saveMeditonMapper;

    // utilサービス
    @Autowired
    private UtilService utilService;

    /**
     * 
     * API_ID:調停案データ更新
     * 
     * @param mediationId:調停案id
     * @return この調停案idがDBに存在する個数
     */
    @Override
    public int isExistMediations(String mediationId) {
        return saveMeditonMapper.isExistMediations(mediationId);
    }

    /**
     * 
     * API_ID:調停案データ更新
     * 
     * 処理後のデータをDBに更新する
     * 
     * @param resultMediation:更新用調停案データ
     * @return 調停案データ更新結果
     * @throws Exception
     */
    @Override
    @Transactional
    public void saveMediton(ResultMediation resultMediation) throws Exception {
        // フロントに添付ファイル
        List<SubsidiaryFile> files = resultMediation.getFiles();
        // SQL文が更新されない場合は例外をスローし、DBが更新されないようにトランザクションをロールバックさせる
        if (!(saveMeditonMapper.updateMediations(setMediation(resultMediation, null, true)) > 0)) {
            throw new RuntimeException();
        }
        if (files == null || files.isEmpty()) {
            return;
        } else {
            Mediation mediation = null;
            for (SubsidiaryFile file : files) {
                int deleteFlag = file.getDeleteFlag();
                switch (deleteFlag) {
                    // 添付ファイルを削除
                    case 1:
                        mediation = setMediation(resultMediation, file, false);
                        if (!(saveMeditonMapper.deleteFiles(mediation) > 0
                                && saveMeditonMapper.deleteFileRelations(mediation) > 0)) {
                            throw new RuntimeException();
                        }
                        break;
                    // 追加添付ファイル
                    case 2:
                        mediation = setMediation(resultMediation, file, false);
                        saveMeditonMapper.addFiles(mediation);
                        saveMeditonMapper.addFileRelations(mediation);
                        break;
                    // 添付ファイルは変更されていません
                    default:
                        break;
                }
            }
        }
    }

    /**
     * 
     * API_ID:調停案データ更新
     * 
     * 取得したデータをDBで使用できるエンティティークラスに変換する
     * 
     * @param resultMediation:更新用調停案データ
     * @param files:更新が必要なファイル
     * @return DB更新用データ
     */
    private Mediation setMediation(ResultMediation resultMediation, SubsidiaryFile file, boolean isMediation) {
        Mediation mediation = new Mediation();
        // 時間フォーマットの変換
        mediation.setLastModifiedDate(getStringDate(null));
        mediation.setPlatformId(resultMediation.getPlatformId());
        mediation.setUserId(resultMediation.getUserId());
        if (isMediation) {
            mediation.setExpectResloveTypeValue(
                    StringUtils.join(resultMediation.getExpectResloveTypeValue(), ","));
            mediation.setOtherContext(resultMediation.getOtherContext());
            mediation.setPayAmount(resultMediation.getPayAmount());
            mediation.setCounterClaimPayment(resultMediation.getCounterClaimPayment());
            mediation.setPaymentEndDate(getStringDate(resultMediation.getPaymentEndDate()));
            mediation.setShipmentPayType(resultMediation.getShipmentPayType());
            mediation.setSpecialItem(resultMediation.getSpecialItem());
            mediation.setMediationId(resultMediation.getMediationId());
            return mediation;
        }
        if (file.getDeleteFlag() == 1) {
            mediation.setFileId(file.getFileId());
        } else {
            mediation.setFileId(utilService.GetGuid());
            mediation.setCaseId(resultMediation.getCaseId());
            mediation.setFileName(file.getFileName());
            mediation.setFileExtension(file.getFileExtension());
            mediation.setFileUrl(file.getFileUrl());
            mediation.setFileSize(file.getFileSize());
            mediation.setFileRelationId(utilService.GetGuid());
            mediation.setMediationId(resultMediation.getMediationId());
        }
        return mediation;
    }

    /**
     * 時間フォーマットの変換
     * Date -> String
     * 
     * @param date 変換が必要な時間（NULLの場合はシステム時間を取得）
     * @return 日付文字列
     */
    private String getStringDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        try {
            if (date == null) {
                return sdf.format(System.currentTimeMillis());
            } else {
                return sdf.format(date);
            }
        } catch (Exception e) {
            // 日付変換に失敗した場合
            return null;
        }
    }

    /**
     * 
     * API_ID:調停案データ取得
     * 
     * DBを検索して処理後のデータをコントローラに渡す
     * 
     * @param ResultMediation コントローラから渡された画面データ
     * @return DB検索result
     */
    @Override
    public ResultMediation getMediationsData(ResultMediation resultMediation) {
        // DBデータ取得
        List<Mediation> mediation = getMediationsDataMapper.selectMediationsData(resultMediation.getCaseId(),
                resultMediation.getPlatformId());
        if (mediation != null && !mediation.isEmpty()) {
            // データ取得に成功した場合、取得したデータを処理する
            resultMediation = setResult(mediation, resultMediation);
        }
        return resultMediation;
    }

    /**
     * 
     * API_ID:調停案データ取得
     * 
     * 取得したList結果をフロントエンドが使用するエンティティークラスに変換する
     * 
     * @param mediation:检索结果
     * @param resultMediation:目标结果集
     * @return resultMediation
     */
    private ResultMediation setResult(List<Mediation> mediation, ResultMediation resultMediation) {
        resultMediation.setFiles(null);
        List<SubsidiaryFile> files = new ArrayList<SubsidiaryFile>();
        Iterator<Mediation> iterator = mediation.iterator();
        // カウンタカウンタ
        int count = 0;
        // 集合を反復器で巡回し、resultMediationに順次渡す
        while (iterator.hasNext()) {
            Mediation next = iterator.next();
            SubsidiaryFile file = new SubsidiaryFile();
            file.setFileName(next.getFileName());
            file.setFileUrl(next.getFileUrl());
            files.add(file);
            // コレクション内の添付ファイル以外のフィールドはすべて同じなので、値は1回だけとります
            if (count == 0) {
                // 文字列をカンマで分割
                String expectResloveTypeValue = next.getExpectResloveTypeValue();
                String[] values = expectResloveTypeValue.split(",");
                resultMediation.setExpectResloveTypeValue(new ArrayList<String>());
                for (String value : values) {
                    resultMediation.getExpectResloveTypeValue().add(value);
                }
                // 設定エラーメッセージ
                resultMediation.setStatus(next.getStatus());
                if (resultMediation.getStatus() == 2 || resultMediation.getStatus() == 3
                        || resultMediation.getStatus() == 4) {
                    resultMediation.setMeg(Constants.AGREEMENT_MESSAGE);
                } else if (resultMediation.getStatus() == 5 || resultMediation.getStatus() == 6) {
                    resultMediation.setMeg(Constants.REFUSED_MESSAGE);
                } else if (resultMediation.getStatus() == 7 || resultMediation.getStatus() == 8) {
                    resultMediation.setMeg(Constants.CONFIRMED_MESSAGE);
                } else if (resultMediation.getStatus() == 9) {
                    resultMediation.setMeg(Constants.FINSHED_MESSAGE);
                } else {
                    resultMediation.setMeg(null);
                }
                resultMediation.setOtherContext(next.getOtherContext());
                resultMediation.setPayAmount(next.getPayAmount());
                resultMediation.setCounterClaimPayment(next.getCounterClaimPayment());
                // 時間フォーマットの変換
                resultMediation.setPaymentEndDate(stringtoDate(next.getPaymentEndDate()));
                resultMediation.setShipmentPayType(next.getShipmentPayType());
                // 時間フォーマットの変換
                resultMediation.setAgreementDate(stringtoDate(next.getAgreementDate()));
                resultMediation.setSpecialItem(next.getSpecialItem());
                resultMediation.setUserId(next.getUserId());
            }
            count++;
        }
        resultMediation.setFiles(files);
        return resultMediation;
    }

    /**
     * 時間フォーマットの変換
     * String -> Date
     * 
     * @param dateString 変換が必要な日付文字列
     * @return 変換後の日付
     */
    private Date stringtoDate(String dateString) {
        Date date = null;
        try {
            if (dateString != null) {
                DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = fmt.parse(dateString);
            }
        } catch (Exception e) {
            date = null;
        }
        return date;
    }

    /**
     * 
     * 調停案データ新規登録
     * 
     * @param resultMediation フロントから転送されたデータを受信する
     */
    @Transactional
    @Override
    public int insMediationsData(ResultMediation resultMediation) {
        // 調停案データ新規登録API
        // 「調停案」新規登録に必要なデータを保存する
        CaseMediations caseMediations = new CaseMediations();
        String caseMediationsId = utilService.GetGuid();
        caseMediations.setId(caseMediationsId);
        caseMediations.setPlatformId(resultMediation.getPlatformId());
        caseMediations.setCaseId(resultMediation.getCaseId());
        caseMediations.setExpectResloveTypeValue(StringUtils.join(resultMediation.getExpectResloveTypeValue(), ","));
        caseMediations.setPayAmount(resultMediation.getPayAmount());
        caseMediations.setCounterClaimPayment(resultMediation.getCounterClaimPayment());
        caseMediations.setPaymentEndDate(getStringDate(resultMediation.getPaymentEndDate()));
        caseMediations.setShipmentPayType(resultMediation.getShipmentPayType());
        caseMediations.setSpecialItem(resultMediation.getSpecialItem());
        // ローグ・ユアサの保存
        caseMediations.setUserId(resultMediation.getUserId());
        caseMediations.setLastModifiedDate(getStringDate(null));
        caseMediations.setLastModifiedBy(resultMediation.getUserId());
        // 「調停案」新規登録
        int mediationCaseInsert = insMediationsDataMapper.insMediationsData(caseMediations);
        // 「調停案」データの新規登録に成功
        if (mediationCaseInsert == Constants.NUM_1) {
            // フロントから転送されたファイルデータを保存する
            List<SubsidiaryFile> filesData = resultMediation.getFiles();
            // ファイルデータを巡回して個別にログインする
            for (int i = 0; i < filesData.size(); i++) {
                // ファイルを添付してアップロードするかどうかを判断する
                if (filesData.get(i).getFileUrl() != null) {
                    Files insFiles = new Files();
                    String filesid = utilService.GetGuid();
                    insFiles.setId(filesid);
                    insFiles.setPlatformId(resultMediation.getPlatformId());
                    insFiles.setCaseId(resultMediation.getCaseId());
                    insFiles.setFileName(filesData.get(i).getFileName());
                    insFiles.setFileExtension(filesData.get(i).getFileExtension());
                    insFiles.setFileUrl(filesData.get(i).getFileUrl());
                    insFiles.setFileSize(filesData.get(i).getFileSize());
                    // ローグ・ユアサの保存
                    insFiles.setRegisterUserId(resultMediation.getUserId());
                    // システム日払いの保存
                    insFiles.setRegisterDate(getStringDate(null));
                    insFiles.setLastModifiedDate(getStringDate(null));
                    // ローグ・ユアサの保存
                    insFiles.setLastModifiedBy(resultMediation.getUserId());
                    // 「添付ファイル」の新規登録
                    int insertFiles = insMediationsDataMapper.insertFiles(insFiles);
                    // 「添付ファイル」新規登録が成功した場合
                    if (insertFiles == Constants.NUM_1) {
                        // 「案件-添付ファイル」テーブルのデータを保存する
                        CaseFileRelations caseFileRelations = new CaseFileRelations();
                        String CaseFileRelationsid = utilService.GetGuid();
                        caseFileRelations.setId(CaseFileRelationsid);
                        caseFileRelations.setPlatformId(resultMediation.getPlatformId());
                        caseFileRelations.setCaseId(resultMediation.getCaseId());
                        caseFileRelations.setRelatedId(caseMediationsId);
                        caseFileRelations.setFileId(filesid);
                        caseFileRelations.setLastModifiedDate(getStringDate(null));
                        // ローグ・ユアサの保存
                        caseFileRelations.setLastModifiedBy(resultMediation.getUserId());
                        // 「案件-添付ファイルリレーション」新規登録
                        int insCaseFileRelations = insMediationsDataMapper.insCaseFileRelations(caseFileRelations);
                        if (insCaseFileRelations == Constants.NUM_0) {
                            return Constants.NUM_0;
                        }
                    } else {
                        return Constants.NUM_0;
                    }
                }
            }
            return Constants.NUM_1;
        } else {
            return Constants.NUM_0;
        }
    }
}