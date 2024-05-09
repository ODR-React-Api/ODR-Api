package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.app.domain.CasePetitions;
import com.web.app.domain.PetitionsContent;
import com.web.app.mapper.PetitionsContentMapper;
import com.web.app.service.PetitionsContentService;

/**
 * 申立ての内容取得
 * 
 * @author DUC 王亞テイ
 * @since 2024/04/23
 * @version 1.0
 */

@Service
public class PetitionsContentServiceImpl implements PetitionsContentService {

    /**
     * 申立ての内容取得
     *
     * @param caseId フロントエンド転送
     * @return 申立ての内容の取得必要なすべてのデータ
     */

    @Autowired
    private PetitionsContentMapper petitionsContentMapper;

    @Override
    public PetitionsContent selectPetitionData(String caseId) {

        PetitionsContent petitionsContent = new PetitionsContent();

        // 申立ての内容取得
        CasePetitions casePetitions = petitionsContentMapper.PetitionListDataSearch(caseId);

        petitionsContent.setCasePetitions(casePetitions);

        // 添付資料取得
        petitionsContent.setAttachedFile(petitionsContentMapper.PetitionFileSearch(casePetitions.getCaseId()));

        // 拡張項目取得
        petitionsContent.setExtensionItem(petitionsContentMapper.PetitionExtensionitemSearch(caseId));

        return petitionsContent;
    }

}
