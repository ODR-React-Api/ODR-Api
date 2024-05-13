package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.CouAnswerLogin.UpdClaimRepliesDataParameter;
import com.web.app.domain.Entity.CaseFileRelations;
import com.web.app.domain.Entity.Files;

/**
 * S14_反訴回答登録画面
 * Mapper层
 * UpdClaimRepliesDataMapper
 * 
 * @author DUC 張明慧
 * @since 2024/05/02
 * @version 1.0
 */
@Mapper
public interface UpdClaimRepliesDataMapper {
    // API_反訴への回答データ更新
    // 「反訴への回答」取得
    int getCaseClaimrepliesCount(String caseId, String platformId);

    // 「反訴への回答.id」取得
    String getCaseClaimrepliesId(String caseId, String platformId);

    // 「反訴への回答」更新
    int updCaseClaimreplies(UpdClaimRepliesDataParameter updClaimRepliesDataParameter);

    // 「添付ファイル」取得
    int getFilesCount(String delFileId, String platformId);

    // 「添付ファイル」論理削除
    int updFiles(UpdClaimRepliesDataParameter updClaimRepliesDataParameter);

    // 「添付ファイル」新規登録
    int insFiles(Files files);

    // 「案件-添付ファイルリレーション」取得
    int getCaseFileRelationsCount(String delFileId, String platformId);

    // 「案件-添付ファイルリレーション」論理削除
    int updCaseFileRelations(UpdClaimRepliesDataParameter updClaimRepliesDataParameter);

    // 「案件-添付ファイルリレーション」新規登録
    int insCaseFileRelations(CaseFileRelations caseFileRelations);
}