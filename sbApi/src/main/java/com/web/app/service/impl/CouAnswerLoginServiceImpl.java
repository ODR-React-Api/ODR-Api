package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.web.app.domain.CouAnswerLogin.UpdClaimRepliesDataParameter;
import com.web.app.domain.Entity.CaseClaimReplies;
import com.web.app.domain.Entity.CaseFileRelations;
import com.web.app.domain.Entity.Files;
import com.web.app.domain.constants.Constants;
import com.web.app.mapper.UpdClaimRepliesDataMapper;
import com.web.app.service.CouAnswerLoginService;
import com.web.app.service.UtilService;

/**
 * S14_反訴回答登録画面
 * Service層実装クラス
 * CouAnswerLoginServiceImpl
 * 
 * @author DUC 張明慧
 * @since 2024/05/02
 * @version 1.0
 */
@Service
public class CouAnswerLoginServiceImpl implements CouAnswerLoginService {
    @Autowired
    private UpdClaimRepliesDataMapper updClaimRepliesDataMapper;

    @Autowired
    private UtilService utilService;

    /**
     * API_反訴への回答データ新規登録/更新
     * 下書きデータ存在しない場合、下記のAPIをコールし、画面入力したデータをDBへ新規登録を行う
     * ・「反訴への回答」は、レコード新規登録（insert）で行う
     * ・「案件-添付ファイルリレーション」は、添付ファイルの数に等しいレコード新規登録(insert)で行う
     * ・「添付ファイル」は、添付ファイルの数に等しいレコード新規登録(insert)で行う
     * 下書きデータ存在する場合、下記のAPIをコールし、画面入力したデータをDBへ更新を行う
     * ・「反訴への回答」は、レコード更新（update）で行う
     * ・「案件-添付ファイルリレーション」は、添付ファイルの添削によりファイルの増減が発生しますので、レコード物理削除(delete)＋新規登録(insert)で行う
     * ・「添付ファイル」は、添付ファイルの添削によりファイルの増減が発生しますので、レコード物理削除(delete)＋新規登録(insert)で行う
     * 
     * @param updClaimRepliesDataParameter API_反訴への回答データ新規登録/更新の引数
     * @return int DBへ更新の状況
     */
    @Transactional
    @Override
    public int UpdClaimRepliesData(UpdClaimRepliesDataParameter updClaimRepliesDataParameter) {
        // セッションのプラットフォームID
        String platformId = updClaimRepliesDataParameter.getPlatformId();
        // セッション情報のCaseId
        String caseId = updClaimRepliesDataParameter.getCaseId();
        // セッション情報の削除対象ファイルid
        String delFileId = updClaimRepliesDataParameter.getDelFileId();
        // 添付資料
        // ファイル名
        String fileName = updClaimRepliesDataParameter.getFileName();
        // 拡張子
        String fileExtension = updClaimRepliesDataParameter.getFileExtension();

        // 画面上の申立番号を利用してテーブル「反訴への回答」で検索し、検索できれば更新し、検索できなければ挿入する。
        // 「反訴への回答」取得
        int caseClaimrepliesCount = updClaimRepliesDataMapper.getCaseClaimrepliesCount(caseId, platformId);
        // 下書きデータ存在の判定
        if (caseClaimrepliesCount > 0) {
            // 下書きデータ存在する場合、下記のAPIをコールし、画面入力したデータをDBへ更新を行う
            // 「反訴への回答」更新
            int updCaseClaimrepliesNum = updClaimRepliesDataMapper.updCaseClaimreplies(updClaimRepliesDataParameter);
            if (updCaseClaimrepliesNum == 0) {
                return 0;
            }

            // 削除対象ファイルidの判定
            if (delFileId != null) {
                // 「添付ファイル」論理削除
                int updFilesNum = updClaimRepliesDataMapper.updFiles(updClaimRepliesDataParameter);
                if (updFilesNum == 0) {
                    return 0;
                }

                // 「案件-添付ファイルリレーション」論理削除
                int updCaseFileRelationsNum = updClaimRepliesDataMapper
                        .updCaseFileRelations(updClaimRepliesDataParameter);
                if (updCaseFileRelationsNum == 0) {
                    return 0;
                }
            }

            // 添付資料ファイル名の判定
            if (fileName != null) {
                // 新規登録を実施Flg
                boolean insExeFlg = true;

                // 画面の下書きの添付ファイル未削除の場合
                if (delFileId == null) {
                    // 添付ファイル存在の判定
                    int filesCount = updClaimRepliesDataMapper.getFilesCount(caseId, platformId, fileName,
                            fileExtension);
                    if (filesCount > 0) {
                        // 画面の下書きの既存の添付ファイルは新規登録しない
                        insExeFlg = false;
                    }
                }

                // 新規登録を実施の判定
                if (insExeFlg) {
                    // 画面の下書きの添付ファイルは新規登録
                    // 「添付ファイル」の新規登録の項目を設定
                    Files files = getFiles(updClaimRepliesDataParameter);
                    // 「添付ファイル」新規登録
                    int insFilesNum = updClaimRepliesDataMapper.insFiles(files);
                    if (insFilesNum == 0) {
                        return 0;
                    }

                    // 「反訴への回答.id」取得
                    String caseClaimrepliesId = updClaimRepliesDataMapper.getCaseClaimrepliesId(caseId, platformId);

                    // 「案件-添付ファイルリレーション」新規登録の項目を設定
                    CaseFileRelations caseFileRelations = getCaseFileRelations(updClaimRepliesDataParameter);
                    // 案件種類ID 反訴への回答.id
                    caseFileRelations.setRelatedId(caseClaimrepliesId);
                    // ファイルID 添付ファイル.id
                    caseFileRelations.setFileId(files.getId());
                    // 「案件-添付ファイルリレーション」新規登録
                    int insCaseFileRelationsNum = updClaimRepliesDataMapper.insCaseFileRelations(caseFileRelations);
                    if (insCaseFileRelationsNum == 0) {
                        return 0;
                    }
                }
            }

            return 1;
        } else {
            // 下書きデータ存在しない場合、下記のAPIをコールし、画面入力したデータをDBへ新規登録を行う
            // 反訴への回答データ新規登録API
            // 「反訴への回答」新規登録の項目を設定
            CaseClaimReplies caseClaimReplies = getCaseClaimReplies(updClaimRepliesDataParameter);
            // 「反訴への回答」新規登録
            int insCaseClaimrepliesNum = updClaimRepliesDataMapper.insCaseClaimreplies(caseClaimReplies);
            if (insCaseClaimrepliesNum == 0) {
                return 0;
            }

            // 添付資料ファイル名の判定
            if (fileName != null) {
                // 画面の下書きの添付ファイルは新規登録
                // 「添付ファイル」の新規登録の項目を設定
                Files files = getFiles(updClaimRepliesDataParameter);
                // 「添付ファイル」新規登録
                int insFilesNum = updClaimRepliesDataMapper.insFiles(files);
                if (insFilesNum == 0) {
                    return 0;
                }

                // 「案件-添付ファイルリレーション」新規登録の項目を設定
                CaseFileRelations caseFileRelations = getCaseFileRelations(updClaimRepliesDataParameter);
                // 案件種類ID 反訴への回答.id
                caseFileRelations.setRelatedId(caseClaimReplies.getId());
                // ファイルID 添付ファイル.id
                caseFileRelations.setFileId(files.getId());
                // 「案件-添付ファイルリレーション」新規登録
                int insCaseFileRelationsNum = updClaimRepliesDataMapper.insCaseFileRelations(caseFileRelations);
                if (insCaseFileRelationsNum == 0) {
                    return 0;
                }
            }

            return 1;
        }
    }

