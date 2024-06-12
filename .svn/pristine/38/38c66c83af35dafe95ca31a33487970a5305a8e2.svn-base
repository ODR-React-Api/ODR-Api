package com.web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.domain.Response;
import com.web.app.domain.MosFileList.LoginUserRoleOpenInfo;
import com.web.app.domain.MosFileList.Files;
import com.web.app.service.MosFileListService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * S07_申立て詳細画面・ファイル
 * Controller層
 * MosFileListController
 * 
 * @author DUC 祭卉康
 * @since 2024/05/20
 * @version 1.0
 */
@CrossOrigin(origins = "*")
@Api(tags = "申立て詳細画面・ファイル")
@RestController
@RequestMapping("/MosFileList")
public class MosFileListController {

    @Autowired
    private MosFileListService mosFileListService;

    /**
     * ログインユーザのロールと開示情報取得
     * 
     * @param loginUserInfo 情報表示
     * @return 取得ログインユーザのロールと開示情報
     */
    @ApiOperation("ログインユーザのロールと開示情報取得")
    @PostMapping("/GetLoginUserRoleOpenInfo")
    @SuppressWarnings("rawtypes")
    public Response GetLoginUserRoleOpenInfo(String id, String caseId, String email) {
        try {
            //情報取得
            LoginUserRoleOpenInfo getloginUserRoleOpenInfo = mosFileListService.loginUserRoleOpenInfo(id,caseId,email);
            return AjaxResult.success("情報取得成功!", getloginUserRoleOpenInfo);
        } catch (Exception e) {
            AjaxResult.fatal("情報取得失敗!", e);
            return null;
        }

    }

    /**
     * 案件添付ファイル取得
     * 
     * @param id ログインユーザ
     * @param caseId セッション情報のcaseId
     * @return 取得案件添付ファイル
     */
    @ApiOperation("案件添付ファイル取得")
    @PostMapping("/GetFilesInfo")
    @SuppressWarnings("rawtypes")
    public Response GetFilesInfo(String id, String caseId) {
        try {
            //情報取得
            Files getfile = mosFileListService.files(id, caseId);
            if (getfile != null) {
                return AjaxResult.success("添付ファイルデータ取得でき!", getfile);
            } else {
                return AjaxResult.success("添付ファイルが空です", getfile);
            }
        } catch (Exception e) {
            AjaxResult.fatal("添付ファイルデータ取得できない!", e);
            return null;
        }

    }

}
