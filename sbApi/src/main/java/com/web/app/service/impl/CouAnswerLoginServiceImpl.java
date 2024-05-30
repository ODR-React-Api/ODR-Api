package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.web.app.domain.CouAnswerLogin.CaseFileRelations;
import com.web.app.domain.CouAnswerLogin.InsClaimRepliesDto;
import com.web.app.domain.CouAnswerLogin.RepliesContext;
import com.web.app.domain.Entity.CaseClaimReplies;
import com.web.app.domain.Entity.Files;
import com.web.app.mapper.GetRepliesContextMapper;
import com.web.app.mapper.InsClaimRepliesDataMapper;
import com.web.app.service.CouAnswerLoginService;
import com.web.app.service.UtilService;

/**
 * 反訴回答登録画面
 * 
 * @author DUC 信召艶
 * @since 2024/04/29
 * @version 1.0
 */
@Service
public class CouAnswerLoginServiceImpl implements CouAnswerLoginService {

    // 反訴・回答データ取得
    @Autowired
    private GetRepliesContextMapper getRepliesContextMapper;

    @Autowired
    private InsClaimRepliesDataMapper insClaimRepliesDataMapper;

    @Autowired
    private UtilService utilService;
    /**
     * 反訴・回答データ取得
     *
     * @param caseId     セッション情報のcaseid
     * @param PlatformId セッション情報のプラットフォームID
     * @return getRepliesContextList
     * @throws Exception エラーの説明内容
     */
    @Override
    public List<RepliesContext> getRepliesContext(String CaseId, String PlatformId) {
        List<RepliesContext> getRepliesContextList = new ArrayList<RepliesContext>();
        getRepliesContextList = getRepliesContextMapper.getRepliesContext(CaseId, PlatformId);
        return getRepliesContextList;
    }

    /**
     * API_反訴への回答データ新規登録
     *
     * @param insClaimRepliesDto API_反訴への回答データ新規登録の引数
     * @throws Exception エラーの説明内容
     */
    @Override
    public Integer insClaimRepliesData(InsClaimRepliesDto insClaimRepliesDto) {
        // 「反訴への回答」新規登録
        CaseClaimReplies insClaimReplies = new CaseClaimReplies();
        // 自動生成GIUD
        insClaimReplies.setId(utilService.GetGuid());
        // セッション情報の案件ID
        insClaimReplies.setCaseId(insClaimRepliesDto.getCaseId());
        // セッション情報のプラットフォームID
        insClaimReplies.setPlatformId(insClaimRepliesDto.getPlatformId());
        // 画面項目の反訴への回答
        insClaimReplies.setReplyContext(insClaimRepliesDto.getReplyContext());
        // ログインユーザ
        insClaimReplies.setLastModifiedBy(insClaimRepliesDto.getLastModifiedBy());
        int insClaimRepliesNum = insClaimRepliesDataMapper.insClaimReplies(insClaimReplies);
        if (insClaimRepliesNum == 0) {
            return 0;
        }

        // 「添付ファイル」の新規登録
        Files files = new Files();
        // 自動生成GIUD
        files.setId(utilService.GetGuid());   
        // セッションのプラットフォームID
        files.setPlatformId(insClaimRepliesDto.getPlatformId());
        // セッション情報のCaseId
        files.setCaseId(insClaimRepliesDto.getCaseId());
        // 画面項目から
        files.setFileName(insClaimRepliesDto.getFileName());
        // 画面項目から
        files.setFileExtension(insClaimRepliesDto.getFileExtension());
        // ＜内部ロジック生成ファイルURL＞
        files.setFileUrl(insClaimRepliesDto.getFileUrl());
        // ＜内部ロジック生成ファイルサイ＞
        files.setFileSize(insClaimRepliesDto.getFileSize());
        // ログインユーザ
        files.setRegisterUserId(insClaimRepliesDto.getRegisterUserId());
        int insClaimRepliesDataFilesNum = insClaimRepliesDataMapper.insClaimRepliesDataFiles(files);
        if (insClaimRepliesDataFilesNum == 0) {
            return 0;
        }

        // 「案件-添付ファイルリレーション」新規登録
        CaseFileRelations caseFileRelations = new CaseFileRelations();
        // 反訴への回答.id
        caseFileRelations.setRelatedId(utilService.GetGuid());
        // 添付ファイル.id
        caseFileRelations.setFileId(utilService.GetGuid());
        // セッションのプラットフォームID
        caseFileRelations.setPlatformId(insClaimRepliesDto.getPlatformId());
        // セッション情報のCaseId
        caseFileRelations.setCaseId(insClaimRepliesDto.getCaseId());
        // ログインユーザ
        caseFileRelations.setLastModifiedBy(insClaimRepliesDto.getLastModifiedBy());
        insClaimRepliesDataMapper.insClaimRepliesDataFilesRelations(caseFileRelations);
        int insClaimRepliesDataFilesRelationsNum = insClaimRepliesDataMapper.insClaimRepliesDataFiles(files);
        if (insClaimRepliesDataFilesRelationsNum == 0) {
            return 0;
        }    
    return 1;
    }
}