package com.web.app.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.MediatorDisclosureRequest;
import com.web.app.mapper.GetMediatorDisclosureDateMapper;
import com.web.app.mapper.UpdMediatorDisclosureFlagMapper;
import com.web.app.service.InfoPublicSeigenService;

@Service
public class InfoPublicSeigenServiceImpl implements InfoPublicSeigenService {
    @Autowired
    private UpdMediatorDisclosureFlagMapper updMediatorDisclosureFlagMapper;

    @Autowired
    private GetMediatorDisclosureDateMapper getMediatorDisclosureDateMapper;

    /**
     * 調停人情報開示制限
     * 
     * @param mediatorDisclosureRequest 請求の変数
     * @return 変更状態
     */
    @Override
    @Transactional(noRollbackFor = { ArithmeticException.class })
    public Boolean updMediatorDisclosureFlag(MediatorDisclosureRequest mediatorDisclosureRequest) {
        try {
            mediatorDisclosureRequest.setLastModifiedDate(new Date());
            Boolean flg = updMediatorDisclosureFlagMapper.updMediatorDisclosureFlag(mediatorDisclosureRequest);
            return flg;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 調停人情報開示変更可能期限日
     * 
     * @param caseId 案件ID
     * @return Date
     */
    @Override
    public Date getMediatorDisclosureDate(String caseId) {
        return getMediatorDisclosureDateMapper.getMediatorDisclosureDate(caseId);
    }
}
