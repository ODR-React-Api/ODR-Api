package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * API_ID:交渉期限延長可能日数取得API
 * 
 * プラットフォームマスタテーブルから交渉期限延長可能日数を取得する。
 * 
 * @author DUC 徐義然
 * @since 2024/05/15
 * @version 1.0
 */
@Mapper
public interface GetNegotiationExtendDaysMapper {
    //交渉期限延長可能日数取得
    String getNegotiationExtendDays(String platformId);
}
