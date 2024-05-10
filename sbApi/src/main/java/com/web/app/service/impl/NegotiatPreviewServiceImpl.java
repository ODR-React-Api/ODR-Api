package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.Entity.CaseFileRelations;
import com.web.app.domain.Entity.CaseNegotiations;
import com.web.app.domain.Entity.File;
import com.web.app.domain.constants.Constants;
import com.web.app.mapper.InsNegotiationDataMapper;
import com.web.app.mapper.UpdNegotiationsDataMapper;
import com.web.app.service.NegotiatPreviewService;

/**
 * 和解案データ登録
 * 
 * @author DUC 李志文
 * @since 2024/05/10
 * @version 1.0
 */
@Service
public class NegotiatPreviewServiceImpl implements NegotiatPreviewService {
    @Autowired
    private UpdNegotiationsDataMapper updNegotiationsDataMapper;

    @Autowired
    private InsNegotiationDataMapper insNegotiationDataMapper;

    @Autowired
    private UtilServiceImpl utilServiceImpl;

    /**
     * 和解案が存在するかどうかを判断する
     *
     * @param CaseNegotiations 和解案、 File ファイル
     * @return 存在->和解案データ更新、存在しない->和解案データ新規登録
     * @throws Exception エラーの説明内容
     */
    @Transactional
    @Override
    public int NegotiatPreview(CaseNegotiations caseNegotiations, File file) {
        CaseNegotiations cNegotiations = updNegotiationsDataMapper.SearchCaseNegotiations(caseNegotiations.getCaseId());
        if (cNegotiations != null) {
            if (cNegotiations.getStatus().equals(0) ||
                    cNegotiations.getStatus().equals(1) ||
                    cNegotiations.getStatus().equals(2)) {
                caseNegotiations.setStatus(2);
            } else if (cNegotiations.getStatus().equals(7) ||
                    cNegotiations.getStatus().equals(8) ||
                    cNegotiations.getStatus().equals(9)) {
                caseNegotiations.setStatus(9);
            } else if (cNegotiations.getStatus().equals(10) ||
                    cNegotiations.getStatus().equals(11) ||
                    cNegotiations.getStatus().equals(12)) {
                caseNegotiations.setStatus(12);
            } else if (cNegotiations.getStatus().equals(13) ||
                    cNegotiations.getStatus().equals(14) ||
                    cNegotiations.getStatus().equals(15)) {
                caseNegotiations.setStatus(15);
            }
            UpdNegotiationsData(caseNegotiations, file);
        } else {
            InsNegotiationData(caseNegotiations, file);
        }
        return 1;
    }

    /**
     * 和解案データ新規登録
     *
     * @param CaseNegotiations 和解案、 File ファイル
     * @return メソッドが正常に実行されたかどうか
     * @throws Exception エラーの説明内容
     */
    @Transactional
    @Override
    public int InsNegotiationData(CaseNegotiations caseNegotiations, File file) {
        caseNegotiations.setId(utilServiceImpl.GetGuid());
        file.setId(utilServiceImpl.GetGuid());
        int addCaseNegotiationsStatus = insNegotiationDataMapper.AddCaseNegotiations(caseNegotiations);
        int addFileStatus = insNegotiationDataMapper.AddFile(file);

        CaseFileRelations caseFileRelations = new CaseFileRelations();
        caseFileRelations.setId(utilServiceImpl.GetGuid());
        caseFileRelations.setPlatformId(caseNegotiations.getPlatformId());
        caseFileRelations.setCaseId(caseNegotiations.getCaseId());
        caseFileRelations.setRelatedId(caseNegotiations.getId());
        caseFileRelations.setFileId(file.getId());
        int addCaseFileRelationsStatus = insNegotiationDataMapper.AddCaseFileRelations(caseFileRelations);
        if (addCaseNegotiationsStatus == 1 && addFileStatus == 1 && addCaseFileRelationsStatus == 1) {
            return Constants.RESULT_STATE_SUCCESS;
        } else {
            return Constants.RESULT_STATE_ERROR;
        }
    }

    /**
     * 和解案データ更新
     *
     * @param CaseNegotiations 和解案、 File ファイル
     * @return メソッドが正常に実行されたかどうか
     * @throws Exception エラーの説明内容
     */
    @Transactional
    @Override
    public int UpdNegotiationsData(CaseNegotiations caseNegotiations, File file) {
        int upCaseNegotiationsStatus = updNegotiationsDataMapper.UpCaseNegotiations(caseNegotiations);
        int upFileStatus = updNegotiationsDataMapper.UpFile(file);
        CaseFileRelations caseFileRelations = new CaseFileRelations();
        caseFileRelations.setFileId(file.getId());
        int upCaseFileRelationsStatus = updNegotiationsDataMapper.UpCaseFileRelations(caseFileRelations);

        int addFileStatus = insNegotiationDataMapper.AddFile(file);
        int addCaseFileRelationsStatus = insNegotiationDataMapper.AddCaseFileRelations(caseFileRelations);
        if (upCaseNegotiationsStatus == 1 && upFileStatus == 1 && upCaseFileRelationsStatus == 1 && addFileStatus == 1
                && addCaseFileRelationsStatus == 1) {
            return Constants.RESULT_STATE_SUCCESS;
        } else {
            return Constants.RESULT_STATE_ERROR;
        }
    }
}
