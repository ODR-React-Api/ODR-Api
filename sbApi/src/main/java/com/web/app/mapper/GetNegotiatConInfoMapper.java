package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.Entity.CaseNegotiations;
import com.web.app.domain.NegotiatAgree.NegotiatAgree;

/**
 * 和解案合意画面
 * 
 * @author DUC 李志文
 * @since 2024/05/14
 * @version 1.0
 */
@Mapper
public interface GetNegotiatConInfoMapper {
    //和解案確認データ取得
    CaseNegotiations selCaseNegotiations(NegotiatAgree negotiatAgree);
}
