package com.web.app.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.ReconciliationUser;
import com.web.app.domain.ActionHistories;
@Mapper
public interface ReconciliationMapper {
    ReconciliationUser reconciliationUserSearch(ReconciliationUser reconciliationUser);
    int reconciliationUpdate(ReconciliationUser reconciliationUser);
    int ActionHistoriesInsert(ActionHistories ActionHistories);
    ActionHistories UserSearch(ActionHistories ActionHistories);
}
