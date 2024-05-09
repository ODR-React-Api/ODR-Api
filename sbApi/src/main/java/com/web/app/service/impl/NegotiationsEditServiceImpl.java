package com.web.app.service.impl;

import com.web.app.domain.CaseFileRelations;
import com.web.app.domain.Files;
import com.web.app.domain.NegotiationsEdit;
import com.web.app.domain.constants.Constants;
import com.web.app.mapper.NegotiationsEditMapper;
import com.web.app.service.NegotiationsEditService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 和解案編集依頼サビース
 * 
 * @author DUC 馬芹
 * @since 2024/05/06
 * @version 1.0
 */
@Service
public class NegotiationsEditServiceImpl implements NegotiationsEditService {

    @Autowired
    private NegotiationsEditMapper negotiationsEditMapper;
    /**
     * 和解案状態を抽出
     *
     * @param param1 和解案
     * @return List<Integer> 
     * @throws
     */
    public List<Integer> selectStatusList(NegotiationsEdit negotiationsEdit) {

        List<Integer> status = negotiationsEditMapper.selectStatusList(negotiationsEdit.getCaseId(),
                negotiationsEdit.getPlatformId(), negotiationsEdit.getDeleteFlag());

        return status;

    }

    /**
     * 新規登録
     *
     * @param param1 和解案、添付ファイル、案件-添付ファイルリレーション
     * @return int
     * @throws 
     */
    @Transactional
    @Override
    public int addNegotiationsEdit(NegotiationsEdit negotiationsEdit, Files files,
            CaseFileRelations caseFileRelations) {
        // 「和解案」新規登録
        int result = negotiationsEditMapper.insertNegotiationsEdit(negotiationsEdit);
        if (result == Constants.RESULT_STATE_ERROR) {
            return Constants.RESULT_STATE_ERROR;
        }
        // 「添付ファイル」の新規登録
        int result2 = negotiationsEditMapper.insertFiles(files);
        if (result2 == Constants.RESULT_STATE_ERROR) {
            return Constants.RESULT_STATE_ERROR;
        }
        // 「案件-添付ファイルリレーション」新規登録
        int result3 = negotiationsEditMapper.insertCaseFileRelations(caseFileRelations);
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
    public int updateNegotiationsEdit(NegotiationsEdit negotiationsEdit, Files filesDelete,
            CaseFileRelations caseFileRelationsDelete, Files files,
            CaseFileRelations caseFileRelations) {

        // 「和解案」新規登録
        int result = negotiationsEditMapper.updateNegotiationsEdit(negotiationsEdit);
        if (result == Constants.RESULT_STATE_ERROR) {
            return Constants.RESULT_STATE_ERROR;
        }
        // 「添付ファイル」論理削除
        int resultDeleteFiles = negotiationsEditMapper.deleteFiles(filesDelete);
        if (resultDeleteFiles == Constants.RESULT_STATE_ERROR) {
            return Constants.RESULT_STATE_ERROR;
        }
        // 「案件-添付ファイルリレーション」論理削除
        int resultDeleteCaseFileRelations = negotiationsEditMapper.deleteCaseFileRelations(caseFileRelationsDelete);
        if (resultDeleteCaseFileRelations == Constants.RESULT_STATE_ERROR) {
            return Constants.RESULT_STATE_ERROR;
        }
        // 「添付ファイル」の新規登録
        int result2 = negotiationsEditMapper.insertFiles(files);
        if (result2 == Constants.RESULT_STATE_ERROR) {
            return Constants.RESULT_STATE_ERROR;
        }
        // 「案件-添付ファイルリレーション」新規登録
        int result3 = negotiationsEditMapper.insertCaseFileRelations(caseFileRelations);
        if (result3 == Constants.RESULT_STATE_ERROR) {
            return Constants.RESULT_STATE_ERROR;
        }
        return Constants.RESULT_STATE_SUCCESS;
    }
}
