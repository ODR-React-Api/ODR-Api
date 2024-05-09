package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.PetitionsContent;
import com.web.app.domain.Response;
import com.web.app.service.PetitionsContentService;

import io.swagger.annotations.Api;

/**
 * 申立ての内容取得
 * 
 * @author DUC 王亞テイ
 * @since 2024/04/23
 * @version 1.0
 */

@CrossOrigin(origins = "*")
@Api(tags = "申立ての内容取得")
@RestController

public class PetitionsContentController {

    /**
     * 申立ての内容取得
     *
     * @param caseId フロントエンド転送
     * @return 申立ての内容の取得必要なすべてのデータ
     * @throws Exception データベース例外
     */

    @Autowired
    private PetitionsContentService petitionsContentService;

    @SuppressWarnings("rawtypes")
    @GetMapping("/getPetitioncontent")
    public Response getPetitioncontent(String caseId) {

        // 申立ての内容取得
        PetitionsContent petitionsContent = petitionsContentService.selectPetitionData(caseId);

        if (petitionsContent != null) {
            return Response.success(petitionsContent);
        }
        return Response.error("失敗");
    }

}
