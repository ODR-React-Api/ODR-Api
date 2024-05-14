package com.web.app.service.impl;

import com.web.app.domain.NegotiatMake.CaseFileRelations;
import com.web.app.domain.NegotiatMake.Files;
import com.web.app.domain.NegotiatMake.Negotiations;
import com.web.app.domain.constants.Constants;
import com.web.app.mapper.InsNegotiationsEditMapper;
import com.web.app.mapper.UpdNegotiationsEditMapper;
import com.web.app.service.NegotiatMakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 和解案編集依頼サビース
 * 
 * @author DUC 馬芹
 * @since 2024/05/06
 * @version 1.0
 */
@Service
public class NegotiatMakeServiceImpl implements NegotiatMakeService {

    @Autowired
    private UpdNegotiationsEditMapper updNegotiationsEditMapper;

    @Autowired
    private InsNegotiationsEditMapper insNegotiationsEditMapper;

    /**
     * 新規登録
     *
     * @param param1 和解案、添付ファイル、案件-添付ファイルリレーション
     * @return int
     * @throws 
     */
    @Transactional
    @Override
    public int addNegotiationsEdit(Negotiations negotiationsEdit, Files files,
            CaseFileRelations caseFileRelations) {
        // 「和解案」新規登録
        int result = insNegotiationsEditMapper.insertNegotiations(negotiationsEdit);
        if (result == Constants.RESULT_STATE_ERROR) {
            return Constants.RESULT_STATE_ERROR;
        }
        // 「添付ファイル」の新規登録
        int result2 = insNegotiationsEditMapper.insertFiles(files);
        if (result2 == Constants.RESULT_STATE_ERROR) {
            return Constants.RESULT_STATE_ERROR;
        }
        // 「案件-添付ファイルリレーション」新規登録
        int result3 = insNegotiationsEditMapper.insertCaseFileRelations(caseFileRelations);
        if (result3 == Constants.RESULT_STATE_ERROR) {
            return Constants.RESULT_STATE_ERROR;
        }
        return Constants.RESULT_STATE_SUCCESS;
    }

    /**
     * 更新登録
     *
     * @param param1 和解案、添付ファイル、案件-添付ファイルリレーション、添付ファイル、案件-添付ファイルリレーション
     * @return int
     * @throws
     */
    @Transactional
    @Override
    public int updateNegotiationsEdit(Negotiations negotiationsEdit, Files filesDelete,
            CaseFileRelations caseFileRelationsDelete, Files files,
            CaseFileRelations caseFileRelations) {

        // 「和解案」新規登録
        int result = updNegotiationsEditMapper.updateNegotiations(negotiationsEdit);
        if (result == Constants.RESULT_STATE_ERROR) {
            return Constants.RESULT_STATE_ERROR;
        }
        // 「添付ファイル」論理削除
        int resultDeleteFiles = updNegotiationsEditMapper.deleteFiles(filesDelete);
        if (resultDeleteFiles == Constants.RESULT_STATE_ERROR) {
            return Constants.RESULT_STATE_ERROR;
        }
        // 「案件-添付ファイルリレーション」論理削除
        int resultDeleteCaseFileRelations = updNegotiationsEditMapper.deleteCaseFileRelations(caseFileRelationsDelete);
        if (resultDeleteCaseFileRelations == Constants.RESULT_STATE_ERROR) {
            return Constants.RESULT_STATE_ERROR;
        }
        // 「添付ファイル」の新規登録
        int result2 = insNegotiationsEditMapper.insertFiles(files);
        if (result2 == Constants.RESULT_STATE_ERROR) {
            return Constants.RESULT_STATE_ERROR;
        }
        // 「案件-添付ファイルリレーション」新規登録
        int result3 = insNegotiationsEditMapper.insertCaseFileRelations(caseFileRelations);
        if (result3 == Constants.RESULT_STATE_ERROR) {
            return Constants.RESULT_STATE_ERROR;
        }
        return Constants.RESULT_STATE_SUCCESS;
    }
}
