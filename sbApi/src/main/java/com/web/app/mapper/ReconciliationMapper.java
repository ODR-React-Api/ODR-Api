package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.ReconciliationUser;

@Mapper
public interface ReconciliationMapper {
 
    ReconciliationUser reconciliationInsertUpdateDeleteTransactional(ReconciliationUser reconciliationUser);
}
