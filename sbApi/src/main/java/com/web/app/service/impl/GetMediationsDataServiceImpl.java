package com.web.app.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.Mediation;
import com.web.app.domain.ResultMediation;
import com.web.app.mapper.GetMediationsDataMapper;
import com.web.app.service.GetMediationsDataService;

/**
 * サービス実装クラス
 * 
 * @author DUC 徐義然
 * @since 2024/05/07
 * @version 1.0
 */
@Service
public class GetMediationsDataServiceImpl implements GetMediationsDataService {

    private static final String AGREEMENT_MESSAGE = "合意済みの調停案は、編集を行うことはできません。";

    private static final String REFUSED_MESSAGE = "拒否済みの調停案は、編集を行うことはできません。";

    private static final String CONFIRMED_MESSAGE = "確認済みの調停案は、編集を行うことはできません。";

    private static final String FINSHED_MESSAGE = "成立済みの調停案は、編集を行うことはできません。";
    
    //マッパーオブジェクト
    @Autowired
    private GetMediationsDataMapper getMediationsDataMapper;

    /**
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
     * 取得したList結果をフロントエンドが使用するエンティティークラスに変換する
     * 
     * @param mediation:检索结果
     * @param resultMediation:目标结果集
     * @return resultMediation
     */
    private ResultMediation setResult(List<Mediation> mediation,ResultMediation resultMediation){
        Iterator<Mediation> iterator = mediation.iterator();
        //カウンタカウンタ
        int count = 0;
        //集合を反復器で巡回し、resultMediationに順次渡す
        while (iterator.hasNext()) {
            Mediation next = iterator.next();
            resultMediation.getFiles().get(count).setFileName(next.getFileName());
            resultMediation.getFiles().get(count).setFileUrl(next.getFileUrl());
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
                    resultMediation.setMeg(AGREEMENT_MESSAGE);
                }else if(resultMediation.getStatus() == 5 || resultMediation.getStatus() == 6){
                    resultMediation.setMeg(REFUSED_MESSAGE);
                }else if(resultMediation.getStatus() == 7 || resultMediation.getStatus() == 8){
                    resultMediation.setMeg(CONFIRMED_MESSAGE);
                }else if(resultMediation.getStatus() == 9){
                    resultMediation.setMeg(FINSHED_MESSAGE);
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
        }
        return resultMediation;
    }
}
