package com.web.app.service;

import com.web.app.domain.GetReplies;

import java.util.List;

public interface GetRepliesService {
      List<GetReplies> getReplies(String CaseId,String PlatformId);
}
