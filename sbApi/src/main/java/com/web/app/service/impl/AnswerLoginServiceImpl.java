package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.config.AnswerLogin.RepliesData;
import com.web.app.mapper.GetRepliesDataMapper;
import com.web.app.service.AnswerLoginService;

/**
 * S11_回答登録画面
 * 
 * @author DUC 信召艶
 * @since 2024/04/25
 * @version 1.0
 */
@Service
public class AnswerLoginServiceImpl implements AnswerLoginService {
    @Autowired
    private GetRepliesDataMapper getRepliesDataMapper;

    /**
     * 反訴・回答データ取得
     *
     * @param caseId     セッション情報のcaseid
     * @param PlatformId セッション情報のプラットフォームID
     * @return getRepliesList
     */
    @Override
    public List<RepliesData> getRepliesData(String caseId, String platformId) {
        List<RepliesData> getRepliesDataList = new ArrayList<RepliesData>();
        getRepliesDataList = getRepliesDataMapper.getReplies(caseId, platformId);
        return getRepliesDataList;
    }
}
