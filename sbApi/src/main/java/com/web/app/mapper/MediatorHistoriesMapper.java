package com.web.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.web.app.domain.UsersMessages;

@Mapper
public interface MediatorHistoriesMapper {

  List<String> usersId(String messageGroupId, String platformId, String uid);

  int mediatorHistoriesUpdate(String caseId, String uid);

  int messagesInsert(@Param("caseId") String caseId, @Param("uid") String uid, @Param("id") String id);

  // int usersMessagesInsert(String caseId, String uid, String id, List<String> result);

  int usersMessagesInsert(List<UsersMessages> usersMessages);

}
