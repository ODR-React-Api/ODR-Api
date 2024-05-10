package com.web.app.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.MediateUser;

/**
 * 調停案ステータス取得
 * 調停人メール取得
 * 調停人情報取得
 * 
 * @author DUC jiawenzhi
 * @since 2024/05/10
 * @version 1.0
 */
@Mapper
public interface GetMediationStatusMapper {
   //調停案ステータス取得
    String Mediationstatus(String CaseId);

    String MediatorUserEmail(MediateUser mediateUser);
    
    String MediatorUserUid(String MediatorUserEmail);

    ArrayList<MediateUser> MediatorIntelligence(MediateUser MediatorIntelligence);
}
