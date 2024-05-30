package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.Entity.CaseClaimReplies;
import com.web.app.domain.CouAnswerLogin.CaseFileRelations;
import com.web.app.domain.Entity.Files;

/**
 * API_反訴への回答データ新規登録
 * 
 * @author DUC 信召艶
 * @since 2024/05/13
 * @version 1.0
 */
@Mapper
public interface InsClaimRepliesDataMapper {
    // 反訴への回答データ新規登録
    int insClaimReplies(CaseClaimReplies insClaimReplies);

    int insClaimRepliesDataFiles(Files files);

    int insClaimRepliesDataFilesRelations(CaseFileRelations caseFileRelations);

}