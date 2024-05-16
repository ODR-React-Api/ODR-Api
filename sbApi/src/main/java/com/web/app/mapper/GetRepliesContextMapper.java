package com.web.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.web.app.domain.couAnswerLogin.GetRepliesContext;
import java.util.List;
@Mapper
public interface GetRepliesContextMapper {
  List<GetRepliesContext> getRepliesContext(String CaseId,String PlatformId);
}
