package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.AnswerLogin.UpdRepliesDataParameter;
import com.web.app.domain.Entity.CaseFileRelations;
import com.web.app.domain.Entity.CaseReplies;
import com.web.app.domain.Entity.Files;

/**
 * S11_回答登録画面
 * Mapper層
 * UpdRepliesDataMapper
 * API_反訴・回答データ新規登録/更新
 * 
 * @author DUC 張明慧
 * @since 2024/05/14
 * @version 1.0
 */
@Mapper
public interface UpdRepliesDataMapper {
    // API_反訴・回答データ新規登録/更新
    // 「反訴・回答」取得
    int getCaseRepliesCount(String caseId, String platformId);

    // 「反訴・回答」新規登録
    int insCaseReplies(CaseReplies caseReplies);

    // 「反訴・回答.id」取得
    String getCaseRepliesId(String caseId, String platformId);

    // 「反訴・回答」更新
    int updCaseReplies(CaseReplies caseReplies);

    // // 「添付ファイル」取得
    // int getFilesCount(String delFileId, String platformId);

    // 「添付ファイル」論理削除
    int updFiles(UpdRepliesDataParameter updRepliesDataParameter);

    // 「添付ファイル」取得
    int getFilesCount(String caseId, String platformId, String fileName, String fileExtension);

    // 「添付ファイル」新規登録
    int insFiles(Files files);

    // 「案件-添付ファイルリレーション」論理削除
    int updCaseFileRelations(UpdRepliesDataParameter updRepliesDataParameter);

    // 「案件-添付ファイルリレーション」新規登録
    int insCaseFileRelations(CaseFileRelations caseFileRelations);
}