    /**
     * 「反訴への回答」新規登録の項目を設定
     * 
     * @param updClaimRepliesDataParameter API_反訴への回答データ新規登録/更新の引数
     * @return caseClaimReplies 「反訴への回答」新規登録の項目
     */
    public CaseClaimReplies getCaseClaimReplies(UpdClaimRepliesDataParameter updClaimRepliesDataParameter) {
        CaseClaimReplies caseClaimReplies = new CaseClaimReplies();
        // Guid取得を利用 自動生成GIUD
        caseClaimReplies.setId(utilService.GetGuid());
        caseClaimReplies.setPlatformId(updClaimRepliesDataParameter.getPlatformId());
        caseClaimReplies.setCaseId(updClaimRepliesDataParameter.getCaseId());
        caseClaimReplies.setStatus(0);
        caseClaimReplies.setReplyContext(updClaimRepliesDataParameter.getReplyContext());
        caseClaimReplies.setDeleteFlag(Constants.DELETE_FLAG_0);
        caseClaimReplies.setLastModifiedBy(updClaimRepliesDataParameter.getLoginUser());
        return caseClaimReplies;
    }

    /**
     * 「添付ファイル」の新規登録の項目を設定
     * 
     * @param updClaimRepliesDataParameter API_反訴への回答データ新規登録/更新の引数
     * @return files 「添付ファイル」の新規登録の項目
     */
    public Files getFiles(UpdClaimRepliesDataParameter updClaimRepliesDataParameter) {
        Files files = new Files();
        // Guid取得を利用 自動生成GIUD
        files.setId(utilService.GetGuid());
        files.setPlatformId(updClaimRepliesDataParameter.getPlatformId());
        files.setCaseId(updClaimRepliesDataParameter.getCaseId());
        files.setFileName(updClaimRepliesDataParameter.getFileName());
        files.setFileExtension(updClaimRepliesDataParameter.getFileExtension());
        files.setFileBlobStorageId(null);
        // TODO
        // ＜内部ロジック生成ファイルURL＞
        files.setFileUrl(null);
        // ＜内部ロジック生成ファイルサイ＞
        files.setFileSize(null);
        files.setRegisterUserId(updClaimRepliesDataParameter.getLoginUser());
        files.setDeleteFlag(Constants.DELETE_FLAG_0);
        files.setLastModifiedBy(updClaimRepliesDataParameter.getLoginUser());
        return files;
    }

    /**
     * 「案件-添付ファイルリレーション」新規登録の項目を設定
     * 
     * @param updClaimRepliesDataParameter API_反訴への回答データ新規登録/更新の引数
     * @return caseFileRelations 「案件-添付ファイルリレーション」新規登録の項目
     */
    public CaseFileRelations getCaseFileRelations(UpdClaimRepliesDataParameter updClaimRepliesDataParameter) {
        CaseFileRelations caseFileRelations = new CaseFileRelations();
        // Guid取得を利用 自動生成GIUD
        caseFileRelations.setId(utilService.GetGuid());
        caseFileRelations.setPlatformId(updClaimRepliesDataParameter.getPlatformId());
        caseFileRelations.setCaseId(updClaimRepliesDataParameter.getCaseId());
        caseFileRelations.setRelationType(Constants.CASE_CLAIMREPLIES);
        caseFileRelations.setDeleteFlag(Constants.DELETE_FLAG_0);
        caseFileRelations.setLastModifiedBy(updClaimRepliesDataParameter.getLoginUser());
        return caseFileRelations;
    }
}
