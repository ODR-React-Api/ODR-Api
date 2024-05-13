package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.UpdNegotiatAgree.ReconciliationUser;

/**
 * 和解案合意更新API
 * 
 * @author DUC 賈文志
 * @since 2024/05/10
 * @version 1.0
 */

@Mapper
public interface UpdNegotiatAgreeMapper {

    // 和解案合意更新API
    int reconciliationUpdate(ReconciliationUser reconciliationUser);
}