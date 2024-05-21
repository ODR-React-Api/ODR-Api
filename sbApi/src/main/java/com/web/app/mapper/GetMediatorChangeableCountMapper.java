package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.Entity.Cases;

/**
 * 調停変更回数取得
 * 
 * @author DUC 李志文
 * @since 2024/05/16
 * @version 1.0
 */
@Mapper
public interface GetMediatorChangeableCountMapper {

    //調停変更回数取得
    Cases SelCases(String caseId);
}
