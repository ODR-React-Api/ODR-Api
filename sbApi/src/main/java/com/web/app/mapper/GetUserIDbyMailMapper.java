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
public interface GetUserIDbyMailMapper {

    //調停人メール取得
    String MediatorUserEmail(String mediateUser);

    String MediatorUserUid(String MediatorUserEmail);

    ArrayList<MediateUser> MediatorIntelligence(MediateUser MediatorIntelligence);
}
