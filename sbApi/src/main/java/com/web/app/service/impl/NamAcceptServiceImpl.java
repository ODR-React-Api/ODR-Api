package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.NamAccept.UpdMediatorHistories;
import com.web.app.mapper.UpdMediatorHistoriesMapper;
import com.web.app.service.NamAcceptService;

/**
 * 指名受理画面ServiceImpl
 * 
 * @author DUC 耿浩哲
 * @since 2024/05/08
 * @version 1.0
 */
@Service
public class NamAcceptServiceImpl implements NamAcceptService {

    @Autowired
    private UpdMediatorHistoriesMapper updMediatorHistoriesMapper;

    /**
     * 調停人変更履歴変更API
     *
     * @param ReplyWithdraw 調停人変更履歴変更オブジェクト
     * @return に答える
     * @throws Exception エラーの説明内容
     */
    @Transactional
    @Override
    public int UpdMediatorHistories(UpdMediatorHistories updMediatorHistories) {
        return updMediatorHistoriesMapper.UpdMediatorHistories(updMediatorHistories);
    }

}