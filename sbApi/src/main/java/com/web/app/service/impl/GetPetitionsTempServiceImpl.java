package com.web.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.FileId;
import com.web.app.domain.GetPetitionTemp;
import com.web.app.domain.PetitionInfo;
import com.web.app.domain.PetitionTemp;
import com.web.app.domain.ScaleItems;
import com.web.app.domain.SessionInfo;
import com.web.app.mapper.GetPetitionsTempMapper;
import com.web.app.service.GetPetitionsTempService;

@Service
public class GetPetitionsTempServiceImpl implements GetPetitionsTempService {


    @Autowired
    private GetPetitionsTempMapper getPetitionsTempMapper;

    GetPetitionTemp petitionInfoList1 = new GetPetitionTemp() ;
    PetitionTemp petitionsTemp = new PetitionTemp();
    PetitionInfo getPetitionInfo = new PetitionInfo();

    @Override
    public GetPetitionTemp petitionsTempSearch(SessionInfo sessionInfo){

        //TBL「案件別個人情報リレーション（case_relations）」とTBL「申立（case_petitions）」より関連ユーザの下書き保存のデータを取得する。
        petitionsTemp = getPetitionsTempMapper.selectPetitionsTemp(sessionInfo.getSessionId());
        if (petitionsTemp != null) {
            petitionInfoList1.setPetitionUserId(petitionsTemp.getPetitionUserId());
            petitionInfoList1.setCasePetition(petitionsTemp.getCasePetition());
            petitionInfoList1.setPetitionUserInfo_Email(petitionsTemp.getPetitionUserInfo_Email());
            petitionInfoList1.setAgent1_Email(petitionsTemp.getAgent1_Email());
            petitionInfoList1.setAgent2_Email(petitionsTemp.getAgent2_Email());
            petitionInfoList1.setAgent3_Email(petitionsTemp.getAgent3_Email());
            petitionInfoList1.setAgent4_Email(petitionsTemp.getAgent4_Email());
            petitionInfoList1.setAgent5_Email(petitionsTemp.getAgent5_Email());
            petitionInfoList1.setTraderUserEmail(petitionsTemp.getTraderUserEmail());
            petitionInfoList1.setProductName(petitionsTemp.getProductName());
            petitionInfoList1.setProductId(petitionsTemp.getProductId());
            petitionInfoList1.setTraderName(petitionsTemp.getTraderName());
            petitionInfoList1.setTraderUrl(petitionsTemp.getTraderUrl());
            petitionInfoList1.setBoughtDate(petitionsTemp.getBoughtDate());
            petitionInfoList1.setPrice(petitionsTemp.getPrice());
            petitionInfoList1.setPetitionTypeValue(petitionsTemp.getPetitionTypeValue());
            petitionInfoList1.setPetitionContext(petitionsTemp.getPetitionContext());
            petitionInfoList1.setExpectResloveTypeValue(petitionsTemp.getExpectResloveTypeValue());
            petitionInfoList1.setOther(petitionsTemp.getOther());
        }
        //TBL「ユーザ（odr_users）」より申立人情報を取得する
        if (petitionsTemp.getPetitionUserInfo_Email() != null) {
            getPetitionInfo = getPetitionsTempMapper.selectOdrUsers(sessionInfo.getSessionId(), petitionsTemp.getPetitionUserInfo_Email());
            petitionInfoList1.setFirstName(getPetitionInfo.getFirstName());
            petitionInfoList1.setLastName(getPetitionInfo.getLastName());
            petitionInfoList1.setFirstName_kana(getPetitionInfo.getFirstName_kana());
            petitionInfoList1.setLastName_kana(getPetitionInfo.getLastName_kana());
            petitionInfoList1.setCompanyName(getPetitionInfo.getCompanyName());
        }
        
        if (petitionsTemp != null && getPetitionInfo != null) {
            //TBL「案件-添付ファイルリレーション（case_file_relations）」より関連下書き案件のファイルIDを取得する。
            List<FileId> getFileId = getPetitionsTempMapper.selectFileId(petitionsTemp.getCasePetition()); 
            if (getFileId.size() > 0) {
                petitionInfoList1.setFileName(getFileId);
            }
            //拡張項目内容取得
            List<ScaleItems> scaleItemsList = getPetitionsTempMapper.scaleItemsSearch(sessionInfo.getPlatformId()); 
            if (scaleItemsList.size() > 0) {
                petitionInfoList1.setPetitionTypeDisplayName(scaleItemsList);
            }                 
        }
        return petitionInfoList1;
    }

}
