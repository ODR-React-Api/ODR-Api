package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.domain.Response;
import com.web.app.domain.Entity.CaseRelations;
import com.web.app.domain.MosDetail.CaseClaimrepliesMosDetail;
import com.web.app.domain.MosDetail.CaseIdParameter;
import com.web.app.domain.MosDetail.CaseInfo;
import com.web.app.domain.MosDetail.CaseMediationsData;
import com.web.app.domain.MosDetail.CaseNegotiationsData;
import com.web.app.domain.MosDetail.CaseRepliesMosDetail;
import com.web.app.domain.MosDetail.GetCaseInfoParameter;
import com.web.app.domain.MosDetail.ParticipatedStatusChangeResultInfo;
import com.web.app.domain.MosDetail.PetitionsContent;
import com.web.app.domain.MosDetail.RelationsContent;
import com.web.app.domain.MosDetail.UpdShowTuritorParameter;
import com.web.app.domain.MosDetail.WithdrawalReturn;
import com.web.app.domain.constants.Constants;
import com.web.app.domain.constants.MessageConstants;
import com.web.app.service.MosDetailService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * S04_申立て概要画面
 * Controller層
 * MosDetailController
 * 
 * @author DUC 張明慧 楊バイバイ 耿浩哲 朱暁芳 張万超 王亞テイ
 * @since 2024/04/22
 * @version 1.0
 */
