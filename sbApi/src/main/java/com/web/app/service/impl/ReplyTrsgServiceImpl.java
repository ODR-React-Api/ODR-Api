package com.web.app.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.web.app.domain.ReplyTrsg.ReplyWithdraw;
import com.web.app.mapper.ReplyWithdrawMapper;
import com.web.app.service.ReplyTrsgService;
import com.web.app.service.UtilService;

/**
 * 反訴取り下げ画面ServiceImpl
 * 
 * @author DUC 耿浩哲
 * @since 2024/04/24
 * @version 1.0
 */
@Service
public class ReplyTrsgServiceImpl implements ReplyTrsgService {

    @Autowired
    private ReplyWithdrawMapper replyWithdrawMapper;

    @Autowired
    private UtilService utilService;

    /**
     * 反訴取り下げServiceAPI
     *
     * @param ReplyWithdraw 反訴取り下げオブジェクト
     * @return DB更新は成功しましたか
     * @throws Exception エラーの説明内容
     */
    @Transactional
    @Override
    public int replyWithdraw (ReplyWithdraw replyWithdraw) throws Exception {
        replyWithdraw.setNegotiationEndDate(utilService.AddDaysToDate(new Date(), utilService.GetMasterPlatforms(replyWithdraw.getPlatformId()).getNegotiationLimitDays()));
        int updateCaseNum = replyWithdrawMapper.updateReplyWithdrawByCase(replyWithdraw);
        int updateCaseReplies = replyWithdrawMapper.updateReplyWithdrawByCaseReplies(replyWithdraw);
        if (updateCaseNum == 1 && updateCaseReplies == 1) {
            return 1;
        }
        // 手動ロールバック
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return 0;
    }

}
