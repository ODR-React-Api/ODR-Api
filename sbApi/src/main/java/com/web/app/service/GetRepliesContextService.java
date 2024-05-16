package com.web.app.service;
import java.util.List;

import com.web.app.domain.couAnswerLogin.GetRepliesContext;
public interface GetRepliesContextService {
  List<GetRepliesContext> getRepliesContext(String CaseId,String PlatformId);
}
