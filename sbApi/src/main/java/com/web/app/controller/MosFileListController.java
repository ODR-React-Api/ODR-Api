package com.web.app.controller;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web.app.domain.Response;
import com.web.app.domain.MosFileList.BatchDownloadOfCaseFilesParameter;
import com.web.app.service.MosFileListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * S07_申立て詳細画面・ファイル
 * Controller層
 * MosFileListController
 * 
 * @author DUC 張明慧
 * @since 2024/05/14
 * @version 1.0
 */
@CrossOrigin(origins = "*")
// ラベルを「申立てファイル一覧画面」と指定する
@Api(tags = "申立てファイル一覧画面")
@RestController
@RequestMapping("/MosFileList")
public class MosFileListController {
    @Autowired
    private MosFileListService mosFileListService;

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
