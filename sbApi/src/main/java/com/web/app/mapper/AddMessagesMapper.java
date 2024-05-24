package com.web.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.web.app.domain.Entity.OdrUsers;
import com.web.app.domain.Entity.UsersMessages;

/**
 * 調停人退出メッセージ登録Mapper
 * 
 * @author DUC 王亞テイ
 * @since 2024/04/23
 * @version 1.0
 */
@Mapper
public interface AddMessagesMapper {

    // セッション情報のCaseId対応な申立人・相手方・代理人のuserid
    List<String> usersId(String messageGroupId, String platformId, String uid);

    // 調停人変更履歴の変更
    int mediatorHistoriesUpdate(String caseId, String uid);

    // 「メッセージ」新規登録
    int messagesInsert(@Param("caseId") String caseId, @Param("uid") String uid, @Param("id") String id);

    // 「ユーザメッセージ」新規登録
    int usersMessagesInsert(List<UsersMessages> usersMessages);

    // 調停人email取得
    OdrUsers userEmail(String uid);

}
