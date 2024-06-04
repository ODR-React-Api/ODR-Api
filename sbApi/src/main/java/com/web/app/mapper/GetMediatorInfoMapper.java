package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.MedUserConfirm.MedUserConfirmSession;

/**
 * 調停人の経験取得
 * 
 * @author DUC 馬芹
 * @since 2024/05/10
 * @version 1.0
 */
@Mapper
public interface GetMediatorInfoMapper {
    // 調停人が担当して、解決出来た案件数を取得
    int getSolveMediatorCount(MedUserConfirmSession medUserConfirmSession);

    // 調停人の担当件総件数を取得
    int getMediatorCount(MedUserConfirmSession medUserConfirmSession);

}
