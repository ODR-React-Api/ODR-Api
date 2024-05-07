package com.web.app.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.ReconciliationUser;
import com.web.app.domain.ActionHistories;
import com.web.app.domain.Entity.Cases;

@Mapper
public interface ReconciliationMapper {
    ReconciliationUser reconciliationUserSearch(ReconciliationUser reconciliationUser);
    int reconciliationUpdate(ReconciliationUser reconciliationUser);
    int ActionHistoriesInsert(ActionHistories ActionHistories);
    ActionHistories UserSearch(ActionHistories ActionHistories);
    String CaseTitleSearch(Cases cases);
}
