package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.mapper.UpdCaseStatusForAcceptMapper;
import com.web.app.service.NamAcceptService;

/**
 * API_案件ステータス更新
 * 
 * @author DUC 閆文静
 * @since 2024/05/14
 * @version 1.0
 */
@Service
public class NamAcceptServiceImpl implements NamAcceptService {

    @Autowired
    private UpdCaseStatusForAcceptMapper updCaseStatusForAcceptMapper;

    /**
     * 申立状態を更新
     * 
     * @param caseId 案件ID
     */
    @Override
    public int updCase(String caseId) {
        return updCaseStatusForAcceptMapper.updCase(caseId);
    }

    /**
     * 調停人履歴レコードを更新
     * 
     * @param caseId 案件ID
     */
    @Override
    public int updMediatorHistories(String caseId) {
        return updCaseStatusForAcceptMapper.updMediatorHistories(caseId);
    }

}
