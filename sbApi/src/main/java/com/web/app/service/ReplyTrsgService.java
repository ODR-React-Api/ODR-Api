package com.web.app.service;

import com.web.app.domain.ReplyTrsg.ReplyWithdraw;

/**
 * 反訴取り下げ画面Service
 * 
 * @author DUC 耿浩哲
 * @since 2024/04/24
 * @version 1.0
 */
public interface ReplyTrsgService {

    int replyWithdraw(ReplyWithdraw ReplyWithdraw) throws Exception;
}
