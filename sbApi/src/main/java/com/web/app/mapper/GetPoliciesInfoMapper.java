package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.PoliciesConfirm.PoliciesInfo;

/**
 * API_利用規約情報取得
 * 
 * @author DUC 閆文静
 * @since 2024/04/19
 * @version 1.0
 */
@Mapper
public interface GetPoliciesInfoMapper {
    // 利用規約情報取得
    PoliciesInfo selectPoliciesInfo();  
}