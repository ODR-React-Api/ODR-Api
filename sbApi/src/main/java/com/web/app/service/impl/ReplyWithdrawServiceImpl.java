package com.web.app.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.web.app.mapper.ReplyWithdrawMapper;
import com.web.app.service.ReplyWithdrawService;
import com.web.app.service.UtilService;
import com.web.app.domain.ReplyWithdraw.ReplyWithdraw;

@Service
public class ReplyWithdrawServiceImpl implements ReplyWithdrawService {

    @Autowired
    private ReplyWithdrawMapper replyWithdrawMapper;

    @Autowired
    private UtilService utilService;

    @Transactional
    @Override
    public int ReplyWithdraw(ReplyWithdraw replyWithdraw) {
        replyWithdraw.setNegotiationEndDate(utilService.AddDaysToDate(new Date(), utilService.GetMasterPlatforms(replyWithdraw.getPlatformId()).getNegotiationLimitDays()));
        int updateCaseNum = replyWithdrawMapper.updateReplyWithdrawByCase(replyWithdraw);
        int updateCaseReplies = replyWithdrawMapper.updateReplyWithdrawByCaseReplies(replyWithdraw);
        if (updateCaseNum == 1 && updateCaseReplies == 1) {
            return 1;
        }
        // 手动回滚
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return 0;
    }

}
