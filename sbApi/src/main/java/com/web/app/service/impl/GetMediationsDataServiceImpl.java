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

@Service
public class GetMediationsDataServiceImpl implements GetMediationsDataService {

    private static final String AGREEMENT_MESSAGE = "合意済みの調停案は、編集を行うことはできません。";

    private static final String REFUSED_MESSAGE = "拒否済みの調停案は、編集を行うことはできません。";

    private static final String CONFIRMED_MESSAGE = "確認済みの調停案は、編集を行うことはできません。";

    private static final String FINSHED_MESSAGE = "成立済みの調停案は、編集を行うことはできません。";

    @Autowired
    private GetMediationsDataMapper getMediationsDataMapper;

    @Override
    public ResultMediation getResultMediation(ResultMediation resultMediation){
        List<Mediation> mediation = getMediationsDataMapper.selectMediationsData(resultMediation.getCaseId(), resultMediation.getPlatformId());
        if (mediation != null && !mediation.isEmpty()) {
            resultMediation = setResult(mediation, resultMediation);
        }
        return resultMediation;
    }


    /**
     * 将检索到的List结果转化到前端要用的实体类中
     * 
     * mediation:检索结果
     * resultMediation:目标结果集
     */
    private ResultMediation setResult(List<Mediation> mediation,ResultMediation resultMediation){
        Iterator<Mediation> iterator = mediation.iterator();
        //计数器
        int count = 0;
        while (iterator.hasNext()) {
            Mediation next = iterator.next();
            resultMediation.getFiles().get(count).setFileName(next.getFileName());
            resultMediation.getFiles().get(count).setFileUrl(next.getFileUrl());
            if (count == 0) {
                String expectResloveTypeValue = next.getExpectResloveTypeValue();
                String[] values = expectResloveTypeValue.split(",");
                resultMediation.setExpectResloveTypeValue(new ArrayList<String>());
                for (String value : values) {
                    resultMediation.getExpectResloveTypeValue().add(value);
                }
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
                //转换时间
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
                //转换时间
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
