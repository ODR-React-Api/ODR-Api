package com.web.app.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.mapper.GetCaseInfoMapper;
import com.web.app.service.DateExtensionService;

/**
 * 期日延長画面ServiceImpl
 * 
 * @author DUC 耿浩哲
 * @since 2024/05/02
 * @version 1.0
 */
@Service
public class DateExtensionServiceImpl implements DateExtensionService {

    @Autowired
    private GetCaseInfoMapper dateExtensionMapper;

    /**
     * 案件情報取得API
     *
     * @param CaseId 案件ID
     * @param PlatformId プラットフォームID
     * @return 交渉期限日
     * @throws Exception エラーの説明内容
     */
    @Override
    public Date getCaseInfo(String CaseId, String PlatformId) throws Exception {
        return dateExtensionMapper.getCaseInfo(CaseId, PlatformId);
    }
}
