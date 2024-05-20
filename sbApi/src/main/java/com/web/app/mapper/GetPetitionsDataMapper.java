package com.web.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.AnswerLogin.PetitionsData;

/**
 * 申立データ取得
 * 
 * @author DUC 王大安
 * @since 2024/4/25
 * @version 1.0
 */
@Mapper
public interface GetPetitionsDataMapper {

    /**
     * 申立データ取得API
     *
     * @param caseId セッション情報の案件ID
     * @param plateFormId セッション情報のプラットフォームID
     * @return 申立データ取得結果
     */
    List<PetitionsData> getPetitionsData(String caseId, String plateFormId);
    
}
