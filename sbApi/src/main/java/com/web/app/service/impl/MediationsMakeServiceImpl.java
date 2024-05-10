package com.web.app.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.constants.Constants;
import com.web.app.domain.mediationsMake.Mediation;
import com.web.app.domain.mediationsMake.ResultMediation;
import com.web.app.domain.mediationsMake.SubsidiaryFile;
import com.web.app.mapper.GetMediationsDataMapper;
import com.web.app.mapper.SaveMeditonMapper;
import com.web.app.service.MediationsMakeService;

/**
 * サービス実装クラス
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
     * API_ID:調停案データ取得
     * 
     * DBを検索して処理後のデータをコントローラに渡す
     * 
     * @param ResultMediation コントローラから渡された画面データ
     * @return DB検索result
     */
    @Override
    public ResultMediation getResultMediation(ResultMediation resultMediation){
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
                String paymentEndDate = next.getPaymentEndDate();
                Date endDate = null;
                try {
                    if (paymentEndDate != null) {
                        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        endDate = fmt.parse(paymentEndDate);
                    }
                } catch (Exception e) {
                    endDate = null;
                }
                resultMediation.setPaymentEndDate(endDate);
                resultMediation.setShipmentPayType(next.getShipmentPayType());
                //時間フォーマットの変換
                String agreement = next.getAgreementDate();
                Date agreementDate = null;
                try {
                    if (agreement != null) {
                        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        agreementDate = fmt.parse(agreement);
                    }
                } catch (Exception e) {
                    agreementDate = null;
                }
                resultMediation.setAgreementDate(agreementDate);
                resultMediation.setSpecialItem(next.getSpecialItem());
                resultMediation.setUserId(next.getUserId());
            }
            count++;

        }
        resultMediation.setFiles(files);
        return resultMediation;
    }
}
