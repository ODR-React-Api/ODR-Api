package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.domain.Response;
import com.web.app.domain.MosDetail.PetitionsContent;
import com.web.app.domain.MosDetail.RelationsContent;
import com.web.app.domain.constants.MessageConstants;
import com.web.app.service.MosDetailService;
import com.web.app.tool.AjaxResult;

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
@RequestMapping("MosDetail")
@RestController
public class MosDetailController {

    @Autowired
    private MosDetailService mosDetailService;

    /**
     * 申立ての内容取得
     *
     * @param caseId フロントエンド転送
     * @return 申立ての内容の取得必要なすべてのデータ
     * @throws Exception データベース例外
     */
    @ApiOperation("申立ての内容取得")
    @SuppressWarnings("rawtypes")
    @GetMapping("/getPetitionsContent")
    public Response getPetitionsContent(String caseId) {
        try {
            // 申立ての内容取得
            PetitionsContent petitionsContent = mosDetailService.selectPetitionData(caseId);

            return AjaxResult.success("Success", petitionsContent);

        } catch (Exception e) {
            return AjaxResult.fatal("Error", e);
        }
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
        try {
            // 関係者内容取得
            RelationsContent relationsContent = mosDetailService.selectRelationsContentData(caseId);

            return AjaxResult.success("Success", relationsContent);

        } catch (Exception e) {
            return AjaxResult.fatal("Error", e);
        }
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
    @GetMapping("/addMessages")
    public Response AddMessages(String caseId, String uid, String platformId, String messageGroupId) {
        try {
            // 調停人退出メッセージ登録
            int num = mosDetailService.AddMessages(caseId, uid, platformId, messageGroupId);

            if (num != 0) {

                return AjaxResult.success(MessageConstants.S05012I);
            }

        } catch (Exception e) {
            return AjaxResult.fatal(MessageConstants.S04023E, e);
        }

        // 追加本数が0の場合
        return AjaxResult.error(MessageConstants.S04023E);
    }
}
