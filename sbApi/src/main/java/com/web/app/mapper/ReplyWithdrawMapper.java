package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.ReplyWithdraw.ReplyWithdraw;

@Mapper
public interface ReplyWithdrawMapper {

    int updateReplyWithdrawByCase(ReplyWithdraw ReplyWithdraw);

    int updateReplyWithdrawByCaseReplies(ReplyWithdraw ReplyWithdraw);
}
