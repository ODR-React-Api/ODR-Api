package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.DateExtensionRec.UpdNegotiationEndDate;
import com.web.app.mapper.UpdNegotiationEndDateMapper;
import com.web.app.service.DateExtensionRecService;

/**
 * 期日延長承認画面ServiceImpl
 * 
 * @author DUC 耿浩哲
 * @since 2024/06/06
 * @version 1.0
 */
@Service
public class DateExtensionRecServiceImpl implements DateExtensionRecService {

    @Autowired
    private UpdNegotiationEndDateMapper updNegotiationEndDateMapper;

    /**
     * 期日延長申請承認API
     *
     * @param updNegotiationEndDate 期日延長申請承認オブジェクト
     * @return 案件情報更新の状況
     * @throws Exception エラーの説明内容
     */
    @Transactional
    @Override
    public int updNegotiationEndDate(UpdNegotiationEndDate updNegotiationEndDate) throws Exception {
        return updNegotiationEndDateMapper.updNegotiationEndDate(updNegotiationEndDate);
    }

}
