package com.web.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.GetPreUserData;

@Mapper
public interface GetPreUserDataMapper {
  GetPreUserData getPreUserDataSearch(String guid, String registerDate);

  List<GetPreUserData> getUserPre(String guid);
}
