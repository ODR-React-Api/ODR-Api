package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.Entity.CaseRelations;
import com.web.app.domain.MosDetail.PetitionsContent;
import com.web.app.domain.MosDetail.RelationsContent;
import com.web.app.service.MosDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 申立て詳細画面_概要
 * 
 * @author DUC 王亞テイ
 * @since 2024/04/23
 * @version 1.0
 */

@CrossOrigin(origins = "*")
@Api(tags = "申立て詳細画面_概要")
@RestController

public class MosDetailController {

    /**
     * 申立ての内容取得
     *
     * @param caseId フロントエンド転送
     * @return 申立ての内容の取得必要なすべてのデータ
     * @throws Exception データベース例外
     */

    @Autowired
    private MosDetailService mosDetailService;

    @ApiOperation("申立ての内容取得")
    @SuppressWarnings("rawtypes")
    @GetMapping("/getPetitionContent")
    public Response getPetitionContent(String caseId) {

        // 申立ての内容取得
        PetitionsContent petitionsContent = mosDetailService.selectPetitionData(caseId);

        if (petitionsContent != null) {
            return Response.success(petitionsContent);
        }
        return Response.error("失敗");
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation("関係者メアド取得")
    @GetMapping("/getRelations")
    public Response getRelations(String caseId) {

        CaseRelations caseRelations = mosDetailService.selectRelationsData(caseId);

        if (caseRelations != null) {
            return Response.success(caseRelations);
        }
        return Response.error("失败");
    }

    /**
     * 関係者内容取得
     *
     * @param caseId フロントエンド転送
     * @return 関係者内容取得の取得必要なすべてのデータ
     * @throws Exception データベース例外
     */

    @SuppressWarnings("rawtypes")
    @ApiOperation("関係者内容取得")
    @GetMapping("/getRelationsContent")
    public Response getRelationsContent(String caseId) {

        // 関係者内容取得
        RelationsContent relationsContent = mosDetailService.selectRelationsContentData(caseId);

        if (relationsContent != null) {
            return Response.success(relationsContent);
        }
        return Response.error("失败");
    }

    /**
     * 調停人退出メッセージ登録
     *
     * @param caseId         フロントエンド転送
     * @param uid            フロントエンド転送
     * @param platformId     セッション情報のPlatformId
     * @param messageGroupId フロントエンド転送
     * @return 調停人退出メッセージ登録の取得必要なすべてのデータ
     * @throws Exception データベース例外
     */

    @SuppressWarnings("rawtypes")
    @ApiOperation("調停人退出メッセージ登録")
    @GetMapping("/updMediatorHistories")
    public Response updMediatorHistories(String caseId, String uid, String platformId, String messageGroupId) {

        // 調停人退出メッセージ登録
        int num = mosDetailService.updateMediatorHistoriesData(caseId, uid, platformId, messageGroupId);

        if (num != 0) {
            return Response.success(num);
        }
        return Response.error("失败");
    }
}
