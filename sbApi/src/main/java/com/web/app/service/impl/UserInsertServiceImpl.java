package com.web.app.service.impl;

import java.text.DecimalFormat;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.UserInfoModel;
import com.web.app.domain.UserInsertModel;
import com.web.app.mapper.UserInsertMapper;
import com.web.app.service.UserInsertService;

@Service
public class UserInsertServiceImpl implements UserInsertService{

  @Autowired
  private UserInsertMapper userInsertMapper;

  @Override
  public int UserInsert(UserInfoModel userInfo) {
    // TODO Auto-generated method stub
    // String uid = userInsertMapper.getMaxUid();

    UserInsertModel userInsert = new UserInsertModel();
    userInsert.setUid(UUID.randomUUID().toString());
    userInsert.setEmail(userInfo.getEmail());
    userInsert.setFirstName(userInfo.getFirstName());
    userInsert.setFirstNameKana(userInfo.getFirstNameKana());
    userInsert.setLastName(userInfo.getLastName());
    userInsert.setLastNameKana(userInfo.getLastNameKana());
    userInsert.setMiddleName(userInfo.getMiddleName());
    userInsert.setMiddleNameKana(userInfo.getMiddleNameKana());
    userInsert.setCompanyName(userInfo.getCompanyName());
    userInsert.setPassword(userInfo.getPassword());
    userInsert.setLastModifiedBy(userInfo.getLastModifiedBy());
    userInsert.setStatus(0);
    userInsert.setMessageFrequency(100);
    userInsert.setTermsConfirmed(1);
    userInsert.setUserType(0);
    userInsert.setDeleteFlag(0);
    userInsert.setPlatformId("0001");
    userInsert.setLanguageId("jp");

    return userInsertMapper.userInsert(userInsert);
  }

  // private String uidFormat(String uid){
  //   DecimalFormat decimalFormat = new DecimalFormat("00000");
  //   String newUid = "";
  //   newUid = uid.substring(1,uid.length());
  //   int i = Integer.parseInt(newUid) + 1;
  //   newUid = "U" + decimalFormat.format(i);
  //   return newUid;
  // }
  
}
