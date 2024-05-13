package com.web.app.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.MediateUser;

/**
 * 調停人メール取得
 * ユーザID取得
 * 
 * @author DUC 賈文志
 * @since 2024/05/13
 * @version 1.0
 */
@Mapper
public interface GetUserIDbyMailMapper {

    //調停人メール取得
    String MediatorUserEmail(String mediateUser);
    //ユーザID取得
    String UserUid(String MediatorUserEmail);

    ArrayList<MediateUser> GetMediatorInfo(String CaseId);
}
