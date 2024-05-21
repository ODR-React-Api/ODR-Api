package com.web.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.AnswerLogin.PetitionDataUser;
import com.web.app.domain.AnswerLogin.PetitionsData;
import com.web.app.service.AnswerLoginService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 回答登録画面
 * 
 * @author DUC 王大安
 * @since 2024/4/25
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "回答登録画面")
@RestController
@RequestMapping("/getData")
public class AnswerLoginController {

    @Autowired
    AnswerLoginService getPetitionsDataService;

    /**
     * 申立データ取得API
     *
     * @param caseId セッション情報の案件ID
     * @param plateFormId セッション情報のプラットフォームID
     * @return 申立データ取得結果
     */
    @ApiOperation("申立データ取得API")
    @PostMapping("/getPetitionsData")
    public List<PetitionsData> getPetitionsData(String caseId, String plateFormId){
        List<PetitionsData> list;
        list = getPetitionsDataService.getPetitionData(caseId, plateFormId);
        return list;
    }

    /**
     * 申立データ取得API
     *
     * @param plateFormId セッション情報のプラットフォームID
     * @return プラットフォ情報
     */
    @ApiOperation("案件別個人情報リレーションデータ取得(申立人)")
    @PostMapping("/getPetitionDataUser")
    public PetitionDataUser GetPetitionDataUser(String plateFormId) {
        return getPetitionsDataService.getPetitionDataUser(plateFormId);
    }
    
}
