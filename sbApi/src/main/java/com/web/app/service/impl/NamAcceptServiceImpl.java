package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.NamAccept.UpdMediatorHistories;
import com.web.app.mapper.UpdCaseStatusForAcceptMapper;
import com.web.app.mapper.UpdMediatorHistoriesMapper;
import com.web.app.service.NamAcceptService;

/**
 * S33_指名受理画面
 * Service層実現類
 * NamAcceptServiceImpl
 * 
 * @author DUC 閆文静 耿浩哲
 * @since 2024/05/08
 * @version 1.0
 */
@Service
public class NamAcceptServiceImpl implements NamAcceptService {

    @Autowired
    private UpdCaseStatusForAcceptMapper updCaseStatusForAcceptMapper;

    @Autowired
    private UpdMediatorHistoriesMapper updMediatorHistoriesMapper;

    /**
     * 申立状態を更新
     * 調停人履歴レコードを更新
     * 
     * @param caseId 案件ID
     */
    @Override
    public int updCaseStatusForAccept(String caseId) {
        updCaseStatusForAcceptMapper.updCase(caseId);
        int updMediatorHistoriesCount = updCaseStatusForAcceptMapper.updMediatorHistories(caseId);
        return updMediatorHistoriesCount;
    }

    /**
     * 調停人変更履歴変更API
     *
     * @param ReplyWithdraw 調停人変更履歴変更オブジェクト
     * @return に答える
     * @throws Exception エラーの説明内容
     */
    @Transactional
    @Override
    public int updMediatorHistories(UpdMediatorHistories updMediatorHistories) throws Exception {
        return updMediatorHistoriesMapper.updMediatorHistories(updMediatorHistories);
    }

}