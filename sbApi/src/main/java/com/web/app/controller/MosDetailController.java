package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.MosDetail.ParticipatedStatusChangeResultInfo;
import com.web.app.domain.MosDetail.PetitionsContent;
import com.web.app.domain.MosDetail.RelationsContent;
import com.web.app.domain.MosDetail.WithdrawalReturn;
import com.web.app.domain.constants.Constants;
import com.web.app.service.MosDetailService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 申立て詳細画面_概要Controller
 * 
 * @author DUC 朱暁芳 張万超 王亞テイ
 * @since 2024/04/23
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "申立て詳細画面_概要")
@RestController
@RequestMapping("/MosDetail")
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

            return AjaxResult.success("Success", num);

        } catch (Exception e) {
            return AjaxResult.fatal("Error", e);
        }

    }

    /**
     * ケースの状態を取り下げに変更する。
     *
     * @param caseId 渡し項目.CaseId
     * @param uid    渡し項目.uid
     * @return 変更結果
     */
    @GetMapping("/applyWithdraw")
    @ApiOperation("取り下げ済状態変更")
    @SuppressWarnings("rawtypes")
    public Response applyWithdraw(@RequestParam("caseId") String caseId, @RequestParam("uid") String uid) {
        try {
            WithdrawalReturn res = mosDetailService.applyWithdraw(caseId, uid);
            return AjaxResult.success(Constants.AJAXRESULT_SUCCESS, res);
        } catch (Exception e) {
            AjaxResult.fatal("error", e);
            return null;
        }
    }

    /**
     * 参加済状態変更
     * 
     * @param caseId 案件ID
     * @return 戻り値は「 参照表明更新済FLG」に返される
     * @throws Exception エラーの説明内容
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("参加済状態変更")
    @PostMapping("/updCasesStatus")
    public Response updCasesStatus(String caseId, String uId) {
        try {
            ParticipatedStatusChangeResultInfo participatedInfo = mosDetailService.participatedStatusSearch(caseId,
                    uId);
            if (participatedInfo != null) {
                return Response.success(participatedInfo);
            }
            return Response.error(Constants.RETCD_NG);
        } catch (Exception e) {
            AjaxResult.fatal("失敗しました。", e);
            return null;
        }
    }
}