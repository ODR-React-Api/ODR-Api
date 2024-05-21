package com.web.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.AnswerLogin.PetitionDataUser;
import com.web.app.domain.AnswerLogin.PetitionsData;
import com.web.app.domain.Entity.MasterPlatforms;
import com.web.app.mapper.GetPetitionsDataMapper;
import com.web.app.service.AnswerLoginService;
import com.web.app.service.UtilService;

/**
 * 回答登録画面
 * 
 * @author DUC 王大安
 * @since 2024/4/25
 * @version 1.0
 */
@Service
public class AnswerLoginServiceImpl implements AnswerLoginService{
    @Autowired
    private GetPetitionsDataMapper getPetitionsDataMapper;

    @Autowired
    private UtilService UtilService;

    /**
     * 申立データ取得API
     *
     * @param caseId セッション情報の案件ID
     * @param plateFormId セッション情報のプラットフォームID
     * @return 申立データ取得結果
     */
    @Override
    public List<PetitionsData> getPetitionData(String caseId, String plateFormId) {
        return getPetitionsDataMapper.getPetitionsData(caseId, plateFormId);
    }

    /**
     * 申立データ取得API
     *
     * @param plateFormId セッション情報のプラットフォームID
     * @return プラットフォ情報
     */
    @Override
    public PetitionDataUser getPetitionDataUser(String plateFormId) {
        // すべてのプラットフォーム情報を取得
        MasterPlatforms masterPlatforms = UtilService.GetMasterPlatforms(plateFormId);
        // データ抽出
        PetitionDataUser petitionDataUser = new PetitionDataUser();
        petitionDataUser.setCounterclaimLimitDays(masterPlatforms.getCounterclaimLimitDays());
        petitionDataUser.setMediationLimitDays(masterPlatforms.getMediationLimitDays());
        petitionDataUser.setNegotiationLimitDays(masterPlatforms.getNegotiationLimitDays());
        petitionDataUser.setPhaseNegotiation(masterPlatforms.getPhase_negotiation());
        petitionDataUser.setPhaseReply(masterPlatforms.getPhase_reply());
        petitionDataUser.setUseOther(masterPlatforms.getUseOther());
        petitionDataUser.setUseProductUrl(masterPlatforms.getUseProductUrl());
        petitionDataUser.setUseTraderName(masterPlatforms.getUseTraderName());
        petitionDataUser.setUserProductId(masterPlatforms.getUserProductId());
        return petitionDataUser;
    }

}
