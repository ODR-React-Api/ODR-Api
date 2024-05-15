package com.web.app.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.app.domain.PoliciesInfo;
import com.web.app.mapper.GetPoliciesInfoMapper;
import com.web.app.service.PoliciesConfirmService;

/**
 * S1_利用規約確認画面
 * Service層実現類
 * PoliciesConfirmServiceImpl
 * 
 * @author DUC 閆文静
 * @since 2024/04/19
 * @version 1.0
 */
@Service
public class PoliciesConfirmServiceImpl implements PoliciesConfirmService {

    @Autowired
    private GetPoliciesInfoMapper getPoliciesInfoMapper;

    /**
     * 利用規約情報取得
     *
     * @return 利用規約情報
     */
    @Override
    public PoliciesInfo getPoliciesInfo() {
        PoliciesInfo policiesInfo = new PoliciesInfo();
        // 利用規約情報取得
        policiesInfo = getPoliciesInfoMapper.selectPoliciesInfo();
        return policiesInfo;
    }
}
