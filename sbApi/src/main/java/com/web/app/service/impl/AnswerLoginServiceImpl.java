package com.web.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.AnswerLogin.PetitionsData;
import com.web.app.mapper.GetPetitionsDataMapper;
import com.web.app.service.AnswerLoginService;

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
    GetPetitionsDataMapper getPetitionsDataMapper;

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
    
}
