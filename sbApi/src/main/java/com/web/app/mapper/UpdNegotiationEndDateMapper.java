package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.DateExtensionRec.UpdNegotiationEndDate;

/**
 * 期日延長承認画面Mapper
 * 
 * @author DUC 耿浩哲
 * @since 2024/06/06
 * @version 1.0
 */
@Mapper
public interface UpdNegotiationEndDateMapper {
    
    // 期日延長申請承認
    int updNegotiationEndDate(UpdNegotiationEndDate updNegotiationEndDate);

}
