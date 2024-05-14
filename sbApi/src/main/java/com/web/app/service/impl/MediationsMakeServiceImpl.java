package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.service.MediationsMakeService;
import com.web.app.domain.Entity.CaseMediations;
import com.web.app.domain.mediationsMake.InsMediationsData;
import com.web.app.mapper.InsMediationsDataMapper;;

@Service
public class MediationsMakeServiceImpl implements MediationsMakeService {
    @Autowired
    private InsMediationsDataMapper mediationcaseMapper;

    @Override
    public ArrayList<InsMediationsData> dataSearch(InsMediationsData mediationcase) {

        ArrayList<InsMediationsData> mList = mediationcaseMapper.dataSearch(mediationcase);

        return mList;
    }

    @Override
    public CaseMediations mediationDataCount(InsMediationsData mediationcase) {

        CaseMediations mediationCount = mediationcaseMapper.mediationCount(mediationcase);

        return mediationCount;
    }

    @Transactional
    @Override
    public int insMediationsData2(InsMediationsData insMediationsData) {

        CaseMediations caseMediations = new CaseMediations();
        // id付与
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString().replaceAll("-", "");
        caseMediations.setId(id);
        // 属性赋予
        caseMediations.setPlatformId(insMediationsData.getPlatformId());
        caseMediations.setCaseId(insMediationsData.getCaseId());
        caseMediations.setExpectResloveTypeValue(insMediationsData.getExpectResloveTypeValue());
        caseMediations.setPayAmount(insMediationsData.getPayAmount());
        caseMediations.setCounterClaimPayment(insMediationsData.getCounterClaimPayment());
        caseMediations.setPaymentEndDate(insMediationsData.getPaymentEndDate());
        caseMediations.setShipmentPayType(insMediationsData.getShipmentPayType());
        caseMediations.setSpecialItem(insMediationsData.getSpecialItem());
        caseMediations.setUserId(insMediationsData.getUserId());
        caseMediations.setLastModifiedDate(insMediationsData.getLastModifiedDate());
        caseMediations.setLastModifiedBy(insMediationsData.getLastModifiedBy());
        // 「調停案」は、レコード新規登録（insert）で行う
        int MediationcaseInsert = mediationcaseMapper.insMediationsData2(caseMediations);

        return MediationcaseInsert;
    }

}