package com.web.app.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Mapper;

import com.web.app.domain.medUserConfirm.GetUserIDbyMail;

/**
 * 調停者メールボックスからユーザUidを取得する
 * 
 * @author DUC 賈文志
 * @since 2024/05/13
 * @version 1.0
 */
@Mapper
public interface GetUserIDbyMailMapper {

    // 調停人メール取得
    String mediatorUserEmail(String mediateUser);

    // ユーザID取得
    String userUid(String MediatorUserEmail);

    ArrayList<GetUserIDbyMail> getMediatorInfo(String CaseId);
}
