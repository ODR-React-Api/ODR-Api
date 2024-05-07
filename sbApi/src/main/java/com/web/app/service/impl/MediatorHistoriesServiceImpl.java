package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.UsersMessages;
import com.web.app.mapper.MediatorHistoriesMapper;

import com.web.app.service.MediatorHistoriesService;

@Service
public class MediatorHistoriesServiceImpl implements MediatorHistoriesService {

  @Autowired
  private MediatorHistoriesMapper mediatorHistoriesMapper;

  @Override
  public int updateMediatorHistoriesData(String caseId, String uid, String platformId, String messageGroupId) {

    List<String> result = mediatorHistoriesMapper.usersId(messageGroupId, platformId, uid);

    int updateNum = mediatorHistoriesMapper.mediatorHistoriesUpdate(caseId, uid);

    String id = UUID.randomUUID().toString();
    int insertMessageNum = mediatorHistoriesMapper.messagesInsert(caseId, uid, id);
    System.out.println("insertMessageNum:" + insertMessageNum);

    List<UsersMessages> usersMessagesList = new ArrayList<>();

    for (String item : result) {
      UsersMessages usersMessages = new UsersMessages();
      usersMessages.setId(UUID.randomUUID().toString());
      usersMessages.setMessageId(id);
      usersMessages.setUserId(item);
      usersMessages.setCaseId(caseId);
      usersMessages.setPlatformId(platformId);
      usersMessagesList.add(usersMessages);
    }

    int insertUMessageNum = mediatorHistoriesMapper.usersMessagesInsert(usersMessagesList);

    if (updateNum == 0 || insertMessageNum == 0 || insertUMessageNum == 0) {
      return 0;
    }

    return 1;
  }

}
