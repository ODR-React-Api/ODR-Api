package com.web.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.UpdNegotiatCon;

public interface UpdNegotiatConMapper {
  String getNegotiationStatus(String negotiationId);
  int setNegotiationStatus(String string);

}
