package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.CouAnswerLogin.UpdClaimRepliesDataParameter;
import com.web.app.domain.Entity.CaseFileRelations;
import com.web.app.domain.Entity.Files;
import com.web.app.domain.constants.Constants;
import com.web.app.mapper.UpdClaimRepliesDataMapper;
import com.web.app.service.CouAnswerLoginService;
import com.web.app.service.UtilService;

/**
 * S14_反訴回答登録画面
 * Service层实现类
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
     * 下書きデータ存在する場合、下記のAPIをコールし、画面入力したデータをDBへ更新を行う
     * ・「反訴への回答」は、レコード更新（update）で行う
     * ・「案件-添付ファイルリレーション」は、添付ファイルの添削によりファイルの増減が発生しますので、レコード物理削除(delete)＋新規登録(insert)で行う
     * ・「添付ファイル」は、添付ファイルの添削によりファイルの増減が発生しますので、レコード物理削除(delete)＋新規登録(insert)で行う
     * 
     * @param updClaimRepliesDataParameter API_反訴への回答データ更新の引数
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

        // 画面上の申立番号を利用してテーブル【反訴への回答】で検索し、検索できれば更新し、検索できなければ挿入する。
        // 「反訴への回答」取得
        int caseClaimrepliesCount = updClaimRepliesDataMapper.getCaseClaimrepliesCount(caseId, platformId);
        // 下書きデータ存在の判定
        if (caseClaimrepliesCount > 0) {
            // 下書きデータ存在する場合、下記のAPIをコールし、画面入力したデータをDBへ更新を行う
            // 「反訴への回答.id」取得
            String caseClaimrepliesId = updClaimRepliesDataMapper.getCaseClaimrepliesId(caseId, platformId);

            // 「反訴への回答」更新
            int updCaseClaimrepliesResult = updClaimRepliesDataMapper.updCaseClaimreplies(updClaimRepliesDataParameter);
            if (updCaseClaimrepliesResult != 1) {
                return 0;
            }

            // 「添付ファイル」取得
            int filesCount = updClaimRepliesDataMapper.getFilesCount(delFileId, platformId);
            // 画面の下書きの添付ファイル存在の判定
            if (filesCount > 0) {
                // 「添付ファイル」論理削除
                int updFilesResult = updClaimRepliesDataMapper.updFiles(updClaimRepliesDataParameter);
                if (updFilesResult != 1) {
                    return 0;
                }
            }
            // 「添付ファイル」の新規登録の項目を設定
            Files files = GetFiles(updClaimRepliesDataParameter);
            // 「添付ファイル」新規登録
            int insFilesResult = updClaimRepliesDataMapper.insFiles(files);
            if (insFilesResult != 1) {
                return 0;
            }

            // 「案件-添付ファイルリレーション」取得
            int caseFileRelationsCount = updClaimRepliesDataMapper.getCaseFileRelationsCount(delFileId, platformId);
            // 画面の下書きの案件-添付ファイルリレーション存在の判定
            if (caseFileRelationsCount > 0) {
                // 「案件-添付ファイルリレーション」論理削除
                int updCaseFileRelationsResult = updClaimRepliesDataMapper
                        .updCaseFileRelations(updClaimRepliesDataParameter);
                if (updCaseFileRelationsResult != 1) {
                    return 0;
                }
            }
            // 「案件-添付ファイルリレーション」新規登録の項目を設定
            CaseFileRelations caseFileRelations = GetCaseFileRelations(updClaimRepliesDataParameter);
            // 案件種類ID 反訴への回答.id
            caseFileRelations.setRelatedId(caseClaimrepliesId);
            // ファイルID 添付ファイル.id
            caseFileRelations.setFileId(files.getId());
            // 「案件-添付ファイルリレーション」新規登録
            int insCaseFileRelations = updClaimRepliesDataMapper.insCaseFileRelations(caseFileRelations);
            if (insCaseFileRelations != 1) {
                return 0;
            }

            return 1;
        } else {
            // 下書きデータ存在しない場合、下記のAPIをコールし、画面入力したデータをDBへ新規登録を行う
            // 反訴への回答データ新規登録API
            return 0;
        }
    }

    /**
     * 「添付ファイル」の新規登録の項目を設定
     * 
     * @param updClaimRepliesDataParameter API_反訴への回答データ更新の引数
     * @return files 「添付ファイル」の新規登録の項目
     */
    public Files GetFiles(UpdClaimRepliesDataParameter updClaimRepliesDataParameter) {
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
     * @param updClaimRepliesDataParameter API_反訴への回答データ更新の引数
     * @return caseFileRelations 「案件-添付ファイルリレーション」新規登録の項目
     */
    public CaseFileRelations GetCaseFileRelations(UpdClaimRepliesDataParameter updClaimRepliesDataParameter) {
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
