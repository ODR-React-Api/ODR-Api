package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.ReplyTrsg.ReplyWithdraw;

/**
 * 反訴取り下げ画面Mapper
 * 
 * @author DUC 耿浩哲
 * @since 2024/04/24
 * @version 1.0
 */
@Mapper
public interface ReplyWithdrawMapper {

    // 案件テーブルの反訴取り下げ
    int updateReplyWithdrawByCase(ReplyWithdraw ReplyWithdraw);

    // 反訴・回答テーブルの反訴取り下げ
    int updateReplyWithdrawByCaseReplies(ReplyWithdraw ReplyWithdraw);
}
