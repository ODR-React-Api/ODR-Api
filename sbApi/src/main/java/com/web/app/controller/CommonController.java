package com.web.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.app.domain.Response;
import com.web.app.domain.User;
import com.web.app.domain.Entity.ActionHistories;
import com.web.app.domain.constants.Constants;
import com.web.app.service.CommonService;
import com.web.app.service.UtilService;
import com.web.app.tool.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Api(tags = "Common") // 声明当前controller需要生成文档，并且指定在文档中的标签为“用户模块”
@RestController
@RequestMapping("/Common")
public class CommonController {

    @Autowired
    private CommonService commonService;

    @Autowired
    private UtilService utilService;

    /**
     * 案件別個人情報リレーションデータ取得(申立人/相手方)
     * 
     * @param identity   = true 申立人;identity = false 相手方
     * @param languageId 言語ID
     * @param platformId プラットフォームのId
     * @param caseId     案件ID
     * @return user
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes" })
    @ApiOperation("案件別個人情報リレーションデータ取得(申立人/相手方)")
    @GetMapping("/GetUserDataFromCaseIdentity")
    public Response GetUserDataFromCaseIdentity(Boolean identity, String languageId, String platformId, String caseId)
            throws Exception {
        try {
            User user = commonService.GetUserDataFromCaseIdentity(identity, languageId, platformId, caseId);

            return AjaxResult.success(Constants.MSG_SUCCESS, user);
        } catch (Exception e) {
            AjaxResult.fatal(Constants.MSG_ERROR, e);
            throw e;
        }
    }

    /**
     * アクション履歴新規登録
     * 
     * @param actionHistories アクション履歴
     * @param fileId          ファイルId
     * @param parametersFlag  Parametersのログインユーザ名があるフラグ
     * @param displayNameFlag 関係者内容取得するフラグ
     * @return true false
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes" })
    @ApiOperation("アクション履歴新規登録")
    @PostMapping("/InsHistories")
    public Response InsertActionHistories(ActionHistories actionHistories, @RequestBody List<String> fileId,
            Boolean parametersFlag,
            Boolean displayNameFlag) throws Exception {
        try {
            Boolean res = commonService.InsertActionHistories(actionHistories, fileId, parametersFlag, displayNameFlag);
            return AjaxResult.success(Constants.MSG_SUCCESS, res);
        } catch (Exception e) {
            AjaxResult.fatal(Constants.MSG_ERROR, e);
            throw e;
        }
    }

    
    /**
     * API_案件データ取得
     *
     * @param CaseId セッション情報のCaseId 
     * @param PlatformId セッション情報のプラットフォームID
     * @return getCasesByCidList
     * @throws Exception エラーの説明内容
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("案件データ取得")
    @GetMapping("/findCasesByCid")
    public Response findCasesByCid(String caseId, String platformId) {
        try {
            String caseTitle = utilService.findCasesByCid(caseId, platformId);
            return AjaxResult.success("案件データ取得に成功しました", caseTitle);
        } catch (Exception e) {
            AjaxResult.fatal("案件データ取得に失敗しました!", e);
            return null;
        }
    }
}