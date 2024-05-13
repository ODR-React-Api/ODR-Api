package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.NamAccept.UpdMediatorHistories;
import com.web.app.mapper.NamAcceptMapper;
import com.web.app.service.NamAcceptService;

@Service
public class NamAcceptServiceImpl implements NamAcceptService {


    @Autowired
    private NamAcceptMapper namAcceptMapper;

    @Transactional
    @Override
    public int UpdMediatorHistories(UpdMediatorHistories updMediatorHistories) {
        return namAcceptMapper.UpdMediatorHistories(updMediatorHistories);
    }

}
