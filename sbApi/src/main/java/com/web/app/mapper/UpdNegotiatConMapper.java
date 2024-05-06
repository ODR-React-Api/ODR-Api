package com.web.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.UpdNegotiatCon;

@Mapper
public interface UpdNegotiatConMapper {
  Integer getNegotiationStatus(String negotiationId);
  int setNegotiationStatus(UpdNegotiatCon updNegotiatCon);
  
}
