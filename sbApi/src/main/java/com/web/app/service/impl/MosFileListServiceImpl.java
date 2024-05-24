package com.web.app.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.Response;
import com.web.app.domain.MosFileList.BatchDownloadOfCaseFilesParameter;
import com.web.app.domain.MosFileList.CaseFileInfo;
import com.web.app.domain.MosFileList.GetFileInfo;
import com.web.app.mapper.GetFileInfoMapper;
import com.web.app.mapper.GetLoginUserRoleOpenInfoMapper;
import com.web.app.service.MosFileListService;
import com.web.app.tool.AjaxResult;

/**
 * S07_申立て詳細画面・ファイル
 * Service層実現類
 * MosFileListServiceImpl
 * 
 * @author DUC 閆文静 張明慧
 * @since 2024/04/25
 * @version 1.0
 */
@Service
public class MosFileListServiceImpl implements MosFileListService {

    // ログインユーザのロールと開示情報取得
    @Autowired
    private GetLoginUserRoleOpenInfoMapper getLoginUserRoleOpenInfoMapper;

    // 案件添付ファイル取得
    @Autowired
    private GetFileInfoMapper getFileInfoMapper;


    /**
     * ログインユーザのロールと開示情報取得
     *
     * @param caseId セッション情報のcaseid
     * @param id     ログインユーザId
     * @param email  ログインユーザemai
     * @return 取得したログインユーザのロールと開示情報
     * @throws Exception ログインユーザのロールと開示情報取得失败
     */
    @Override
    public GetFileInfo getLoginUserRoleOpenInfo(String caseId, String id, String email) {
        GetFileInfo getFileInfo = new GetFileInfo();
        // ログインユーザのロールと開示情報取得API
        getFileInfo = getLoginUserRoleOpenInfoMapper.selectLoginUserRoleOpenInfo(caseId);

        // ログインユーザIdがPetitionUserIdと一致すれば、申立人とする
        if (getFileInfo.getPetitionUserId().equals(id)) {
            getFileInfo.setPositionFlg(1);

            // ログインユーザemailがTraderUserEmailと一致すれば、相手方とする
        } else if (getFileInfo.getTraderUserEmail().equals(email)) {
            getFileInfo.setPositionFlg(2);

            // ログインユーザemailがMediatorUserEmailと一致すれば、調停人とする
        } else if (getFileInfo.getMediatorUserEmail().equals(email)) {
            getFileInfo.setPositionFlg(3);
        }
        return getFileInfo;
    }

    /**
     * 案件添付ファイル取得
     *
     * @param caseId                 セッション情報のcaseid
     * @param id                     ログインユーザId
     * @param positionFlg            立場フラグ
     * @param mediatorDisclosureFlag 調停人情報開示フラグ
     * @return 取得した案件添付ファイル
     * @throws Exception エラー画面(404)へ遷移
     */
    @Override
    public List<CaseFileInfo> getCaseFileInfo(String caseId, String id, Integer positionFlg,
            Integer mediatorDisclosureFlag) {
        List<CaseFileInfo> caseFileInfoList = new ArrayList<>();

        // 案件添付ファイル取得
        caseFileInfoList = getFileInfoMapper.selectCaseFileInfoList(caseId, id, positionFlg,
                mediatorDisclosureFlag);

        return caseFileInfoList;
    }

    /**
     * API_案件添付ファイル一括ダウンロード
     * 画面上選択された複数ファイルの「FileUrl」リストを引き渡し、全対象ファイルを１つのZIPアイルに圧縮し、クライアントへZIPファイルストリームを返す
     * 
     * @param batchDownloadOfCaseFilesParameter API_案件添付ファイル一括ダウンロードの引数
     * @param response                          クライアント応答情報
     * @return Response 案件添付ファイル一括ダウンロードの状況
     */
    @Override
    @SuppressWarnings("rawtypes")
    public Response BatchDownloadOfCaseFiles(BatchDownloadOfCaseFilesParameter batchDownloadOfCaseFilesParameter,
            HttpServletResponse response) {
        try {
            // 画面上選択された複数ファイルの「FileUrl」リスト /path/to/xxx.txt
            String[] srcFiles = batchDownloadOfCaseFilesParameter.getFileUrl();
            // ZIPファイル
            String destZipFile = "output.zip";
            // CharacterEncodingの設定
            response.setCharacterEncoding("UTF-8");
            // Headerの設定
            response.setHeader("Content-disposition", "attachment;filename=" + destZipFile);
            // ファイル圧縮ダウンロード
            zipFiles(srcFiles, response);
            return AjaxResult.success("一括ダウンロードに成功しました!");
        } catch (IOException e) {
            return AjaxResult.fatal("一括ダウンロードに失敗しました!", e);
        }
    }

    /**
     * ファイル圧縮ダウンロード
     * 
     * @param srcFiles 画面上選択された複数ファイルの「FileUrl」リスト
     * @param response クライアント応答情報
     * @throws IOException IO異常
     */
    public static void zipFiles(String[] srcFiles, HttpServletResponse response) throws IOException {
        try (
                // OutputStreamオブジェクトの取得
                BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
                ZipOutputStream zipOut = new ZipOutputStream(bos)) {
            // 各ファイルをzipファイルにループして追加する
            for (String srcFilename : srcFiles) {
                File fileToZip = new File(srcFilename);
                // 元のファイル名を保持するディレクトリ構造
                String destFileName = fileToZip.getName();
                if (fileToZip.isFile()) {
                    try (
                            // ダウンロードファイルの入力ストリームを取得する
                            FileInputStream fis = new FileInputStream(fileToZip);
                            BufferedInputStream bis = new BufferedInputStream(fis)) {
                        // ZipEntryの作成
                        ZipEntry zipEntry = new ZipEntry(destFileName);
                        // ZIPファイルにZipEntryを追加する
                        zipOut.putNextEntry(zipEntry);
                        // 入力ストリームからデータを読み出してZIPファイルに書き込む
                        byte[] data = new byte[1024];
                        int length;
                        while ((length = bis.read(data)) > 0) {
                            zipOut.write(data, 0, length);
                        }
                        // 現在のZipEntryを閉じる
                        zipOut.closeEntry();
                        // 入力ストリームを閉じる
                        bis.close();
                        fis.close();
                    }
                }
            }
            // 出力ストリームを閉じる
            zipOut.close();
            bos.close();
        }
    }
}
