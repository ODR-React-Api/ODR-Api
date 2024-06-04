package com.web.app.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.domain.Response;
import com.web.app.domain.MosFileList.BatchDownloadOfCaseFilesParameter;
import com.web.app.domain.MosFileList.CaseFileInfo;
import com.web.app.domain.MosFileList.GetFileInfo;
import com.web.app.service.MosFileListService;
import com.web.app.tool.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * S07_申立て詳細画面・ファイル
 * Controller層
 * MosFileListController
 * 
 * @author DUC 閆文静 張明慧
 * @since 2024/04/25
 * @version 1.0
 */
@Api(tags = "申立て詳細画面・ファイル")
@RestController
@RequestMapping("/MosFileList")
public class MosFileListController {

    @Autowired
    private MosFileListService mosFileListService;

    GetFileInfo getFileInfo = new GetFileInfo();

    /**
     * ログインユーザのロールと開示情報取得
     *
     * @param caseId セッション情報のcaseid
     * @param id     ログインユーザId
     * @param email  ログインユーザemai
     * @return 取得したログインユーザのロールと開示情報
     * @throws Exception ログインユーザのロールと開示情報取得失败!
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("ログインユーザのロールと開示情報取得")
    @GetMapping("/getLoginUserRoleOpenInfo")
    public Response selectLoginUserRoleOpenInfo(String caseId, String id, String email) {
        try {
            getFileInfo = mosFileListService.getLoginUserRoleOpenInfo(caseId, id, email);
            return AjaxResult.success("ログインユーザのロールと開示情報取得成功!", getFileInfo);
        } catch (Exception e) {
            AjaxResult.fatal("ログインユーザのロールと開示情報取得失败!", e);
            return null;
        }
    }

    /**
     * 案件添付ファイル取得
     *
     * @param caseId セッション情報のcaseid
     * @param id     ログインユーザId
     * @return 取得した案件添付ファイル
     * @throws Exception 案件添付ファイル取得失败!
     */
    @SuppressWarnings("rawtypes")
    @ApiOperation("案件添付ファイル取得")
    @PostMapping("/getFileInfo")
    public Response getFileInfo(String caseId, String id) {
        // 立場フラグ
        Integer positionFlg = getFileInfo.getPositionFlg();
        // 調停人情報開示フラグ
        Integer mediatorDisclosureFlag = getFileInfo.getMediatorDisclosureFlag();
        try {
            List<CaseFileInfo> caseFileInfoList = mosFileListService.getCaseFileInfo(caseId, id, positionFlg,
                    mediatorDisclosureFlag);
            return AjaxResult.success("案件添付ファイル取得成功!", caseFileInfoList);
        } catch (Exception e) {
            AjaxResult.fatal("案件添付ファイル取得失败!", e);
            return null;
        }
    }

    /**
     * API_案件添付ファイル一括ダウンロード
     * 画面上選択されたCheckboxのファイル（複数可能）をZIPファイルに圧縮し、ダウンロードする
     * 
     * @param batchDownloadOfCaseFilesParameter API_案件添付ファイル一括ダウンロードの引数
     * @param response                          クライアント応答情報
     * @return Response 案件添付ファイル一括ダウンロードの状況
     */
    @ApiOperation("案件添付ファイル一括ダウンロード")
    @PostMapping("/BatchDownloadOfCaseFiles")
    @SuppressWarnings("rawtypes")
    public Response batchDownloadOfCaseFiles(
            @RequestBody BatchDownloadOfCaseFilesParameter batchDownloadOfCaseFilesParameter,
            HttpServletResponse response) {
        return mosFileListService.BatchDownloadOfCaseFiles(batchDownloadOfCaseFilesParameter, response);
    }

}