@CrossOrigin(origins = "*")
// ラベルを「申立て概要画面」と指定する
@Api(tags = "申立て概要画面")
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

            if (num != 0) {

                return AjaxResult.success(MessageConstants.S05012I);
            }

        } catch (Exception e) {
            return AjaxResult.fatal(MessageConstants.S04023E, e);
        }

        // 追加本数が0の場合
        return AjaxResult.error(MessageConstants.S04023E);
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

    /**
     * 関係者メアド取得ControllerAPI
     *
     * @param caseId 案件ID
     * @return 案件別個人情報リレーション
     * @throws Exception エラーの説明内容
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("関係者メアド取得")
    @PostMapping("/getCaseRelations")
    public Response getCaseRelations(String caseId) {
        try {
            CaseRelations caseRelations = mosDetailService.getCaseRelations(caseId);
            if (caseRelations != null) {
                return Response.success(caseRelations);
            }
            return Response.error(Constants.RETCD_NG);
        } catch (Exception e) {
            return Response.error(e.getMessage());
        }
    }

    /**
     * 回答の内容取得
     * API_回答の内容取得
     * 渡し項目.CaseIdを引数に、DBよりケースに該当する回答の内容を取得して、画面へ返す。
     * 
     * @param caseRepliesMosDetail 渡し項目.CaseId
     * @return Response API「回答内容取得」を呼び出すData
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("API_回答の内容取得")
    @PostMapping("/GetCaseRepliesMosDetail")
    public Response getCaseRepliesMosDetail(@RequestBody CaseRepliesMosDetail caseRepliesMosDetail) {
        try {
            // 回答の内容取得
            CaseRepliesMosDetail returnCaseRepliesMosDetail = mosDetailService
                    .getCaseRepliesMosDetail(caseRepliesMosDetail.getCaseId());
            return AjaxResult.success("回答の内容取得成功!", returnCaseRepliesMosDetail);
        } catch (Exception e) {
            AjaxResult.fatal("回答の内容取得失敗!", e);
            return null;
        }
    }

    /**
     * 反訴への回答取得
     * API_反訴への回答取得
     * 渡し項目.CaseIdを引数に、DBよりケースに該当する反訴への回答を取得して、画面へ返す。
     * 
     * @param caseClaimrepliesMosDetail 渡し項目.CaseId
     * @return Response API「反訴への回答取得」を呼び出すData
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("API_反訴への回答取得")
    @PostMapping("/GetCaseClaimrepliesMosDetail")
    public Response getCaseClaimrepliesMosDetail(@RequestBody CaseClaimrepliesMosDetail caseClaimrepliesMosDetail) {
        try {
            // 反訴への回答取得
            CaseClaimrepliesMosDetail returnCaseClaimrepliesMosDetail = mosDetailService
                    .getCaseClaimrepliesMosDetail(caseClaimrepliesMosDetail.getCaseId());
            return AjaxResult.success("反訴への回答取得成功!", returnCaseClaimrepliesMosDetail);
        } catch (Exception e) {
            AjaxResult.fatal("反訴への回答取得失敗!", e);
            return null;
        }
    }

    /**
     * API_案件状態取得
     * 申立て一覧画面より渡されたCaseIdを引数として、DBから該当する案件のステータスを取得する。
     * 実行Flgが1の場合
     * 
     * @param getCaseInfoParameter API_案件状態取得の引数
     * @return Response API「案件状態取得」を呼び出すData
     */
    @ApiOperation("案件状態取得")
    @PostMapping("/GetCaseInfo")
    @SuppressWarnings("rawtypes")
    public Response getCaseInfo(@RequestBody GetCaseInfoParameter getCaseInfoParameter) {
        // 実行Flgが1の場合は以下1.～6.の処理を行う
        if (getCaseInfoParameter.getExecuteFlg() == 1) {
            try {
                // 案件状態取得
                CaseInfo caseInfo = mosDetailService.GetCaseInfo(getCaseInfoParameter.getCaseId(),
                        getCaseInfoParameter.getPlatformId(), getCaseInfoParameter.getUserId());
                return AjaxResult.success("案件状態取得成功!", caseInfo);
            } catch (Exception e) {
                AjaxResult.fatal("案件状態取得失敗!", e);
                return null;
            }
        } else {
            return AjaxResult.error("実行Flgが不正!");
        }
    }

    /**
     * API_チュートリアル表示制御変更
     * 実行Flgが2の場合
     * 
     * @param updShowTuritorParameter API_チュートリアル表示制御変更の引数
     * @return Response チュートリアル表示制御変更の状況
     */
    @ApiOperation("チュートリアル表示制御変更")
    @PostMapping("/UpdShowTuritor")
    @SuppressWarnings("rawtypes")
    public Response updShowTuritor(@RequestBody UpdShowTuritorParameter updShowTuritorParameter) {
        // 実行Flgが2の場合は以下処理を行う
        if (updShowTuritorParameter.getExecuteFlg() == 2) {
            try {
                // チュートリアル表示制御変更
                int res = mosDetailService.UpdShowTuritor(updShowTuritorParameter);
                if (res > 0) {
                    return AjaxResult.success("更新成功!");
                }
                return AjaxResult.success("更新失敗!");
            } catch (Exception e) {
                AjaxResult.fatal("更新失敗!", e);
                return null;
            }
        } else {
            return AjaxResult.error("実行Flgが不正!");
        }
    }

    /**
     * API_和解内容取得
     * 渡し項目.CaseIdを引数に、DBよりケースに該当する和解の内容を取得して、画面へ返す。
     * 
     * @param caseIdParameter 渡し項目.CaseId
     * @return Response API「和解内容取得」を呼び出すData
     */
    @ApiOperation("和解内容取得")
    @PostMapping("/GetCaseNegotiationsData")
    @SuppressWarnings("rawtypes")
    public Response getCaseNegotiationsData(@RequestBody CaseIdParameter caseIdParameter) {
        try {
            // 和解内容取得
            CaseNegotiationsData caseNegotiationsData = mosDetailService
                    .GetCaseNegotiationsData(caseIdParameter.getCaseId());
            return AjaxResult.success("和解内容取得成功!", caseNegotiationsData);
        } catch (Exception e) {
            AjaxResult.fatal("和解内容取得失敗!", e);
            return null;
        }
    }

    /**
     * API_調停内容取得
     * 渡し項目.CaseIdを引数に、DBよりケースに該当する調停の内容を取得して、画面へ返す。
     * 
     * @param caseIdParameter 渡し項目.CaseId
     * @return Response API「調停内容取得」を呼び出すData
     */
    @ApiOperation("調停内容取得")
    @PostMapping("/GetCaseMediationsData")
    @SuppressWarnings("rawtypes")
    public Response getCaseMediationsData(@RequestBody CaseIdParameter caseIdParameter) {
        try {
            // 調停内容取得
            CaseMediationsData caseMediationsData = mosDetailService.GetCaseMediationsData(caseIdParameter.getCaseId());
            return AjaxResult.success("調停内容取得成功!", caseMediationsData);
        } catch (Exception e) {
            AjaxResult.fatal("調停内容取得失敗!", e);
            return null;
        }
    }

    /**
     * APIで調停人変更履歴の変更を行う。
     * 
     * @param caseId セッション.案件ID
     * @param userId セッション.ユーザID(ログインユーザーID)
     * @return Response API「調停人変更履歴の変更を行う」を呼び出すData
     */
    @ApiOperation("調停人変更履歴の変更を行う")
    @GetMapping("/UpdMediatorHistories")
    @SuppressWarnings("rawtypes")
    public Response updMediatorHistories(String caseId, String userId) {
        try {
            int count = mosDetailService.updMediatorHistories(caseId, userId);
            if (count == 0) {
                return AjaxResult.error("Error");
            }
            return AjaxResult.success("Success", count);
        } catch (Exception e) {
            return AjaxResult.fatal("Error", e);
        }
    }
}
