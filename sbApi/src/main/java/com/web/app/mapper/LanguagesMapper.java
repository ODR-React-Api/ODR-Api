package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import com.web.app.domain.Languages;

@Mapper
public interface LanguagesMapper {

  List<Languages> selectAllLanguages();
  
}
