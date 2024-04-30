package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.ExpandItems;
import com.web.app.domain.GetPlatform;
import com.web.app.domain.PetitionType;
import com.web.app.domain.Status;
import com.web.app.mapper.GetPlatformMapper;
import com.web.app.service.GetPlatformService;

@Service
public class GetPlatformServiceImpl implements GetPlatformService {


    @Autowired
    private GetPlatformMapper getPlatformMapper;


    List<String> petitionTypeList1 = new ArrayList<>();
    List<String> resloveTypeList1 = new ArrayList<>();
    GetPlatform itemsResultList1 = new GetPlatform() ;

    @Override
    public GetPlatform odrUsersSearch(String sessionId){

        //ユーザ情報取得
        String getPlatformId = getPlatformMapper.selectOdrUsers(sessionId);
            //申立種類取得
            List<PetitionType> petitionTypeList = getPlatformMapper.masterTypesSearch1(getPlatformId);
            if (petitionTypeList.size() > 0) {
                for(int i=0; i<petitionTypeList.size(); i++){
                    petitionTypeList1.add(petitionTypeList.get(i).getDisplayName());
                }
                itemsResultList1.setPetitionTypeDisplayName(petitionTypeList1);            
            }

            //希望する解決方法取得
            List<PetitionType> resloveTypeList = getPlatformMapper.masterTypesSearch2(getPlatformId);
            if (resloveTypeList.size() > 0) {
                for(int i=0; i<resloveTypeList.size(); i++){
                    resloveTypeList1.add(resloveTypeList.get(i).getDisplayName());
                }  
                itemsResultList1.setResloveTypeDisplayName(resloveTypeList1);         
            }
            
            //画面制御表示項目表示状態取得
            Status result1 = getPlatformMapper.masterPlatformsSearch(getPlatformId);
            if (result1 != null) {
                itemsResultList1.setUserProductId(result1.getUserProductId());
                itemsResultList1.setUseTraderName(result1.getUseTraderName());
                itemsResultList1.setUseProductUrl(result1.getUseProductUrl());
                itemsResultList1.setUseOther(result1.getUseOther());
        
                //拡張項目の取得
                if (result1.getUseOther() == 1) {
                    List<ExpandItems> expandProjectList = getPlatformMapper.expandProjectSearch(getPlatformId);
                    if (expandProjectList.size() > 0) {
                        itemsResultList1.setExpandItems(expandProjectList);
                    }
                }            
            }    

        return itemsResultList1;
    }

}
