package com.web.app.service;

import java.util.List;

import com.web.app.domain.AnswerLogin.PetitionDataUser;
import com.web.app.domain.AnswerLogin.PetitionsData;

/**
 * 回答登録画面
 * 
 * @author DUC 王大安
 * @since 2024/4/25
 * @version 1.0
 */
public interface AnswerLoginService {
    /**
     * 申立データ取得API
     *
     * @param caseId 画面の案件ID
     * @param plateFormId 画面のプラットフォームID
     * @return 申立データ取得結果
     */
    List<PetitionsData> getPetitionData(String caseId, String plateFormId);

    /**
     * API_プラットフォ情報取得
     *
     * @param plateFormId 画面のプラットフォームID
     * @return プラットフォ情報
     */
    PetitionDataUser getPetitionDataUser(String plateFormId);
}
