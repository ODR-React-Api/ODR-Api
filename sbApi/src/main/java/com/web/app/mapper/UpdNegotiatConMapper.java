package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.UpdNegotiatCon;

@Mapper
public interface UpdNegotiatConMapper {

  Integer getNegotiationStatus(String caseId);

  int setNegotiationStatus(UpdNegotiatCon updNegotiatCon);

  UpdNegotiatCon getRelationsEmail(UpdNegotiatCon updNegotiatCon);

  UpdNegotiatCon getUsersEmail(UpdNegotiatCon updNegotiatCon);

}
