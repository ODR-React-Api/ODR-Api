package com.web.app.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;
import com.web.app.domain.constants.Constants;
import com.web.app.domain.mediationsMake.Mediation;
import com.web.app.domain.mediationsMake.ResultMediation;
import com.web.app.domain.mediationsMake.SubsidiaryFile;
import com.web.app.mapper.GetMediationsDataMapper;
import com.web.app.mapper.SaveMeditonMapper;
import com.web.app.service.MediationsMakeService;
import com.web.app.service.UtilService;

/**
 * 調停案作成画面
 * 
 * @author DUC 徐義然
 * @since 2024/05/07
 * @version 1.0
 */
@Service
public class MediationsMakeServiceImpl implements MediationsMakeService {
    
    //調停案データ取得マッパーオブジェクト
    @Autowired
    private GetMediationsDataMapper getMediationsDataMapper;

    //調停案データ更新マッパーオブジェクト
    @Autowired
    private SaveMeditonMapper saveMeditonMapper;

    //utilサービス
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
    public int isExistMediations(String mediationId){
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
        //フロントに添付ファイル
        List<SubsidiaryFile> files = resultMediation.getFiles();
        //SQL文が更新されない場合は例外をスローし、DBが更新されないようにトランザクションをロールバックさせる
        if (!(saveMeditonMapper.updateMediations(setMediation(resultMediation, null, true)) > 0)) {
            throw new RuntimeException();
        }
        if(files == null || files.isEmpty()){
            return;
        }else{
            Mediation mediation = null;
            for (SubsidiaryFile file : files) {
                int deleteFlag = file.getDeleteFlag();
                switch (deleteFlag) {
                    //添付ファイルを削除
                    case 1:
                        mediation = setMediation(resultMediation, file, false);
                        if (!(saveMeditonMapper.deleteFiles(mediation) > 0 && saveMeditonMapper.deleteFileRelations(mediation) > 0)) {
                            throw new RuntimeException();
                        }
                        break;
                    //追加添付ファイル
                    case 2:
                        mediation = setMediation(resultMediation, file, false);
                        saveMeditonMapper.addFiles(mediation);
                        saveMeditonMapper.addFileRelations(mediation);
                        break;
                    //添付ファイルは変更されていません
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
    private Mediation setMediation(ResultMediation resultMediation,SubsidiaryFile file,boolean isMediation){
        Mediation mediation = new Mediation();
        //時間フォーマットの変換
        mediation.setLastModifiedDate(getStringDate(null));
        mediation.setPlatformId(resultMediation.getPlatformId());
        mediation.setUserId(resultMediation.getUserId());
        if (isMediation) {
            mediation.setExpectResloveTypeValue(
                StringUtils.join(resultMediation.getExpectResloveTypeValue(),",")
            );
            mediation.setOtherContext(resultMediation.getOtherContext());
            mediation.setPayAmount(resultMediation.getPayAmount());
            mediation.setCounterClaimPayment(resultMediation.getCounterClaimPayment());
            mediation.setPaymentEndDate(getStringDate(resultMediation.getPaymentEndDate()));
            mediation.setShipmentPayType(resultMediation.getShipmentPayType());
            mediation.setSpecialItem(resultMediation.getSpecialItem());
            mediation.setMediationId(resultMediation.getMediationId());
            return mediation;
        }
        if(file.getDeleteFlag() == 1){
            mediation.setFileId(file.getFileId());
        }else{
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
    private String getStringDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        try {
            if(date == null){
                return sdf.format(System.currentTimeMillis());
            }else{
                return sdf.format(date);
            }
        } catch (Exception e) {
            //日付変換に失敗した場合
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
    public ResultMediation getMediationsData(ResultMediation resultMediation){
        //DBデータ取得
        List<Mediation> mediation = getMediationsDataMapper.selectMediationsData(resultMediation.getCaseId(), resultMediation.getPlatformId());
        if (mediation != null && !mediation.isEmpty()) {
            //データ取得に成功した場合、取得したデータを処理する
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
    private ResultMediation setResult(List<Mediation> mediation,ResultMediation resultMediation){
        resultMediation.setFiles(null);
        List<SubsidiaryFile> files = new ArrayList<SubsidiaryFile>();
        Iterator<Mediation> iterator = mediation.iterator();
        //カウンタカウンタ
        int count = 0;
        //集合を反復器で巡回し、resultMediationに順次渡す
        while (iterator.hasNext()) {
            Mediation next = iterator.next();
            SubsidiaryFile file = new SubsidiaryFile();
            file.setFileName(next.getFileName());
            file.setFileUrl(next.getFileUrl());
            files.add(file);
            //コレクション内の添付ファイル以外のフィールドはすべて同じなので、値は1回だけとります
            if (count == 0) {
                //文字列をカンマで分割
                String expectResloveTypeValue = next.getExpectResloveTypeValue();
                String[] values = expectResloveTypeValue.split(",");
                resultMediation.setExpectResloveTypeValue(new ArrayList<String>());
                for (String value : values) {
                    resultMediation.getExpectResloveTypeValue().add(value);
                }
                //設定エラーメッセージ
                resultMediation.setStatus(next.getStatus());
                if (resultMediation.getStatus() == 2 || resultMediation.getStatus() == 3 || resultMediation.getStatus() == 4) {
                    resultMediation.setMeg(Constants.AGREEMENT_MESSAGE);
                }else if(resultMediation.getStatus() == 5 || resultMediation.getStatus() == 6){
                    resultMediation.setMeg(Constants.REFUSED_MESSAGE);
                }else if(resultMediation.getStatus() == 7 || resultMediation.getStatus() == 8){
                    resultMediation.setMeg(Constants.CONFIRMED_MESSAGE);
                }else if(resultMediation.getStatus() == 9){
                    resultMediation.setMeg(Constants.FINSHED_MESSAGE);
                }else{
                    resultMediation.setMeg(null);
                }
                resultMediation.setOtherContext(next.getOtherContext());
                resultMediation.setPayAmount(next.getPayAmount());
                resultMediation.setCounterClaimPayment(next.getCounterClaimPayment());
                //時間フォーマットの変換
                resultMediation.setPaymentEndDate(stringtoDate(next.getPaymentEndDate()));
                resultMediation.setShipmentPayType(next.getShipmentPayType());
                //時間フォーマットの変換
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

    private Date stringtoDate(String dateString){
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
}
